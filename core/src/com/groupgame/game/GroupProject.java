package com.groupgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.groupgame.game.states.GameStateManager;
import com.groupgame.game.states.MenuState;

public class GroupProject extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final int BRICK=38;

	public static final String TITLE="Group Game";
	private  GameStateManager gsm;

	private SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm =new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //获取时间间隔
		gsm.render(batch);


	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
