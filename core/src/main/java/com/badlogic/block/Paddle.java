package com.badlogic.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    int x;
    int y;
    int height;
    int width;

    int screenHeight;

    public Paddle(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void update(int mouseWorldX, int mouseWorldY) {
        screenHeight = Gdx.graphics.getHeight();
        this.x = mouseWorldX - (width / 2);
        this.y = mouseWorldY - (height / 2);
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }

}
