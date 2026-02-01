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

    enum CollisionSide {
        VERT, HORIZ, NONE
    }

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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
        if (collidesWith(paddle) == CollisionSide.VERT) {
            ySpeed = -ySpeed;
        } else {
        }
    }

    private CollisionSide collidesWith(Paddle paddle) {
        int ballLeft = x - size;
        int ballRight = x + size;
        int ballBottom = y - size;
        int ballTop = y + size;

        int padLeft = paddle.x;
        int padRight = paddle.x + paddle.width;
        int padBottom = paddle.y;
        int padTop = paddle.y + paddle.height;


        if (
            ballLeft < padRight
                && ballRight > padLeft
                && ballBottom < padTop
                && ballTop > padBottom
        ) {
            return CollisionSide.VERT;
        }


        return CollisionSide.NONE;
    }
}
