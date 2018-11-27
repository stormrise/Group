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
    public static final int OBS_GAP = 140;//每个间距
    public static final int OBS_H = 181;//障碍物总高
    public static boolean fake;

    private Texture obstacle;
    private TextureRegion obsRegion;
    private Rectangle bounds;
    private Vector2 position, velocity;
    private Random r =new Random();

    private int GRAVITY=-5;



    public Obstacle(float y){

        //随机障碍物
        if(r.nextBoolean()) {
            obstacle = new Texture("bar_left.png");
        }else{
            obstacle = new Texture("saw.png");
        }

        //obstacle=new Texture("bar_left.png");//30,181是图片的像素值
        obsRegion = new TextureRegion(obstacle,30,60*MathUtils.random(1,3));//MathUtils.random(10,181)//随机成3等份长度，

        //随机位置
        if(MathUtils.randomBoolean()){
            position=new Vector2(38,y);
        }else{
            position=new Vector2(480-38-30,y);
            obsRegion.flip(true,false);//右边翻转图像
        }
        //position=new Vector2(x, y);
        velocity=new Vector2(0,-250);
        bounds= new Rectangle(position.x,position.y,obsRegion.getRegionWidth(),obsRegion.getRegionHeight());

        fake=false;
    }

    public void update(float dt){
        //速度随游戏越来越快
        velocity.add(0,-0.001f-(PlayState.count+1)/300f-PlayState.nums/1000f);
        velocity.scl(dt);
        if(velocity.y<-600)
            velocity.y=-600;
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

    //重新放置，循环利用
    public void reposition(float y){
        position.set(position.x,y);

        bounds.setPosition(position.x,position.y);
    }

    //碰撞方法
    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose(){
        obstacle.dispose();

    }
}
