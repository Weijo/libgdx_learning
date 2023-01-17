package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
public class Emote extends Entity{
    private Texture texture;
    public Emote(Texture texture, float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }
}