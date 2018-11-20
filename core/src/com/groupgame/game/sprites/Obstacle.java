package com.groupgame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.groupgame.game.states.PlayState;

import java.util.Random;

public class Obstacle extends Sprite {
    public static final int OBS_GAP = 140;
    public static final int OBS_H = 181;

    private Texture obstacle;
    private TextureRegion obsRegion;
    private Rectangle bounds;
    private Vector2 position, velocity;

    private int GRAVITY=-5;



    public Obstacle(float y){

        obstacle=new Texture("bar_left.png");//30,181
        obsRegion = new TextureRegion(obstacle,30,MathUtils.random(60,181));//MathUtils.random(10,181)

        if(MathUtils.randomBoolean()){
            position=new Vector2(38,y);
        }else{
            position=new Vector2(480-38-30,y);
            obsRegion.flip(true,false);
        }
        //position=new Vector2(x, y);
        velocity=new Vector2(0,-200);
        bounds= new Rectangle(position.x,position.y,obsRegion.getRegionWidth(),obsRegion.getRegionHeight());

    }

    public void update(float dt){
        velocity.add(0,-0.01f-(PlayState.count+1)/50f+PlayState.nums/200f);
        velocity.scl(dt);
        position.add(0,velocity.y);

        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }


    public Texture getObstacle() {
        return obstacle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getObsRegion() {
        return obsRegion;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void reposition(float y){
        position.set(position.x,y);

        bounds.setPosition(position.x,position.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose(){
        obstacle.dispose();

    }
}
