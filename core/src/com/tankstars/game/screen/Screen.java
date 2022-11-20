package com.tankstars.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Screen {
    protected OrthographicCamera cam;

    protected Vector3 mouse;

    protected GameScreenManager game;

    protected Screen(GameScreenManager game){
        this.game=game;
        this.cam=new OrthographicCamera();
        this.mouse=new Vector3();
        //constructs vector at 0,0,0
    }

    protected abstract void handleInput();
    // as abstract methods only need declaration no implementation
    public abstract void update(float dt);
    //here dt is the delta time difference between one frame rendered and next frame rendered
    public abstract void render(SpriteBatch ab);
    //spritebatch is basically a container for everything that we need to render on the screen
    //textures and the stuff like that
    public abstract void dispose();
}
