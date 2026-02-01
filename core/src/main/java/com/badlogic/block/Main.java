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
    //    Ball ball = new Ball(Gdx.graphics.getWidth() / 2, 100, 40, 0, 0);
    private Ball ball;
    private Paddle paddle;
    Random r = new Random();

    private BitmapFont font;
    private SpriteBatch batch;


    @Override
    public void create() {
        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 40, 5, 5);
        paddle = new Paddle(100, 40, 20, 100);
        batch = new SpriteBatch();
        font = new BitmapFont();
        shape = new ShapeRenderer();
    }


    @Override
    public void render() {

        ball.checkCollision(paddle);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update();
        ball.draw(shape);

        paddle.update();
        paddle.draw(shape);

        shape.end();

        batch.begin();
        font.draw(batch, "y is" + Integer.toString(Gdx.input.getY()), 20, 40);
        batch.end();
    }
}
