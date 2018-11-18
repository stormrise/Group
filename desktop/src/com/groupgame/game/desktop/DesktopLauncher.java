package com.groupgame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groupgame.game.GroupProject;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=GroupProject.WIDTH;
		config.height=GroupProject.HEIGHT;
		config.title=GroupProject.TITLE;
		new LwjglApplication(new GroupProject(), config);
	}
}
