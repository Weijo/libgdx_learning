package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    private int x;
    private int y;
    private int radius;
    private int xSpeed;
    private int ySpeed;
    public Ball(int x, int y, int radius, int speed) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xSpeed = speed;
        this.ySpeed = speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getRadius() {
        return radius;
    }
    public int getXSpeed() {
        return xSpeed;
    }
    public int getYSpeed() {
        return ySpeed;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
}
