package com.groupgame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.groupgame.game.GroupProject;

import java.util.Random;

public class Theft {
    private static final int GRAVITY=-15;
    private Vector2 position;
    private Vector2 velocity;
    private Texture theft;
    private TextureRegion region;
    private Rectangle bounds;
    Random r =new Random();

    public Theft(int x, int y){
        position=new Vector2(x,y);
        velocity=new Vector2(0,0);

        if(r.nextBoolean()) {
            theft = new Texture("Idle0.png");
        }else{
            theft = new Texture("Idle1.png");
        }
        //theft = new Texture("theft.png");
        region = new TextureRegion(theft,45,80);

        bounds=new Rectangle(position.x,position.y,region.getRegionWidth(),region.getRegionHeight());

    }

    public void update(float dt){
        //velocity.add(0,GRAVITY,0);
        //velocity.scl(dt);
        //position.add(0,velocity.y,0);
        //velocity.scl(1/dt);
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

        if(bool){
            setPosition(GroupProject.WIDTH-GroupProject.BRICK*2-region.getRegionWidth(),0);
            region.flip(true,false);
        }else{
            setPosition(-(GroupProject.WIDTH-GroupProject.BRICK*2-region.getRegionWidth()),0);
            region.flip(true,false);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose(){
        theft.dispose();
    }
}
