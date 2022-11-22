package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;

public class LoadScreen implements Screen {
    private TankStars game;
    private Texture loadingscreen;

    //private Texture menuscreen,newgamebtn,resumegamebtn,supportgamebtn,exitgamebtn;
    public LoadScreen(TankStars game) {
        this.game = game;
        this.loadingscreen = new Texture("loadgame.png");
    }

    @Override
    public void show() {
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.begin();
        game.batch.draw(loadingscreen, TankStars.WIDTH/2-loadingscreen.getWidth()/2, 0);
//		img.dispose();
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


