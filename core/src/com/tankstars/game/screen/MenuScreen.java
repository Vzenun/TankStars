package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;

public class MenuScreen implements Screen {
    private TankStars game;
    private Texture menuscreen,newgamebtn,resumegamebtn,supportbtn,exitgamebtn,key,menuscreen2,circlebtn,circlebtn2;
    public MenuScreen(TankStars game) {
        this.game = game;
        menuscreen=new Texture("MenuScreen.png");
        key=new Texture("KEYS.png");
        menuscreen2=new Texture("menuscreen2.png");
        newgamebtn=new Texture("playbtn.png");
        resumegamebtn=new Texture("resumegame.png");
        exitgamebtn=new Texture("exitgame.png");
        supportbtn=new Texture("supportbtn.png");
        circlebtn=new Texture("circle.png");
        circlebtn2=new Texture("circlewhite.png");
        /*resumegamebtn=new Texture("menuscreen.png");
        newgamebtn=new Texture("menuscreen.png");
        supportbtn=new Texture("menuscreen.png");
        exitgamebtn=new Texture("menuscreen.png");*/
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(menuscreen,0,0);
        game.batch.draw(key,0,0);
        game.batch.draw(menuscreen2,25,25);
        game.batch.draw(newgamebtn,867,470);
        game.batch.draw(resumegamebtn,867,350);
        game.batch.draw(exitgamebtn,867,220);
        game.batch.draw(supportbtn,867,100);
        game.batch.draw(circlebtn2,1103,155);
        game.batch.draw(circlebtn,1101,137);
        game.batch.end();
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

