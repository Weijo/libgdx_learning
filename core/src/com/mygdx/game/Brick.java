package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Brick extends Collidable {
    public Brick(Texture texture, float x, float y, float speed, float width, float height, float mass, Brain brain, Sound sound) {
        super(x, y, speed, texture, width, height, mass, brain, sound);
    }

    @Override
    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            this.setDx(-this.getSpeed());
        } else if (Gdx.input.isKeyPressed(Input.Keys.L)) {
            this.setDx(this.getSpeed());
        } else {
            this.setDx(0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            this.setDy(this.getSpeed());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.K)) {
            this.setDy(-this.getSpeed());
        } else {
            this.setDy(0);
        }
    }
}
