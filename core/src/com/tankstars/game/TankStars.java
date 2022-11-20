package com.tankstars.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.screen.GameScreenManager;
import com.tankstars.game.screen.MenuScreen;

public class TankStars extends Game {
	public static final int HEIGHT=720;
	public static final int WIDTH=1270;

	public SpriteBatch batch;
	Texture img;
	Texture bad;
	//GameScreenManager game;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("LoadGame.jpg");
		bad= new Texture("Abrams.png");
		//game = new GameScreenManager();
		//MenuScreen menu=new MenuScreen(game);
		//game.push(menu);
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
		/*Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img,0,0);
		batch.draw(bad, 0, -90);
//		img.dispose();
		batch.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
