package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class NonCollidable extends Entity {
    private Brain brain;
    public NonCollidable(float x, float y, float speed, Color color, Brain brain) {
        super(x, y, speed, color);
        this.brain = brain;
    }
    public NonCollidable(float x, float y, float speed, Texture texture, float width, float height, float mass, Brain brain) {
        super(x, y, speed, texture, width, height, mass);
        this.brain = brain;
    }
    @Override
    public void movement() {

    }
}
