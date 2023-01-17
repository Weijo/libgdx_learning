package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class Ball extends Entity{
    Color color;
    public Ball(float x, float y, float speed, float radius, int colorChoice) {
        super(x, y, speed, radius);
        switch (colorChoice) {
            case 1:
                this.color = Color.RED;
                break;
            case 2:
                this.color = Color.BLUE;
                break;
            case 3:
                this.color = Color.GREEN;
                break;
            case 4:
                this.color = Color.YELLOW;
                break;
            case 5:
                this.color = Color.PURPLE;
                break;
            case 6:
                this.color = Color.VIOLET;
                break;
            case 7:
                this.color = Color.ORANGE;
                break;
            default:
                this.color = Color.WHITE;
                break;
        }
    }
    public Color getColor() {
        return color;
    }

    @Override
    public void updateMotion() {
        float delta = Gdx.graphics.getDeltaTime();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        if (this.isLeftMove()) {
            this.setX(this.getX() - this.getSpeed() * delta);
        }
        if (this.isRightMove()) {
            this.setX(this.getX() + this.getSpeed() * delta);
        }
        if (this.isUpMove()) {
            this.setY(this.getY() + this.getSpeed() * delta);
        }
        if (this.isDownMove()) {
            this.setY(this.getY() - this.getSpeed() * delta);
        }

        // ball boundary check
        this.setX(MathUtils.clamp(this.getX(), this.getRadius(), screenWidth - this.getRadius()));
        this.setY(MathUtils.clamp(this.getY(), this.getRadius(), screenHeight - this.getRadius()));

    }
}
