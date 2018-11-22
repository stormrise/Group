package com.groupgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.groupgame.game.states.GameStateManager;
import com.groupgame.game.states.MenuState;

public class GroupProject extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final int BRICK=38;

	public static final String TITLE="Group Game";
	private  GameStateManager gsm;

	private SpriteBatch batch;

	private Music music;

	private Stage stage;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);

		int row_height = Gdx.graphics.getWidth() / 12;
		int col_width = Gdx.graphics.getWidth() / 12;


//		Button button2=new TextButton("PAUSE",);
//		button2.setSize(col_width*4,row_height);
//		button2.setPosition(col_width*7,Gdx.graphics.getHeight()-row_height*3);
//		button2.addListener(new InputListener(){
//			@Override
//			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//
//			}
//			@Override
//			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//
//				return true;
//			}
//		});
//		stage.addActor(button2);


		batch = new SpriteBatch();
		gsm =new GameStateManager();
//		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
//        music.setLooping(true);
//        music.setVolume(0.1f);
//        music.play();
		Gdx.gl.glClearColor(0, 0, 0, 1);

		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //获取时间间隔
		gsm.render(batch);
		stage.act();
		stage.draw();


	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
