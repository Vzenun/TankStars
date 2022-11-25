package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;

import org.w3c.dom.css.Rect;

import java.io.Serializable;

public class PlayScreen implements Screen, Serializable {
    private TankStars game;
    private Texture showmain,playbutton;
    private Circle capture;
    public PlayScreen(TankStars game)
    {
        this.game=game;
        showmain=new Texture("playsc.png");
        capture=new Circle(35,720-659,65);
    }
    @Override
    public void show() {

    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 postotouch = new Vector3();
            postotouch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            cam.unproject(postotouch);

            if (capture.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new PauseScreen(game));
                dispose();
            }
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1, 0, 0, 1);
//        cam.update();
        game.batch.begin();
        game.batch.draw(showmain,0,0);
        game.batch.end();
        handleInput();
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
