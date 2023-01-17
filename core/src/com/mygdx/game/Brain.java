package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;

public class Brain implements InputProcessor {
    private Array<Emote> emotes;
    private Array<Ball> balls;
    private Array<Brick> bricks;

    public Brain(Array<Emote> emotes, Array<Ball> balls, Array<Brick> bricks) {
        this.emotes = emotes;
        this.balls = balls;
        this.bricks = bricks;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            // Emotes inputs
            case Input.Keys.LEFT:
                for (Emote emote: emotes) {
                    emote.setLeftMove(true);
                }
                break;
            case Input.Keys.RIGHT:
                for (Emote emote: emotes) {
                    emote.setRightMove(true);
                }
                break;
            case Input.Keys.UP:
                for (Emote emote: emotes) {
                    emote.setUpMove(true);
                }
                break;
            case Input.Keys.DOWN:
                for (Emote emote: emotes) {
                    emote.setDownMove(true);
                }
                break;

            // Ball inputs
            case Input.Keys.A:
                for (Ball ball: balls) {
                    ball.setLeftMove(true);
                }
                break;
            case Input.Keys.D:
                for (Ball ball: balls) {
                    ball.setRightMove(true);
                }
                break;
            case Input.Keys.W:
                for (Ball ball: balls) {
                    ball.setUpMove(true);
                }
                break;
            case Input.Keys.S:
                for (Ball ball: balls) {
                    ball.setDownMove(true);
                }
                break;

            // Brick inputs
            case Input.Keys.J:
                for (Brick brick: bricks) {
                    brick.setLeftMove(true);
                }
                break;
            case Input.Keys.L:
                for (Brick brick: bricks) {
                    brick.setRightMove(true);
                }
                break;
            case Input.Keys.I:
                for (Brick brick: bricks) {
                    brick.setUpMove(true);
                }
                break;
            case Input.Keys.K:
                for (Brick brick: bricks) {
                    brick.setDownMove(true);
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            // Emotes inputs
            case Input.Keys.LEFT:
                for (Emote emote: emotes) {
                    emote.setLeftMove(false);
                }
                break;
            case Input.Keys.RIGHT:
                for (Emote emote: emotes) {
                    emote.setRightMove(false);
                }
                break;
            case Input.Keys.UP:
                for (Emote emote: emotes) {
                    emote.setUpMove(false);
                }
                break;
            case Input.Keys.DOWN:
                for (Emote emote: emotes) {
                    emote.setDownMove(false);
                }
                break;

            // Ball inputs
            case Input.Keys.A:
                for (Ball ball: balls) {
                    ball.setLeftMove(false);
                }
                break;
            case Input.Keys.D:
                for (Ball ball: balls) {
                    ball.setRightMove(false);
                }
                break;
            case Input.Keys.W:
                for (Ball ball: balls) {
                    ball.setUpMove(false);
                }
                break;
            case Input.Keys.S:
                for (Ball ball: balls) {
                    ball.setDownMove(false);
                }
                break;

            // Brick inputs
            case Input.Keys.J:
                for (Brick brick: bricks) {
                    brick.setLeftMove(false);
                }
                break;
            case Input.Keys.L:
                for (Brick brick: bricks) {
                    brick.setRightMove(false);
                }
                break;
            case Input.Keys.I:
                for (Brick brick: bricks) {
                    brick.setUpMove(false);
                }
                break;
            case Input.Keys.K:
                for (Brick brick: bricks) {
                    brick.setDownMove(false);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
