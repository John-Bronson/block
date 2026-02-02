package com.badlogic.block;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block implements Collidable {
    int x, y, width, height;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    @Override
    public int getWidth() { return width; }

    @Override
    public int getHeight() { return height; }


    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, width, height);
    }
}
