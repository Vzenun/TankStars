package com.tankstars.game.screen;

import com.badlogic.gdx.Screen;

import javax.swing.Spring;

public class Player implements Screen {

    private int x;
    private int y;

    private static Player player = null;
    private static Player getPlayer() {
        if(player == null) {
            player = new Player(1, 1);
        }
        return player;
    }

    public Player(int x, int y) {
        this.x = y;
        this.y = x;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
