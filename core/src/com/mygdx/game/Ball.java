package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class Ball extends NonCollidable {
    private float radius;
    public Ball(float x, float y, float speed, float radius, Color color, Brain brain) {
        super(x, y, speed, color, brain);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.setDx(-this.getSpeed());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.setDx(this.getSpeed());
        } else {
            this.setDx(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.setDy(this.getSpeed());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.setDy(-this.getSpeed());
        } else {
            this.setDy(0);
        }
    }
}
