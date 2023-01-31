package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }
    public void dispose() {
        batch.dispose();
        font.dispose();
        this.screen.dispose();
    }
}
