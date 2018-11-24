package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.groupgame.game.GroupProject;


public class GameOverState extends State{
    private Texture background;
    private Texture gameover;


    private BitmapFont font = new BitmapFont();

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GroupProject.WIDTH, GroupProject.HEIGHT);
        background = new Texture("background.png");
        gameover= new Texture("gameover.png");
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);// 防止字体模糊


    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            PlayState.count=0;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        font.draw(sb,"Touch Anywhere To Restart",cam.position.x- gameover.getWidth() / 2, cam.position.y-gameover.getHeight());
        font.draw(sb,"Score:"+String.valueOf(PlayState.count-1<0 ? 0 : PlayState.count-1),cam.position.x- gameover.getWidth() / 2, cam.position.y-gameover.getHeight()-50);
        font.setColor(Color.BLACK);
        font.getData().setScale(1.5f);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        font.dispose();
    }
}
