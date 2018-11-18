package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.groupgame.game.GroupProject;


public class GameOverState extends State{
    private Texture background;
    private Texture gameover;


    BitmapFont font = new BitmapFont();

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, GroupProject.WIDTH, GroupProject.HEIGHT);
        background = new Texture("background.png");
        gameover= new Texture("gameover.png");



    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(gameover, cam.position.x - gameover.getWidth() / 2, cam.position.y);
        font.draw(sb,"Touch Anywhere To Restart",cam.position.x- gameover.getWidth() / 2, cam.position.y-gameover.getHeight());
        font.draw(sb,"Score:"+String.valueOf(PlayState.count),cam.position.x- gameover.getWidth() / 2, cam.position.y-gameover.getHeight()-50);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}
