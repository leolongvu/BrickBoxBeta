package com.leoric.brickbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leoric.brickbox.BBGame;
import com.leoric.gameworld.DesktopGoogleService;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Brick Box";
		config.width = 600;
		config.height = 1000;
		new LwjglApplication(new BBGame(new DesktopGoogleService()), config);	}
}
