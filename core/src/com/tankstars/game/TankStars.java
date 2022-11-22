package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tankstars.game.screen.LoadScreen;
import com.tankstars.game.screen.MenuScreen;

public class TankStars extends Game {
	public static final int HEIGHT=720;
	public static final int WIDTH=1270;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new LoadScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
