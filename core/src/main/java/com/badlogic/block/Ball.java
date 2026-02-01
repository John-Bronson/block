package com.badlogic.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        enum CollisionSide {
            TOP,
            BOTTOM,
            LEFT,
            RIGHT,
            NONE
        }

    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < this.size || x > Gdx.graphics.getWidth() - this.size) {
            xSpeed = -xSpeed;
        }
        if (y < this.size || y > Gdx.graphics.getHeight() - this.size) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle) {
        if (collidesWith(paddle)) {
            ySpeed = -ySpeed;
        } else {
        }
    }

    private boolean collidesWith(Paddle paddle) {
        if (x - size < paddle.x + paddle.width && x + size > paddle.x && y - size < paddle.y + paddle.height && y + size > paddle.y) {
            return true;
        }

        return false;
    }
}
