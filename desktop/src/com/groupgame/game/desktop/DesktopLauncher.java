package com.groupgame.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groupgame.game.NinjaJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=NinjaJump.WIDTH;
		config.height=NinjaJump.HEIGHT;
		config.title=NinjaJump.TITLE;
		new LwjglApplication(new NinjaJump(), config);
	}
}
