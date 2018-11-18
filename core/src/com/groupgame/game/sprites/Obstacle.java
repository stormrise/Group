package com.groupgame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.groupgame.game.states.PlayState;

import java.util.Random;

public class Obstacle {
    private Texture obstacle;
    private TextureRegion obsRegion;
    private Rectangle bounds;
    private Vector2 position, velocity;
    private Random rand;
    private int GRAVITY=-15;



    public Obstacle(float x,float y){

        obstacle=new Texture("bar_left.png");//30,181
        rand=new Random();
        position=new Vector2(x, y);
        velocity=new Vector2(0,0);
        obsRegion = new TextureRegion(obstacle,30,rand.nextInt(181));
        bounds= new Rectangle(position.x,position.y,obsRegion.getRegionWidth(),obsRegion.getRegionHeight());

    }

    public void update(float dt){
        velocity.add(0,GRAVITY-(PlayState.count+1)/10);
        velocity.scl(dt);
        position.add(0,velocity.y);

        velocity.scl(1/dt);
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

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose(){
        obstacle.dispose();

    }
}
