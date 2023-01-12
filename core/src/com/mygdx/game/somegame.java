package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;

public class somegame extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Array<Emote> emotes;

	@Override
	public void create () {
		// Setup camera and SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);
		batch = new SpriteBatch();

		// Create emotes
		emotes = new Array<Emote>();

		Emote pogchamp = new Emote("pogchamp.png",400, 500, 64, 64,50);
		Emote lul = new Emote("lul.png",400, 400, 64, 64,100);
		Emote sadge = new Emote("sadge.png",400, 300, 64, 52, 150);
		emotes.add(pogchamp);
		emotes.add(lul);
		emotes.add(sadge);
	}

	@Override
	public void render () {
		// Clear the screen with a dark blue color. The
		// arguments to clear are red, green, blue and alpha
		// component in the range [0, 1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices
		camera.update();

		// tell the SpriteBatch to render in the coordinate system specified by the camera
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the emotes
		batch.begin();
		for (Emote emote: emotes) {
			batch.draw(emote.tex, emote.x, emote.y);
		}
		batch.end();

		// Process user input
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			for (Emote emote: emotes) {
				emote.x -= emote.speed * Gdx.graphics.getDeltaTime();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			for (Emote emote: emotes) {
				emote.x += emote.speed * Gdx.graphics.getDeltaTime();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			for (Emote emote: emotes) {
				emote.y -= emote.speed * Gdx.graphics.getDeltaTime();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			for (Emote emote: emotes) {
				emote.y += emote.speed * Gdx.graphics.getDeltaTime();
			}
		}

		// Check boundaries
		for (Emote emote: emotes) {
			if (emote.x < 0) emote.x = 0;
			if (emote.x > 800 - 64) emote.x = 800 - 64;
			if (emote.y < 0) emote.y = 0;
			if (emote.y > 800 - 64) emote.y = 800 - 64;
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for (Iterator<Emote> iter = emotes.iterator(); iter.hasNext();){
			Emote emote = iter.next();
			emote.tex.dispose();
		}
	}
}
