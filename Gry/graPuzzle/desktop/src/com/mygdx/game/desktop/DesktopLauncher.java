package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SimpleJigsawPuzzleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		config.height = SimpleJigsawPuzzleGame.WINDOWS_HEIGHT;
		config.width = SimpleJigsawPuzzleGame.WINDOWS_WIDTH;
		config.title = "Gra Puzzle";

		new LwjglApplication(new SimpleJigsawPuzzleGame(), config);
	}
}
