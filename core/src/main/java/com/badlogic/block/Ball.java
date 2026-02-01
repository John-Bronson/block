package com.badlogic.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public void drawDebug(BitmapFont font, SpriteBatch batch) {
        font.draw(batch, "ySpeed is " + ySpeed, 20, 50);
        font.draw(batch, "xSpeed is " + xSpeed, 20, 60);
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
        int ballLeft = x - size;
        int ballRight = x + size;
        int ballBottom = y - size;
        int ballTop = y + size;

        int padLeft = paddle.x;
        int padRight = paddle.x + paddle.width;
        int padBottom = paddle.y;
        int padTop = paddle.y + paddle.height;

        // Early out if not overlapping
        boolean overlapping = collidesWith(ballLeft, ballRight, ballBottom, ballTop, padLeft, padRight, padBottom, padTop);

        int overlapX = Math.min(ballRight, padRight) - Math.max(ballLeft, padLeft);
        int overlapY = Math.min(ballTop, padTop) - Math.max(ballBottom, padBottom);

        if (overlapping) {
            if (overlapX < overlapY) {
                int padCenterX = paddle.x + paddle.width / 2;
                if (x < padCenterX) {
                    x -= (overlapX + 1);
                } else {
                    x += (overlapX + 1);
                }
                xSpeed = -xSpeed;
            } else if (overlapX > overlapY) {
                int patCenterY = paddle.y + paddle.height / 2;
                if (y < patCenterY) {
                    y -= (overlapY + 1);
                } else {
                    y += (overlapY + 1);
                }
                ySpeed = -ySpeed;
            }
        }

    }

    private static boolean collidesWith(int ballLeft, int ballRight, int ballBottom, int ballTop, int padLeft, int padRight, int padBottom, int padTop) {
        return ballLeft < padRight && ballRight > padLeft && ballBottom < padTop && ballTop > padBottom;
    }
}


