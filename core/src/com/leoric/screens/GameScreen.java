package com.leoric.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.leoric.BBhelpers.InputHandler;
import com.leoric.brickbox.IGoogleServices;
import com.leoric.gameworld.GameRenderer;
import com.leoric.gameworld.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	
	public GameScreen(IGoogleServices googleService) {
		Gdx.app.log("GameScreen", "Attached");
		float screenHeight = Gdx.graphics.getHeight();
		float screenWidth = Gdx.graphics.getWidth();
		world = new GameWorld((int)screenWidth, (int)screenHeight);	
		Gdx.input.setInputProcessor(new InputHandler(world, googleService));
		renderer = new GameRenderer(world, (int)screenHeight, (int)screenWidth);
	}
	
	@Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "pause called");      
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "hide called");     
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//Leave blank
	}

}
