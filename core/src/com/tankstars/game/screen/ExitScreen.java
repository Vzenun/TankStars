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

public class ExitScreen implements Screen, Serializable {
    private TankStars game;
    private Texture jumptomain,backscreen;
    private Rectangle yes,no;
    public ExitScreen(TankStars game)
    {
        this.game=game;
        jumptomain=new Texture("exitornot.png");
        backscreen=new Texture("PauseSc.png");
        yes=new Rectangle(540,720-402,100,100);
        no=new Rectangle(540,720-262,100,100);
    }
    @Override
    public void show() {

    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 postotouch = new Vector3();
            postotouch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//            cam.unproject(postotouch);

            if ((yes.contains(postotouch.x, postotouch.y))) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
            else
            if(no.contains(postotouch.x, postotouch.y)){
                game.setScreen(new MenuScreen(game));
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
        game.batch.draw(backscreen,0,0);
        game.batch.draw(jumptomain,320,100);
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
