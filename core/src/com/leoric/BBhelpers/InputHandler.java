package com.leoric.BBhelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.leoric.brickbox.IGoogleServices;
import com.leoric.gameworld.GameWorld;
import com.leoric.ui.Button;
import com.sun.jndi.toolkit.url.Uri;

public class InputHandler implements InputProcessor {

	private GameWorld myWorld;
	
	private List<Button> menuButtons;
	private List<Button> menuoverButtons;

    private Button playButton;
    private Button rating;
    private Button backButton;
    private Button replayButton;
    
    IGoogleServices googleService;
	
	public InputHandler(GameWorld world, IGoogleServices googleService) {
		   myWorld = world;   
		   this.googleService = googleService;
		   menuButtons = new ArrayList<Button>(); 
		   menuoverButtons = new ArrayList<Button>(); 
		   playButton = new Button(
	                myWorld.getGameWidth() / 2 - (int)(myWorld.getBlockWidth() * 3) / 2, myWorld.getGameHeight() * 37 / 100, 
	                (int)(myWorld.getBlockWidth() * 3), AssetLoader.playbuttonup.getRegionHeight() * 
	                (int)(myWorld.getBlockWidth() * 3) / AssetLoader.playbuttonup.getRegionWidth(), 
	                AssetLoader.playbuttonup, AssetLoader.playbuttondown);
		   menuButtons.add(playButton);
		   rating = new Button(
	                myWorld.getGameWidth() / 2 - (int)(myWorld.getBlockWidth()), myWorld.getGameHeight() * 28 / 100, 
	                (int)(myWorld.getBlockWidth() * 2), AssetLoader.rating.getRegionHeight() * 
	                (int)(myWorld.getBlockWidth() * 2) / AssetLoader.rating.getRegionWidth(), 
	                AssetLoader.rating, AssetLoader.ratingdown);
		   menuButtons.add(rating);
		   backButton = new Button(
	                myWorld.getBlockWidth(), myWorld.getGameHeight() * 20 / 100, 
	                (int)(myWorld.getBlockWidth() * 3), AssetLoader.backbutton.getRegionHeight() * 
	                (int)(myWorld.getBlockWidth() * 3) / AssetLoader.backbutton.getRegionWidth(), 
	                AssetLoader.backbutton, AssetLoader.backbuttondown);
		   menuoverButtons.add(backButton);
		   replayButton = new Button(
	                myWorld.getBlockWidth() * 5, myWorld.getGameHeight() * 20 / 100, 
	                (int)(myWorld.getBlockWidth() * 3), AssetLoader.replaybutton.getRegionHeight() * 
	                (int)(myWorld.getBlockWidth() * 3) / AssetLoader.replaybutton.getRegionWidth(), 
	                AssetLoader.replaybutton, AssetLoader.replaybuttondown);
		   menuoverButtons.add(replayButton);
		}
	
	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		arg1 = myWorld.getGameHeight() - arg1;
		if (myWorld.isStart()) {
			myWorld.onClick();
		}
		else if (myWorld.isMenu()) {
			playButton.isTouchDown(arg0, arg1);
			rating.isTouchDown(arg0, arg1);
		}
		else if (myWorld.isReady()) {
            myWorld.start();
            myWorld.onClick();
        }		
		else if (myWorld.isMenuOver()) {
			backButton.isTouchDown(arg0, arg1);
            replayButton.isTouchDown(arg0, arg1);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		arg1 = myWorld.getGameHeight() - arg1;
		if (myWorld.isMenu()) {
            if (playButton.isTouchUp(arg0, arg1)) {
    			myWorld.resetGame();
                myWorld.ready();
                return true;
            }
            else if (rating.isTouchUp(arg0, arg1)) {
            	googleService.rateApp();
            }
        } 
		else if (myWorld.isGameOver()) {
			myWorld.menuOver();
			return true;
		}
		else if (myWorld.isMenuOver()) {
			if (backButton.isTouchUp(arg0, arg1)) {
				myWorld.resetGame();
				myWorld.menu();
				return true;
			}
			else if (replayButton.isTouchUp(arg0, arg1)) {
				myWorld.resetGame();
				myWorld.ready();
				return true;
			}
		}
		return false;
	}

	public List<Button> getMenuButtons() {
        return menuButtons;
    }
	
	public List<Button> getMenuoverButtons() {
        return menuoverButtons;
    }
}
