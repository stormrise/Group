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
import com.badlogic.gdx.utils.Timer;
import com.groupgame.game.GroupProject;
import com.groupgame.game.sprites.Obstacle;
import com.groupgame.game.sprites.Theft;

import java.util.Iterator;
import java.util.Random;

public class PlayState extends State {

    private Theft theft;

    public static int count =0;   //计数器，计点击屏幕次数
    public static int nums =0;
    private Texture bg;
    public static Boolean isInleft;
    public static Boolean jumpover = false;
    private BitmapFont font = new BitmapFont();
    private Array<Obstacle> obstacles;
    private float time = 1f;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        theft=new Theft(38,150);
        isInleft=true;
        bg=new Texture("background.png");
        cam.setToOrtho(false,GroupProject.WIDTH,GroupProject.HEIGHT);  //Sets this camera to an orthographic projection

        obstacles = new Array<Obstacle>();
        Obstacle obstacle;
        for(int i=1;i<=16;i++) {
            obstacle = new Obstacle(i*(Obstacle.OBS_GAP+Obstacle.OBS_H));
            obstacles.add(obstacle);
        }
        if(obstacles.get(0).getPosition().x<240){
            theft.setPosition(480-38-38-45,0);
            theft.getRegion().flip(true,false);
            isInleft = false;
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(theft.getPosition().x==GroupProject.BRICK){   //如果余数是0则向右移动；count%2==0
                isInleft=true;
                theft.jump(isInleft);
            }else if(theft.getPosition().x==GroupProject.WIDTH-GroupProject.BRICK-theft.getRegion().getRegionWidth()){   //否则向左移动；
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
                nums++;
            }

            if(obstacle.collides(theft.getBounds())){
                time -= 0.8f;
                theft.killed();

                    gsm.set(new GameOverState(gsm));

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
        bg.dispose();
        theft.dispose();
        font.dispose();
        for(Obstacle obstacle : obstacles){
            obstacle.dispose();
        }


    }
}
