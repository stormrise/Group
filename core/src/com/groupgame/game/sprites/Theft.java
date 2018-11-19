package com.groupgame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Timer;
import com.groupgame.game.GroupProject;
import com.groupgame.game.states.PlayState;

import java.util.Random;

public class Theft extends Sprite {
    private static final int GRAVITY=-15;
    private Vector2 position;
    private Vector2 velocity;
    private Texture theft;
    private TextureRegion region;
    private TextureRegion deadregion;
    private Box2D b2body;
    private Rectangle bounds;
    private Random r =new Random();
    private int Character;
    private Sound jumpSound;

    public Theft(int x, int y){
        position=new Vector2(x,y);
        velocity=new Vector2(0,0);

        if(r.nextBoolean()) {
            theft = new Texture("Idle0.png");
            deadregion = new TextureRegion(new Texture("Dead0.png"), 77, 80);//77,80
            Character=0;
        }else{
            theft = new Texture("Idle1.png");
            deadregion = new TextureRegion(new Texture("Dead1.png"), 77, 80);//77,80
            Character=1;
        }
        //theft = new Texture("theft.png");
        region = new TextureRegion(theft,45,80);

        bounds=new Rectangle(position.x,position.y+5,region.getRegionWidth(),region.getRegionHeight()-5);

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumpc.wav"));

    }

    public void update(float dt){
        //velocity.add(0,GRAVITY);
        velocity.scl(dt);

        if(PlayState.isInleft){
            if(position.x<GroupProject.WIDTH-GroupProject.BRICK*2-region.getRegionWidth()){
                position.add(velocity.x,0);
                rotate(3);

            }else{
                velocity.x=0;
                position.x=GroupProject.WIDTH-GroupProject.BRICK-region.getRegionWidth();
                //region.flip(true,false);
                rotate(0);
            }
        }else {
            if(position.x>GroupProject.BRICK){
                position.add(velocity.x,0);
                rotate(3);
            }else{
                velocity.x=0;
                position.x=GroupProject.BRICK;
                //region.flip(true,false);
                rotate(0);
            }
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }


    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return theft;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setPosition(int x, int y){
        position.add(x,y);
    }

    public void jump(Boolean bool){
        jumpSound.play(0.5f);
        if(bool){
            //setPosition(GroupProject.WIDTH-GroupProject.BRICK*2-region.getRegionWidth(),0);
            velocity.set(800,0);
            region.flip(true,false);
        }else{
            //setPosition(-(GroupProject.WIDTH-GroupProject.BRICK*2-region.getRegionWidth()),0);
            velocity.set(-800,0);
            region.flip(true,false);
        }
    }

    public void killed() {

//        switch (Character) {
//            case 0:
//                deadregion = new TextureRegion(new Texture("Dead0.png"), 77, 80);//77,80
//            break;
//            case 1:
//                deadregion = new TextureRegion(new Texture("Dead1.png"), 77, 80);//77,80
//            break;
//        }
        region=deadregion;
        bounds.setPosition(position.x,position.y);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose(){
        jumpSound.dispose();
        theft.dispose();
    }
}
