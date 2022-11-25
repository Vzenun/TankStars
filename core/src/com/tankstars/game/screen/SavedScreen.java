package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;
import java.awt.Rectangle;
import java.io.Serializable;

public class SavedScreen implements Screen, Serializable {
    //private OrthographicCamera cam;
    private TankStars game;
    private Texture menuscreen,svbtn1,svbtn2,svbtn3,supportbtn,exitgamebtn,key,menuscreen2,circlebtn,circlebtn2,svbtn4;
    private Rectangle mainmenu,game1btn,game2btn,game3btn;
    public SavedScreen(TankStars game) {
//        cam=new OrthographicCamera();
        this.game = game;
        menuscreen=new Texture("MenuScreen.png");
        key=new Texture("KEYS.png");
        menuscreen2=new Texture("menuscreen2.png");
        svbtn1=new Texture("savegames1.png");
        svbtn2=new Texture("savegames2.png");
        svbtn3=new Texture("savegames3.png");
        svbtn4=new Texture("savegamemainmenu.png");
        circlebtn=new Texture("circle.png");
        circlebtn2=new Texture("circlewhite.png");
        mainmenu=new Rectangle(867,720-100-svbtn4.getHeight(),svbtn4.getWidth(),svbtn4.getHeight());
        game1btn=new Rectangle(867,720-470-svbtn1.getHeight(),svbtn1.getWidth(),svbtn1.getHeight());
        game2btn=new Rectangle(867,720-350-svbtn2.getHeight(),svbtn2.getWidth(),svbtn2.getHeight());
        game3btn=new Rectangle(867,720-220-svbtn3.getHeight(),svbtn3.getWidth(),svbtn3.getHeight());
    }
    @Override
    public void show() {

    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 postotouch = new Vector3();
            postotouch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            cam.unproject(postotouch);

            if (mainmenu.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
            else
            if (game1btn.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
            else
            if (game2btn.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
            else
            if (game3btn.contains(postotouch.x, postotouch.y)) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(0, 0, 0, 0);
//        cam.update();
        game.batch.begin();
        game.batch.draw(menuscreen,0,0);
        game.batch.draw(key,0,0);
        game.batch.draw(menuscreen2,25,25);
        game.batch.draw(svbtn1,853,467);
        game.batch.draw(svbtn2,852,340);
        game.batch.draw(svbtn3,853,217);
        game.batch.draw(svbtn4,855,93);
        game.batch.draw(circlebtn2,1103,155);
        game.batch.draw(circlebtn,1101,137);
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
        menuscreen.dispose();
        menuscreen2.dispose();
        key.dispose();
        svbtn1.dispose();
        svbtn2.dispose();
        svbtn3.dispose();
        svbtn4.dispose();
        circlebtn.dispose();
        circlebtn2.dispose();
    }
}

