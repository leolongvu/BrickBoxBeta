package com.leoric.gameworld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.leoric.BBhelpers.AssetLoader;
import com.leoric.BBhelpers.InputHandler;
import com.leoric.ui.Button;

public class GameRenderer {

	private GameWorld myWorld;
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;
	
	private int gameWidth;
	private int gameHeight;
	private int blockWidth;
	private int rownumber;
	private int top;
	
	private BitmapFont textFont;
	private BitmapFont menuFont;
	private BitmapFont rankFont;
	private GlyphLayout scoreGlyphLayout;
	
	private List<Button> menuButtons;
	private List<Button> menuoverButtons;
	
	public GameRenderer(GameWorld world, int gameHeight, int gameWidth) {
		myWorld = world;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		init();
	}
	
	private void init() {
		camera = new OrthographicCamera();     
		camera.setToOrtho(false, gameWidth, gameHeight);
        
        batch = new SpriteBatch();
    	batch.setProjectionMatrix(camera.combined);     
    	
    	shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    	
		this.blockWidth = myWorld.getBlockWidth();
		this.rownumber = myWorld.getRowNum();
		
		this.menuButtons = ((InputHandler)Gdx.input.getInputProcessor()).getMenuButtons();
		this.menuoverButtons = ((InputHandler)Gdx.input.getInputProcessor()).getMenuoverButtons();
		
		top = gameHeight - (blockWidth * rownumber);
		
		createFont();
		
		scoreGlyphLayout = new GlyphLayout();
	}
	
    public void render() {
    	renderWorld();
        camera.update();
    }
    
    private synchronized void renderWorld() {    	 
		Gdx.gl.glClearColor(0, 0, 0, 100);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  
		batch.begin();
		renderPlayfield(); 
    	if (myWorld.isMenu()) { 
    		drawMenuUI();
    	}
    	if (myWorld.isReady()) {
    		renderScore();
    		drawreadyLogo();
    	}
    	if (myWorld.isStart()) {     	
        	renderScore();
    	}
    	if (myWorld.isGameOver()) {
  		
    	}
    	if (myWorld.isMenuOver()) {
    		drawoverMenuUI();
    	}
        batch.end();
    }
    
    private void createFont() {
    	FileHandle fontFile = Gdx.files.internal("chickweed titling.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = (top + blockWidth) / 2;
        textFont = generator.generateFont(parameter);
        generator.dispose();
        
        FileHandle fontFile1 = Gdx.files.internal("chickweed titling.ttf");
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(fontFile1);
        FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
        parameter1.size = (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 91 / 100) / 7;
        menuFont = generator1.generateFont(parameter1);
        
        FileHandle fontFile2 = Gdx.files.internal("chickweed titling.ttf");
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(fontFile2);
        FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
        parameter2.size = (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 91 / 100) / 8;
        rankFont = generator2.generateFont(parameter2);
        generator2.dispose();
    }
    
    private void drawMenuUI() {
        batch.draw(AssetLoader.namelogo, gameWidth / 2 - blockWidth * 7 / 2, 
        		gameHeight * 67 / 100, blockWidth * 7, AssetLoader.namelogo.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.namelogo.getRegionWidth());
        for (Button button : menuButtons) {
            button.draw(batch);
        }
    }
    
    private void drawoverMenuUI() {
        batch.draw(AssetLoader.gameover, gameWidth / 2 - blockWidth * 7 / 2, 
        		gameHeight * 84 / 100, blockWidth * 7, AssetLoader.gameover.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameover.getRegionWidth());
        batch.draw(AssetLoader.gameoverpopup, gameWidth / 2 - blockWidth * 7 / 2, 
        		gameHeight * 40 / 100, blockWidth * 7, AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth());
        
        String score = "Score:";
        scoreGlyphLayout.setText(menuFont, score);
        float textHeight = scoreGlyphLayout.height;
        float textX = gameWidth / 2 - blockWidth * 21 / 8;
        float textY = gameHeight * 40 / 100 + (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 192 / 1000) +
        		(AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 3 / 4 * 91 / 100) + textHeight / 2; 
        menuFont.setColor(Color.BLACK);
        menuFont.draw(batch, score, textX, textY);
        
        float textY1 = gameHeight * 40 / 100 + (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 192 / 1000) +
        		(AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 2 / 4 * 91 / 100) + textHeight / 2;  
        menuFont.setColor(Color.BLACK);
        menuFont.draw(batch, "" + myWorld.getScore(), textX, textY1);
        
        float textY2 = gameHeight * 40 / 100 + (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 192 / 1000) +
        		(AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 1 / 4 * 91 / 100) + textHeight / 2; 
        menuFont.setColor(Color.BLACK);
        menuFont.draw(batch, "Best:", textX, textY2);
        
        float textY3 = gameHeight * 40 / 100 + (AssetLoader.gameoverpopup.getRegionHeight() * 
        		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() * 192 / 1000) + textHeight / 2; 
        menuFont.setColor(Color.BLACK);
        menuFont.draw(batch, "" + AssetLoader.getHighScore(), textX, textY3);
        
        switch (AssetLoader.getRank()) {
        case 0:
        	batch.draw(AssetLoader.unranked, gameWidth / 2 + blockWidth * 7 / 8, 
            		gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 10, blockWidth * 7 / 4, AssetLoader.unranked.getRegionHeight() * 
            		blockWidth * 7 / (4 * AssetLoader.unranked.getRegionWidth()));
        	scoreGlyphLayout.setText(rankFont, "Unranked");
        	float textX1 = gameWidth / 2 + blockWidth * 7 / 4 - scoreGlyphLayout.width / 2;
        	float textY4 = gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 6;
        	rankFont.setColor(Color.BLACK);
        	rankFont.draw(batch, "Unranked", textX1, textY4);
        	break;
        case 1:
        	batch.draw(AssetLoader.amatuer, gameWidth / 2 + blockWidth * 7 / 8, 
            		gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 10, blockWidth * 7 / 4, AssetLoader.unranked.getRegionHeight() * 
            		blockWidth * 7 / (4 * AssetLoader.unranked.getRegionWidth()));
        	scoreGlyphLayout.setText(rankFont, "Amatuer");
        	float textX2 = gameWidth / 2 + blockWidth * 7 / 4 - scoreGlyphLayout.width / 2;
        	float textY5 = gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 6;
        	rankFont.setColor(Color.BLACK);
        	rankFont.draw(batch, "Amatuer", textX2, textY5);
        	break;
        case 2:
        	batch.draw(AssetLoader.inter, gameWidth / 2 + blockWidth * 7 / 8, 
            		gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 10, blockWidth * 7 / 4, AssetLoader.unranked.getRegionHeight() * 
            		blockWidth * 7 / (4 * AssetLoader.unranked.getRegionWidth()));
        	scoreGlyphLayout.setText(rankFont, "Intermediate");
        	float textX3 = gameWidth / 2 + blockWidth * 7 / 4 - scoreGlyphLayout.width / 2;
        	float textY6 = gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 6;
        	rankFont.setColor(Color.BLACK);
        	rankFont.draw(batch, "Intermediate", textX3, textY6);
        	break;
        case 3:
        	batch.draw(AssetLoader.elite, gameWidth / 2 + blockWidth * 7 / 8, 
            		gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 10, blockWidth * 7 / 4, AssetLoader.unranked.getRegionHeight() * 
            		blockWidth * 7 / (4 * AssetLoader.unranked.getRegionWidth()));
        	scoreGlyphLayout.setText(rankFont, "Elite");
        	float textX4 = gameWidth / 2 + blockWidth * 7 / 4 - scoreGlyphLayout.width / 2;
        	float textY7 = gameHeight * 40 / 100 + AssetLoader.gameoverpopup.getRegionHeight() * 
            		blockWidth * 7 / AssetLoader.gameoverpopup.getRegionWidth() - blockWidth * 7 / 4
            		- blockWidth * 7 / 6;
        	rankFont.setColor(Color.BLACK);
        	rankFont.draw(batch, "Elite", textX4, textY7);
        	break;
        }   	                   
        
        for (Button button : menuoverButtons) {
            button.draw(batch);
        }
    }
    
    private void drawreadyLogo() {
    	batch.draw(AssetLoader.readylogo, gameWidth / 3, gameHeight * 18 / 50, blockWidth * 4, 
    			230 * blockWidth * 4 / 262);
    }
    
    public void renderPlayfield() {
    	for (int i = 0; i < rownumber - 1; i++) {
			for (int j = 0; j < 9; j++) {
				drawBlock(myWorld.playfield[i][j], i, j);
			}
		}
    }  
    
    private void renderScore() {
    	String score = "Score: " + myWorld.getScore();
    	scoreGlyphLayout.setText(textFont, score);
        float textHeight = scoreGlyphLayout.height;
        float textX = blockWidth / 4;
        float textY = gameHeight - ((top + blockWidth) / 2) + (textHeight / 2);
        textFont.setColor(Color.WHITE);
        textFont.draw(batch, score, textX, textY);
        
        String speed = "Speed: " + myWorld.getSpeed();
        scoreGlyphLayout.setText(textFont, speed);
        float textWidth = scoreGlyphLayout.width;
        float textX1 = gameWidth - textWidth - textX;
        textFont.draw(batch, speed, textX1, textY);
    }
    
    private void drawBlock(int type, int row, int col) {
    	switch (type) {
    	case 0: 
    		batch.draw(AssetLoader.background, col * blockWidth, row * blockWidth, blockWidth, blockWidth);
    		break; 
    	case 1:
    		batch.draw(AssetLoader.brick, col * blockWidth, row * blockWidth, blockWidth, blockWidth);
    		break;
    	}
    }
}