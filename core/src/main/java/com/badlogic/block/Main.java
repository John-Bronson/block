package com.badlogic.block;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;
    private Ball ball;
    private Paddle paddle;
    Random r = new Random();

    private BitmapFont font;
    private SpriteBatch batch;

    ArrayList<Block> blocks = new ArrayList<>();

    @Override
    public void create() {
        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 20, 5, 5);
        paddle = new Paddle(100, 40, 20, 100);
        batch = new SpriteBatch();
        font = new BitmapFont();
        shape = new ShapeRenderer();

        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }


    @Override
    public void render() {
        // check for collisions
        ball.checkCollision(paddle);

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block block = blocks.get(i);

            if (ball.checkCollision(block)) {
                blocks.remove(i);
            }
        }

        int mouseWorldX = Gdx.input.getX();
        int mouseWorldY = Gdx.graphics.getHeight() - Gdx.input.getY();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ball.update();
        paddle.update(mouseWorldX, mouseWorldY);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);

        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block block = blocks.get(i);
            block.draw(shape);
        }

        shape.end();

        batch.begin();
        ball.drawDebug(font, batch);
        font.draw(batch, "y is" + Integer.toString(Gdx.input.getY()), 20, 40);
        batch.end();
    }
}
