package com.leoric.gameworld;

import com.leoric.BBhelpers.AssetLoader;

public class GameWorld {	
	
	public enum GameState {
	    READY, RUNNING, GAMEOVER, MENU, MENUOVER
	}
	
	private GameState currentState;
	
	public int[][] playfield;
	
	private int row;
	private int count; 
	private int left;
	private int score;
	private double speed;
	private int shapeLeft;
	
	private int rownum;
	private int blockwidth;
	private int rowstop;
	private int gameWidth;
	private int gameHeight;
	
	private boolean newGame = true;
	private boolean isLeft = false;
	
	public int getScore() {
		return this.score;
	}
	
	public int getSpeed() {
		int respeed = 0;
		switch ((int)(speed * 1000)) {
		case 120:
			respeed = 1;
			break;
		case 110:
			respeed = 2;
			break;
		case 100:
			respeed = 3;
			break;
		case 90:
			respeed = 4;
			break;
		case 80:
			respeed = 5;
			break;
		case 70:
			respeed = 6;
			break;
		case 60:
			respeed = 7;
			break;
		case 50:
			respeed = 8;
			break;
		}
		return respeed;
	}
	
	public GameWorld(int gameWidth, int gameHeight) {
		this.blockwidth = gameWidth / 9;		
		this.rownum = Math.min(15, gameHeight / blockwidth);
		this.rowstop = rownum * 3 / 5;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		
		resetGame();
		
		currentState = GameState.MENU;
	}
	
	public int getGameWidth() {
		return this.gameWidth;
	}
	
	public int getGameHeight() {
		return this.gameHeight;
	}
	
	public int getBlockWidth() {
		return blockwidth;
	}
	
	public int getRowNum() {
		return rownum;
	}		
	
	private void moveBlock() {
		if (isLeft) {
			shapeLeft--;
			setRow(row, count, shapeLeft);
			if (shapeLeft == 0) {
				AssetLoader.endBrick.play();
				isLeft = false;
			}
		} else {
			shapeLeft++;
			setRow(row, count, shapeLeft);
			if (shapeLeft + count > 8) {
				AssetLoader.endBrick.play();
				isLeft = true;
			}
		}
	}
	
	private float speedCount = 0;
	
	public void update(float deltaTime) {
		if (currentState != GameState.GAMEOVER && currentState != GameState.MENUOVER) {
			if (speedCount < speed) {
				speedCount += deltaTime;
			}
			else {
				speedCount = 0;
				moveBlock();
			}	
		}		
	}	
	
	public void onClick() {	
		if (newGame) {		
			AssetLoader.setBrick.play();
			setRow(row, count, shapeLeft);
			left = shapeLeft;
			newGame = false;
			row++;
			score++;
		} 
		else {
			int delta = 0;
			if (left != shapeLeft) {
				if (left < shapeLeft) {
					delta = shapeLeft - left;
					if (delta >= count) {						
						if (score > AssetLoader.getHighScore()) {
			                AssetLoader.setHighScore(score);
						} 
						AssetLoader.setRank(AssetLoader.getHighScore());
						AssetLoader.lost.play();
						currentState = GameState.GAMEOVER;
					} 
					else {
						count -= delta;							
						left = shapeLeft;
						AssetLoader.setBrick.play();
						setRow(row, count, left);	
						speed = 0.1;
						if (row == rowstop) {
							scrollDown(playfield);
						} 
						else {
							row++;
						}
						score++;
					}
				} 
				else {
					delta = left - shapeLeft;
					if (delta >= count) {
						if (score > AssetLoader.getHighScore()) {
			                AssetLoader.setHighScore(score);
						} 
						AssetLoader.setRank(AssetLoader.getHighScore());
						AssetLoader.lost.play();
						currentState = GameState.GAMEOVER;
					} 
					else {
						count -= delta;
						AssetLoader.setBrick.play();
						setRow(row, count, left);
						speed = 0.1;						
						if (row == rowstop) {
							scrollDown(playfield);
						} 
						else {
							row++;
						}
						score++;
					}
				}
			} 
			else {
				AssetLoader.setBrick.play();
				if (speed > 0.059) {
					speed -= 0.01;
				} 
				if (row == rowstop) {
					scrollDown(playfield);
				} 
				else {
					row++;
				}
				score++;
			}
		}
	}
	
	private void setRow(int row, int count, int left) {
		for (int i = 0; i < left; i++) {
			playfield[row][i] = 0;
		}
		for (int i = left; i < left + count; i++) {
			playfield[row][i] = 1;
		}
		for (int i = left + count; i < 9; i++) {
			playfield[row][i] = 0;
		}
	}
	
	private void scrollDown(int[][] playfield) {
		for (int k = 0; k < rowstop; k++) {
			for (int i = 0; i < 9; i++) {
				playfield[k][i] =  playfield[k + 1][i];
			}
		}	
	}
	
	public boolean isReady() {
        return currentState == GameState.READY;
    }
	
	public void start() {
        currentState = GameState.RUNNING;
    }
	
	public boolean isStart() {
		return currentState == GameState.RUNNING;
	}
	
	public void resetGame() {
		
		playfield = new int[rownum][9];
		
		newGame = true;
		isLeft = false;
		
		count = 5;
		left = 0;
		score = 0;
		speed = 0.12;
		shapeLeft = 0;
		row = 0;
	}
	
	public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
	
	public boolean isMenu() {
        return currentState == GameState.MENU;
    }
	
	public void menu() {
		currentState = GameState.MENU;		
	}

	public boolean isMenuOver() {
		return currentState == GameState.MENUOVER;
	}
	
	public void menuOver() {
		currentState = GameState.MENUOVER;
	}
	
	public void ready() {
        currentState = GameState.READY;
    }
}
