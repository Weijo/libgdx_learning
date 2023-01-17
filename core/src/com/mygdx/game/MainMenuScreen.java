package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final MyGame game;
    private OrthographicCamera camera;

    private int screenWidth;
    private int screenHeight;
    public MainMenuScreen(final MyGame game) {
        this.game = game;
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to some game? ", screenWidth / 2 - 175, screenHeight / 2 + 125);
        game.font.draw(game.batch, "WASD to control balls ", screenWidth / 2 - 175, screenHeight / 2 + 50);
        game.font.draw(game.batch, "Arrow keys to control emotes ", screenWidth / 2 - 175, screenHeight / 2);
        game.font.draw(game.batch, "IJKL  to control bricks ", screenWidth / 2 - 175, screenHeight / 2 - 50);
        game.font.draw(game.batch, "Press any key to continue", screenWidth / 2 - 175, screenHeight / 2 - 125);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
