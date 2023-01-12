package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
public class Emote {
    public Texture tex;
    public int speed;
    public float x;
    public float y;
    public float width;
    public float height;

    public Emote(String img, float x, float y, float width, float height, int speed) {
        this.tex = new Texture(img);
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
