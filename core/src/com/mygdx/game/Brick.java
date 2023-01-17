package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Brick extends Entity{
    private Texture texture;
    public Brick(Texture texture, float x, float y, float speed, float width, float height) {
        super(x, y, speed, width, height);
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }
}
