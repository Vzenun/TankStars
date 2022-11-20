package com.tankstars.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tankstars.game.TankStars;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(TankStars.WIDTH, TankStars.HEIGHT);
		config.useVsync(true);
		config.setForegroundFPS(60);
		config.setTitle("TankStars");
		new Lwjgl3Application(new TankStars(), config);
	}
}
