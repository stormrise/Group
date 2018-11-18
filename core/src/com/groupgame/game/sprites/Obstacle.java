package com.groupgame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.groupgame.game.states.PlayState;

import java.util.Random;

public class Obstacle {
    private Texture obstacle;
    private Vector3 position;
    private Random rand;
    private Vector3 velocity;
    private int GRAVITY=-15;

    public Obstacle(float x,float y){

        obstacle=new Texture("obstacle_1.png");
        rand=new Random();
        position=new Vector3(x, y,0);
        velocity=new Vector3(0,0,0);

    }

    public void update(float dt){
        velocity.add(0,GRAVITY-(PlayState.count+1)/10,0);
        velocity.scl(dt);
        position.add(0,velocity.y,0);

        velocity.scl(1/dt);
    }


    public Texture getObstacle() {
        return obstacle;
    }

    public Vector3 getPosition() {
        return position;
    }
}
