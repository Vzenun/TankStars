package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.concurrent.RecursiveAction;

public class PauseScreen implements Screen, Serializable {

    private TankStars game;
    private Texture pssc;
    private Rectangle capture,capturemainmenu,mainmenu;
    public PauseScreen(TankStars game) {
        this.game = game;
        pssc = new Texture("PauseSc.png");
        capture = new Rectangle(540,720 - 580, 200, 100);
        capturemainmenu = new Rectangle(540, 720 - 180, 200, 100);
    }

    @Override
    public void show() {

    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 postotouch = new Vector3();
            postotouch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (capture.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
            else
            if(capturemainmenu.contains(postotouch.x,postotouch.y)){
                game.setScreen(new ExitScreen(game));
                dispose();
            }
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(pssc,0,0);
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
