package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
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
    private Brain brain;
    private OrthographicCamera camera;
    private Texture pogchampTexture;
    private Texture lulTexture;
    private Texture sadgeTexture;
    private Texture brickTexture;
    private Sound bonk;
    private Array<Entity> entities;
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

        // Load sound
        bonk = Gdx.audio.newSound(Gdx.files.internal("bonk.mp3"));

        // Create Brain
        brain = new Brain();

        // Create entity array
        entities = new Array<Entity>();

        // Create emotes
        Emote pogchamp = new Emote(pogchampTexture,400, 500, 100, pogchampTexture.getWidth(), pogchampTexture.getHeight(), 50, brain, bonk);
        Emote lul = new Emote(lulTexture,400, 400, 200, lulTexture.getWidth(), lulTexture.getHeight(), 50, brain, bonk);
        Emote sadge = new Emote(sadgeTexture,400, 300, 300, sadgeTexture.getWidth(), sadgeTexture.getHeight(), 50, brain, bonk);

        entities.add(pogchamp);
        entities.add(lul);
        entities.add(sadge);

        // Create balls
        for (int i = 0; i < 10; i ++) {
            float radius = MathUtils.random(10, 100);
            float x = MathUtils.random(0, screenWidth - radius);
            float y = MathUtils.random(0, screenHeight - radius);
            float speed = MathUtils.random(150, 300);
            int colorChoice = MathUtils.random(1, 7);
            Color colour;
            switch (colorChoice) {
                case 1:
                    colour = Color.RED;
                    break;
                case 2:
                    colour = Color.BLUE;
                    break;
                case 3:
                    colour = Color.GREEN;
                    break;
                case 4:
                    colour = Color.YELLOW;
                    break;
                case 5:
                    colour = Color.PURPLE;
                    break;
                case 6:
                    colour = Color.VIOLET;
                    break;
                case 7:
                    colour = Color.ORANGE;
                    break;
                default:
                    colour = Color.WHITE;
                    break;
            }
            Ball ball = new Ball(x, y, speed, radius, colour, brain);
            entities.add(ball);
        }

        // Create bricks
//        bricks = new Array<Brick>();
        for (int i = 0; i < 5; i ++) {
            float x = MathUtils.random(0, screenWidth - 64);
            float y = MathUtils.random(0, screenHeight - 64);
            float speed = MathUtils.random(150, 300);
            Brick brick = new Brick(brickTexture, x, y, speed, brickTexture.getWidth(),brickTexture.getHeight(), 50, brain, bonk);
            entities.add(brick);
        }
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

        for (Entity entity: entities) {
            if (entity instanceof Collidable) {
                batch.begin();
                Collidable collidable = (Collidable) entity;
                batch.draw(collidable.getTexture(), collidable.getX(), collidable.getY(), collidable.getWidth(), collidable.getHeight());
                batch.end();
            }
            else if (entity instanceof Ball) {
                Ball ball = (Ball) entity;
                shape.begin(ShapeRenderer.ShapeType.Filled);
                shape.setColor(ball.getColor());
                shape.circle(ball.getX(), ball.getY(), ball.getRadius());
                shape.end();
            }
        }


        // Update motion for all entities
        for (int i = 0; i < entities.size; i++) {
            Entity entity = entities.get(i);
            entity.updatePosition();
            entity.movement();

            if (entity instanceof Collidable) {
                Collidable collidable = (Collidable) entity;
                for (Entity other: entities) {
                    if (other instanceof Collidable) {
                        Collidable other1 = (Collidable) other;
                        if (other1 == collidable) {
                            continue;
                        }

                        if (collidable.collidesWith(other1)) {
                            collidable.handleCollision(other1);
                            collidable.reactToCollision();
                        }
                    }
                }
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
        pogchampTexture.dispose();
        lulTexture.dispose();
        sadgeTexture.dispose();
        brickTexture.dispose();
        bonk.dispose();
    }
}
