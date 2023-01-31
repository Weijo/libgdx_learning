package com.mygdx.game;

public interface iCollidable {
    public boolean collidesWith(Collidable other);
    public void handleCollision(Collidable other);
    public void reactToCollision();
}
