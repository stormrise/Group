package com.groupgame.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.groupgame.game.NinjaJump;
import com.groupgame.game.states.PlayState;

import java.util.Random;

public class Ninja extends Sprite {
    private static final int GRAVITY=-15;
    private Vector2 position;
    private Vector2 velocity;
    private Texture theft;
    private TextureRegion region;
    private Rectangle bounds;
    private Random r =new Random();
    private int Character;
    private Sound jumpSound;


    public Ninja(int x, int y){
        position=new Vector2(x,y);
        velocity=new Vector2(0,0);

        //随机选取角色
        if(r.nextBoolean()) {
            theft = new Texture("Idle0.png");
            Character=0;
        }else{
            theft = new Texture("Idle1.png");
            Character=1;
        }
        //theft = new Texture("theft.png");
        region = new TextureRegion(theft,45,80);//45，80是图片的像素值

        bounds=new Rectangle(position.x,position.y,region.getRegionWidth(),region.getRegionHeight());

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumpc.wav"));

    }

    public void update(float dt){
        //velocity.add(0,GRAVITY);
        velocity.scl(dt);

        //判断不同方向时，跳的方向不同，跳到了之后停止在终点
        if(PlayState.isInleft){
            if(position.x<NinjaJump.WIDTH-NinjaJump.BRICK*2-region.getRegionWidth()){
                position.add(velocity.x,0);


            }else{
                velocity.x=0;
                position.x=NinjaJump.WIDTH-NinjaJump.BRICK-region.getRegionWidth();

            }
        }else {
            if(position.x>NinjaJump.BRICK){
                position.add(velocity.x,0);

            }else{
                velocity.x=0;
                position.x=NinjaJump.BRICK;

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

    //跳跃方法
    public void jump(Boolean bool){
        PlayState.jumpover=false;

        jumpSound.play(0.8f);
        if(bool){
            velocity.set(600+(PlayState.count+1)/100f+PlayState.nums/500f,0);//随游戏变快，和obstacle保持一致

        }else{
            velocity.set(-600-(PlayState.count+1)/100f-PlayState.nums/500f,0);

        }
        PlayState.count++;
        rotate(0);
        region.flip(true,false);//跳跃后翻转图片
    }

    public void killed() {
        //死亡时展示死亡的图片
        switch (Character) {
            case 0:
                theft=new Texture("Dead0.png");//77,80
            break;
            case 1:
                theft=new Texture("Dead1.png");//77,80
            break;
        }

        region.setRegion(theft);
        rotate(3);
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
