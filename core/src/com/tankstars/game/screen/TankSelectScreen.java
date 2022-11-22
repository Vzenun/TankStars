package com.tankstars.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.tankstars.game.TankStars;

import java.awt.Rectangle;

import sun.awt.image.GifImageDecoder;

public class TankSelectScreen implements Screen {
    private TankStars game;
    Animation<TextureRegion> animation;
    private Texture screen2,blackscreen,abramsbtn,badgebtn,health,abrams,backbtn,playbutton,upgradebtn;
    private Rectangle backbtnbnd,playbtnbnd,upgradebtnbnd;
    public TankSelectScreen(TankStars game) {
        this.game = game;
        screen2 = new Texture("choosetank.jpg");
        blackscreen=new Texture("KEYS-2.png");
        abramsbtn=new Texture("Abramslabel.png");
        badgebtn=new Texture("badge.png");
        health=new Texture("health.png");
        abrams=new Texture("Abrams.png");
        backbtn=new Texture("backbtn.png");
        playbutton=new Texture("play2.png");
        upgradebtn=new Texture("upgrade.png");
        backbtnbnd=new Rectangle(30,720-30-backbtn.getHeight(),backbtn.getWidth(),backbtn.getHeight());
        playbtnbnd=new Rectangle(888,720-160-playbutton.getHeight(),playbutton.getWidth(),playbutton.getHeight());
        upgradebtnbnd=new Rectangle(888,720-35-upgradebtn.getHeight(),upgradebtn.getWidth(),upgradebtn.getHeight());
    }

    @Override
    public void show() {

    }

    private void handleInput(){
        if(Gdx.input.justTouched()){
            Vector3 postotouch=new Vector3();
            postotouch.set(Gdx.input.getX(),Gdx.input.getY(),0);
//            cam.unproject(postotouch);

            if(backbtnbnd.contains(postotouch.x,postotouch.y)) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
            else
            if(playbtnbnd.contains(postotouch.x,postotouch.y))
            {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(screen2,0,0);
        game.batch.draw(blackscreen,0,0);
        game.batch.draw(abramsbtn,145,565);
        game.batch.draw(health,310,497);
        game.batch.draw(badgebtn,246,492);
        game.batch.draw(abrams,90,20);
        game.batch.draw(backbtn,30,30);
        game.batch.draw(playbutton,888,160);
        game.batch.draw(upgradebtn,888,35);
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
