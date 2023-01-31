package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public abstract class Entity {
    private Texture texture;
    private Color color;
    private float x, y, speed;
    private float width, height;
    private float dy, dx;
    private float mass;
    public Entity(float x, float y, float speed, Color color) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.color = color;
        this.dy = 0;
        this.dx = 0;
    }
    public Entity(float x, float y, float speed, Texture texture, float width, float height, float mass) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.mass = mass;
        this.dy = 0;
        this.dx = 0;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public float[] getCenter() {
        float[] result = new float[2];
        result[0] = (this.getX() + this.getWidth()) / 2f;
        result[1] = (this.getY() + this.getHeight()) / 2f;
        return result;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void updatePosition() {
        float delta = Gdx.graphics.getDeltaTime();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        this.setX(this.getX() + this.getDx() * delta);
        this.setY(this.getY() + this.getDy() * delta);
    }
    public abstract void movement();
}
