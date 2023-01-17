package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final MyGame game;

    private int screenWidth;
    private int screenHeight;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private OrthographicCamera camera;
    private Texture pogchampTexture;
    private Texture lulTexture;
    private Texture sadgeTexture;
    private Texture brickTexture;
    private Array<Emote> emotes;
    private Array<Ball> balls;
    private Array<Brick> bricks;
    private Brain brain;
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

        // Load textures
        pogchampTexture = new Texture("pogchamp.png");
        lulTexture = new Texture("lul.png");
        sadgeTexture = new Texture("sadge.png");
        brickTexture = new Texture("brick.png");

        // Create emotes
        emotes = new Array<Emote>();

        Emote pogchamp = new Emote(pogchampTexture,400, 500, 100, pogchampTexture.getWidth(), pogchampTexture.getHeight());
        Emote lul = new Emote(lulTexture,400, 400, 200, lulTexture.getWidth(), lulTexture.getHeight());
        Emote sadge = new Emote(sadgeTexture,400, 300, 300, sadgeTexture.getWidth(), sadgeTexture.getHeight());
        emotes.add(pogchamp);
        emotes.add(lul);
        emotes.add(sadge);

        // Create balls
        balls = new Array<Ball>();
        for (int i = 0; i < 10; i ++) {
            float radius = MathUtils.random(10, 100);
            float x = MathUtils.random(0, screenWidth - radius);
            float y = MathUtils.random(0, screenHeight - radius);
            float speed = MathUtils.random(150, 300);
            int colorChoice = MathUtils.random(1, 7);
            balls.add(new Ball(x, y, speed, radius, colorChoice));
        }

        // Create bricks
        bricks = new Array<Brick>();
        for (int i = 0; i < 5; i ++) {
            float x = MathUtils.random(0, screenWidth - 64);
            float y = MathUtils.random(0, screenHeight - 64);
            float speed = MathUtils.random(150, 300);
            bricks.add(new Brick(brickTexture, x, y, speed, brickTexture.getWidth(),brickTexture.getHeight()));
        }

        // Set up brain input processor
        brain = new Brain(emotes, balls, bricks);
        Gdx.input.setInputProcessor(brain);
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

        // Draw balls
        shape.begin(ShapeRenderer.ShapeType.Filled);
        for (Ball ball : balls) {
            shape.setColor(ball.getColor());
            shape.circle(ball.getX(), ball.getY(), ball.getRadius());
        }
        shape.end();

        // Update motion for all entities
        for (Emote emote: emotes) {
            emote.updateMotion();
        }
        for (Ball ball: balls) {
            ball.updateMotion();
        }
        for (Brick brick: bricks) {
            brick.updateMotion();
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
        pogchampTexture.dispose();
        lulTexture.dispose();
        sadgeTexture.dispose();
        brickTexture.dispose();
    }
}
