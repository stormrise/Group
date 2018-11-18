package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.groupgame.game.GroupProject;
import com.groupgame.game.sprites.Obstacle;
import com.groupgame.game.sprites.Theft;

import java.util.Iterator;
import java.util.Random;

public class PlayState extends State {

    private Theft theft;

    public static int count =0;   //计数器，计点击屏幕次数
    private Texture bg;
    private Boolean isInleft;
    private BitmapFont font = new BitmapFont();
    private Array<Obstacle> obstacles;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        theft=new Theft(38,150);
        bg=new Texture("background.png");
        cam.setToOrtho(false,GroupProject.WIDTH,GroupProject.HEIGHT);  //Sets this camera to an orthographic projection

        obstacles = new Array<Obstacle>();
        Obstacle obstacle;
        for(int i=1;i<=16;i++) {
            obstacle = new Obstacle(i*(Obstacle.OBS_GAP+Obstacle.OBS_H));
            obstacles.add(obstacle);
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(count%2==0){   //如果余数是0则向右移动；
                isInleft=true;
                theft.jump(isInleft);
            }else{   //否则向左移动；
                isInleft=false;
                theft.jump(isInleft);
            }
            count++;
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        theft.update(dt);


        for(int i=0;i<obstacles.size;i++){
            Obstacle obstacle = obstacles.get(i);
            obstacle.update(dt);

            if(obstacle.getPosition().y<-200){
                obstacle.reposition(obstacle.getPosition().y+16*(Obstacle.OBS_GAP+Obstacle.OBS_H));
            }

            if(obstacle.collides(theft.getBounds())){
                gsm.set(new GameOverState(gsm));
                count=0;
            }
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.setProjectionMatrix(cam.combined);//set orthographic projection

        sb.begin();
        sb.draw(bg,0,0,GroupProject.WIDTH,GroupProject.HEIGHT);
        sb.draw(theft.getRegion(),theft.getPosition().x,theft.getPosition().y);

        font.draw(sb,String.valueOf(count),240,700);
        font.setColor(Color.BLACK);

        for(Obstacle obstacle :obstacles){
            sb.draw(obstacle.getObsRegion(),obstacle.getPosition().x,obstacle.getPosition().y);
        }

        sb.end();



    }

    @Override
    public void dispose() {
        theft.dispose();

        for(Obstacle obstacle : obstacles){
            obstacle.dispose();
        }


    }
}
