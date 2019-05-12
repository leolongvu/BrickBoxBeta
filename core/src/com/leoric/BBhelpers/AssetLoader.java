package com.leoric.BBhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture;
	public static TextureRegion background, brick, playbuttonup, playbuttondown, readylogo, namelogo,
	highscore, highscoredown, rating, ratingdown, gameover, gameoverpopup, backbutton, backbuttondown
	, replaybutton, replaybuttondown, elite, inter, amatuer, unranked, logo;
	
	public static Sound endBrick, setBrick, lost;
	
	public static Preferences prefs;
	
	public static void load() {

		texture = new Texture(Gdx.files.internal("back.jpg"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		background = new TextureRegion(texture, 0, 0, 256, 256);

		texture = new Texture(Gdx.files.internal("brick.jpg"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		brick = new TextureRegion(texture, 0, 0, 256, 256);
		
		texture = new Texture(Gdx.files.internal("playbutton1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playbuttonup = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("highscore1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		highscore = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("highscoredown1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		highscoredown = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("playbuttondown1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		playbuttondown = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("readytext.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		readylogo = new TextureRegion(texture, 0, 0, 262, 230);
		
		texture = new Texture(Gdx.files.internal("gamename1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		namelogo = new TextureRegion(texture, 0, 0, 1317, 558);
		
		texture = new Texture(Gdx.files.internal("rating1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rating = new TextureRegion(texture, 0, 0, 586, 216);
		
		texture = new Texture(Gdx.files.internal("ratingdown1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		ratingdown = new TextureRegion(texture, 0, 0, 586, 216);
		
		texture = new Texture(Gdx.files.internal("gameover1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gameover = new TextureRegion(texture, 0, 0, 1673, 246);
		
		texture = new Texture(Gdx.files.internal("gameoverpopup1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gameoverpopup = new TextureRegion(texture, 0, 0, 600, 390);
		
		texture = new Texture(Gdx.files.internal("backbutton1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		backbutton = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("backbutton1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		backbutton = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("backbuttondown1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		backbuttondown = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("replaybutton1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		replaybutton = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("replaybuttondown1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		replaybuttondown = new TextureRegion(texture, 0, 0, 600, 374);
		
		texture = new Texture(Gdx.files.internal("elite.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		elite = new TextureRegion(texture, 0, 0, 1017, 971);
		
		texture = new Texture(Gdx.files.internal("intermediate.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		inter = new TextureRegion(texture, 0, 0, 1017, 971);
		
		texture = new Texture(Gdx.files.internal("amatuer.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		amatuer = new TextureRegion(texture, 0, 0, 1017, 971);
		
		texture = new Texture(Gdx.files.internal("unranked.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		unranked = new TextureRegion(texture, 0, 0, 1017, 971);
		
		texture = new Texture(Gdx.files.internal("logo.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		logo = new TextureRegion(texture, 0, 0, 422, 414);
		
		endBrick = Gdx.audio.newSound(Gdx.files.internal("EndBrick.mp3"));
		setBrick = Gdx.audio.newSound(Gdx.files.internal("SetBrick.mp3"));
		lost = Gdx.audio.newSound(Gdx.files.internal("Lost.mp3"));
		
		prefs = Gdx.app.getPreferences("Brick Box");
		
		if (!prefs.contains("highScore")) {
		    prefs.putInteger("highScore", 0);
		}
		
		if (!prefs.contains("rank")) {
		    prefs.putInteger("rank", 0);
		}
	}
	
	public static void setHighScore(int val) {
	    prefs.putInteger("highScore", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static int getHighScore() {
	    return prefs.getInteger("highScore");
	}
	
	public static void setRank(int val) {
		if (val <= 10) {
			prefs.putInteger("rank", 0);
			prefs.flush();
		}
		else if (10 < val && val <= 40) {
			prefs.putInteger("rank", 1);
			prefs.flush();
		}
		else if (40 < val && val <= 80) {
			prefs.putInteger("rank", 2);
			prefs.flush();
		}
		else if (val > 80) {
			prefs.putInteger("rank", 3);
			prefs.flush();
		}
	}

	// Retrieves the current high score
	public static int getRank() {
	    return prefs.getInteger("rank");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
	}

}
