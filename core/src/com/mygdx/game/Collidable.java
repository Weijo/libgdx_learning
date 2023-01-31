package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Collidable extends Entity implements iCollidable{
    private Brain brain;
    private Sound sound;
    public Collidable(float x, float y, float speed, Texture texture, float width, float height, float mass, Brain brain, Sound sound) {
        super(x, y, speed, texture, width, height, mass);
        this.brain = brain;
        this.sound = sound;
    }
    @Override
    public void movement() {
    }
    @Override
    public boolean collidesWith(Collidable other) {
        return this.getX() < other.getX() + other.getWidth() &&
                this.getX() + this.getWidth() > other.getX() &&
                this.getY() < other.getY() + other.getHeight() &&
                this.getY() + this.getHeight() > other.getY();
    }
    @Override
    public void handleCollision(Collidable other) {
        float dx = other.getX() - this.getX();
        float dy = other.getY() - this.getY();
        float collVectorMag = (float) Math.sqrt(dx * dx + dy * dy);
        float collVectorX = dx / collVectorMag;
        float collVectorY = dy / collVectorMag;

        float relativeVelocity = other.getDx() * collVectorX + other.getDy() * collVectorY - this.getDx() * collVectorX - this.getDy() * collVectorY;
        if (relativeVelocity >= 0) return;

        float impulse = (float) (-(1 + 0.9) * relativeVelocity / (1 / this.getMass() + 1 / other.getMass()));
        float impulseX = impulse * collVectorX;
        float impulseY = impulse * collVectorY;

        this.setDx(this.getDx() - impulseX / this.getMass());
        this.setDy(this.getDy() - impulseY / this.getMass());
        other.setDx(other.getDx() + impulseX / other.getMass());
        other.setDy(other.getDy() + impulseY / other.getMass());
    }

    @Override
    public void reactToCollision() {
        sound.play();
    }


}
