package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStars;
import java.awt.Rectangle;
import java.io.Serializable;

public class MenuScreen implements Screen,Serializable {
    //private OrthographicCamera cam;
    private TankStars game;
    private Rectangle newgamebtnbnd,resumegamebtnbnd,supportbtnbnd,exitgamebtnbnd;
    private Texture menuscreen,newgamebtn,resumegamebtn,supportbtn,exitgamebtn,key,menuscreen2,circlebtn,circlebtn2;
    public MenuScreen(TankStars game) {
//        cam=new OrthographicCamera();
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
        newgamebtnbnd=new Rectangle(867,720-470-newgamebtn.getHeight(),newgamebtn.getWidth(),newgamebtn.getHeight());
        resumegamebtnbnd=new Rectangle(867,720-350-resumegamebtn.getHeight(),resumegamebtn.getWidth(),resumegamebtn.getHeight());
        supportbtnbnd=new Rectangle(867,720-100-supportbtn.getHeight(),supportbtn.getWidth(),supportbtn.getHeight());
        exitgamebtnbnd=new Rectangle(867,720-220-exitgamebtn.getHeight(),exitgamebtn.getWidth(),exitgamebtn.getHeight());

    }

    @Override
    public void show() {

    }

    private void handleInput(){
        if(Gdx.input.justTouched()){
            Vector3 postotouch=new Vector3();
            postotouch.set(Gdx.input.getX(),Gdx.input.getY(),0);
//            cam.unproject(postotouch);

            if(newgamebtnbnd.contains(postotouch.x,postotouch.y)) {
                game.setScreen(new TankSelectScreen(game));
                dispose();
            }
            else if(resumegamebtnbnd.contains(postotouch.x,postotouch.y)) {

                game.setScreen(new SavedScreen(game));
                dispose();
            }
            else if(exitgamebtnbnd.contains(postotouch.x,postotouch.y)) {

                Gdx.app.exit();
                dispose();
            }
            else if(exitgamebtnbnd.contains(postotouch.x,postotouch.y)) {
                Gdx.app.exit();
                dispose();
            }

            //else if()
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
        game.batch.draw(newgamebtn,853,465);
        game.batch.draw(resumegamebtn,852,340);
        game.batch.draw(exitgamebtn,855,217);
        game.batch.draw(supportbtn,855,93);
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
        newgamebtn.dispose();
        resumegamebtn.dispose();
        supportbtn.dispose();
        exitgamebtn.dispose();
        key.dispose();
        menuscreen2.dispose();
        circlebtn.dispose();
        circlebtn2.dispose();
    }
}

