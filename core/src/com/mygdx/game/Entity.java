package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Entity {
    private float x;
    private float y;
    private float speed;
    private float width;
    private float height;
    private float radius;
    private boolean leftMove;
    private boolean rightMove;
    private boolean upMove;
    private boolean downMove;

    public Entity(float x, float y, float speed, float radius) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.radius = radius;
    }
    public Entity(float x, float y, float speed, float width, float height) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getSpeed() {
        return speed;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getRadius() {
        return radius;
    }

    public boolean isLeftMove() {
        return leftMove;
    }
    public boolean isRightMove() {
        return rightMove;
    }
    public boolean isUpMove() {
        return upMove;
    }
    public boolean isDownMove() {
        return downMove;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public void setLeftMove(boolean leftMove) {
        this.leftMove = leftMove;
    }
    public void setRightMove(boolean rightMove) {
        this.rightMove = rightMove;
    }
    public void setUpMove(boolean upMove) {
        this.upMove = upMove;
    }
    public void setDownMove(boolean downMove) {
        this.downMove = downMove;
    }

    public void updateMotion() {
        float delta = Gdx.graphics.getDeltaTime();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (leftMove) {
            x -= speed * delta;
        }
        if (rightMove) {
            x += speed * delta;
        }
        if (upMove) {
            y += speed * delta;
        }
        if (downMove) {
            y -= speed * delta;
        }

        // Boundary check
        x = MathUtils.clamp(x, 0, screenWidth - width);
        y = MathUtils.clamp(y, 0, screenHeight - height);

    }
}
