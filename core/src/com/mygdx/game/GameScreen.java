package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    final MyGame game;

    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private OrthographicCamera camera;
    private Array<Emote> emotes;
    private Array<Ball> balls;
    private Array<Brick> bricks;
    private long lastBrickTime;
    private Random r;
    public GameScreen(final MyGame game) {
        this.game = game;

        // Set screen width and height
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // Setup camera, SpriteBatch, ShapeRenderer
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        batch = new SpriteBatch();
        shape = new ShapeRenderer();

        // Create emotes
        emotes = new Array<Emote>();

        Emote pogchamp = new Emote("pogchamp.png",400, 500, 100);
        Emote lul = new Emote("lul.png",400, 400, 200);
        Emote sadge = new Emote("sadge.png",400, 300, 300);
        emotes.add(pogchamp);
        emotes.add(lul);
        emotes.add(sadge);

        // Create balls
        r = new Random();
        balls = new Array<Ball>();
        for (int i = 0; i < 10; i ++) {
            balls.add(new Ball(r.nextInt(screenWidth),
                    r.nextInt(screenHeight),
                    r.nextInt(100),
                    r.nextInt(20)));
        }

        // Create first brick
        bricks = new Array<Brick>();
        spawnBrick();
    }

    private void spawnBrick() {
        int x = MathUtils.random(0, screenWidth - 64);
        int y = screenHeight;
        int speed = MathUtils.random(100, 200);
        Brick brick = new Brick("brick.png", x, y, speed);
        bricks.add(brick);
        lastBrickTime = TimeUtils.nanoTime();
    }

    @Override
    public void render (float delta) {
        // Clear the screen with a dark blue color. The
        // arguments to clear are red, green, blue and alpha
        // component in the range [0, 1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices
        camera.update();

        // tell the SpriteBatch to render in the coordinate system specified by the camera
        batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the emotes
        batch.begin();
        for (Emote emote: emotes) {
            batch.draw(emote.getTexture(), emote.getX(), emote.getY(), emote.getWidth(), emote.getHeight());
        }
        for (Brick brick : bricks) {
            batch.draw(brick.getTexture(), brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        }
        batch.end();

        // Update emote positions
        for (Emote emote: emotes) {
            // Updating position
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                emote.setX(emote.getX() - (emote.getSpeed() * delta));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                emote.setX(emote.getX() + (emote.getSpeed() * delta));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                emote.setY(emote.getY() + (emote.getSpeed() * delta));
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                emote.setY(emote.getY() - (emote.getSpeed() * delta));
            }

            // Boundary checking
            emote.setX(MathUtils.clamp(emote.getX(), 0, screenWidth - emote.getWidth()));
            emote.setY(MathUtils.clamp(emote.getY(), 0, screenHeight - emote.getHeight()));
        }

        // Update ball positions
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball ball : balls) {
            ball.setX(ball.getX() + ball.getXSpeed());
            ball.setY(ball.getY() + ball.getYSpeed());

            if (ball.getX() < 0 || ball.getY() > screenWidth) {
                ball.setxSpeed(-ball.getXSpeed());
            }
            if (ball.getY() < 0 || ball.getY() > screenHeight) {
                ball.setySpeed(-ball.getYSpeed());
            }

            shape.circle(ball.getX(), ball.getY(), ball.getRadius());
        }
        shape.end();

        // Spawn bricks
        if (TimeUtils.nanoTime() - lastBrickTime > 1000000000) spawnBrick();

        for (Iterator<Brick> iter = bricks.iterator(); iter.hasNext();) {
            Brick brick = iter.next();
            brick.setY(brick.getY() - (brick.getSpeed() * delta));
            if (brick.getY() + brick.getHeight() < 0) {
                brick.getTexture().dispose();
                iter.remove();
            }
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
    public void dispose () {
        batch.dispose();
        for (Emote emote : emotes) {
            emote.getTexture().dispose();
        }
        for (Brick brick : bricks) {
            brick.getTexture().dispose();
        }
    }
}
