package com.leoric.brickbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.leoric.BBhelpers.AssetLoader;
import com.leoric.screens.SplashScreen;

public class BBGame extends Game {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Gdx.app.log("BBGame", "created");
		AssetLoader.load();
		setScreen(new SplashScreen(this, googleService));
	}

	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	
	public IGoogleServices googleService;

	public BBGame(IGoogleServices googleServices)
	{
		super();
		this.googleService = googleServices;
	}
}
