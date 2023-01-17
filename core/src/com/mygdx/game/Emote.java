package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Emote {
    private Texture texture;
    private float x;
    private float y;
    private float width;
    private float height;
    private int speed;
    public Emote(String img, float x, float y, int speed) {
        this.texture = new Texture(img);
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}