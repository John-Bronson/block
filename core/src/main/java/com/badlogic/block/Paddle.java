package com.badlogic.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int height;
    int width;

    int cursorY;

    public Paddle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void update() {
        cursorY = Gdx.graphics.getHeight();

        this.x = Gdx.input.getX() - (width / 2);
        this.y = (cursorY - Gdx.input.getY() - (height / 2));
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }

}
