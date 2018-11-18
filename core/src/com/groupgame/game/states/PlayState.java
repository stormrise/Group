package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.groupgame.game.GroupProject;
import com.groupgame.game.sprites.Obstacle;
import com.groupgame.game.sprites.Theft;

public class PlayState extends State {

    private Theft theft;
    private Obstacle leftObstacle;
    private Obstacle rightObstacle;
    public static int count =0;   //计数器，计点击屏幕次数
    private Texture bg;


    protected PlayState(GameStateManager gsm) {
        super(gsm);
        theft=new Theft(38,180);
        leftObstacle=new Obstacle(48,600);
        rightObstacle=new Obstacle(350,650);
        bg=new Texture("background.png");
        cam.setToOrtho(false,GroupProject.WIDTH+300,GroupProject.HEIGHT+200);  //Sets this camera to an orthographic projection
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(count%2==0){   //如果余数是0则向右移动；
                theft.setPosition(308,0);
            }else{   //否则向左移动；
                theft.setPosition(-308,0);
            }
            count++;
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        theft.update(dt);
        leftObstacle.update(dt);
        rightObstacle.update(dt);


    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined);//set orthographic projection
        sb.begin();
        sb.draw(bg,0,0,GroupProject.WIDTH,GroupProject.HEIGHT);
        sb.draw(leftObstacle.getObstacle(),leftObstacle.getPosition().x,leftObstacle.getPosition().y);
        sb.draw(rightObstacle.getObstacle(),rightObstacle.getPosition().x,rightObstacle.getPosition().y);
        sb.draw(theft.getTexture(),theft.getPosition().x,theft.getPosition().y);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
