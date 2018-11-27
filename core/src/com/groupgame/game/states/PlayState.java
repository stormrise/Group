package com.groupgame.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.groupgame.game.NinjaJump;
import com.groupgame.game.sprites.Obstacle;
import com.groupgame.game.sprites.Ninja;

public class PlayState extends State {
    //枚举游戏状态是否在运行
    public enum State{
        Running, Paused
    }
    private static State state;
    private Ninja ninja;

    public static int count =0;   //计数器，计点击屏幕次数
    public static int nums =0;//掉落了多少个锯齿
    private Texture bg;
    public static Boolean isInleft;
    public static Boolean jumpover = false;
    private BitmapFont font = new BitmapFont();
    private Array<Obstacle> obstacles;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        state = State.Running;

        ninja =new Ninja(NinjaJump.BRICK,150);
        isInleft=true;
        bg=new Texture("background.png");
        cam.setToOrtho(false,NinjaJump.WIDTH,NinjaJump.HEIGHT);  //Sets this camera to an orthographic projection
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);// 防止字体模糊

        obstacles = new Array<Obstacle>();
        Obstacle obstacle;
        //16个能铺满两个屏幕，可以循环使用
        for(int i=1;i<=16;i++) {
            obstacle = new Obstacle(i*(Obstacle.OBS_GAP+Obstacle.OBS_H));
            obstacles.add(obstacle);
        }
        if(obstacles.get(0).getPosition().x<240){
            ninja.setPosition(480-38-38-45,0);
            ninja.getRegion().flip(true,false);
            isInleft = false;
        }

    }

    @Override
    protected void handleInput() {
        //不同状态下的手指点击效果不同
        switch (state) {
            case Running:
                //游戏状态下点击人物跳动
                if (Gdx.input.justTouched()) {
                    //调用Ninja.class方法
                    if (ninja.getPosition().x == NinjaJump.BRICK) {   //如果余数是0则向右移动；count%2==0//以前的方法
                        isInleft = true;
                        ninja.jump(isInleft);
                    } else if (ninja.getPosition().x == NinjaJump.WIDTH - NinjaJump.BRICK - ninja.getRegion().getRegionWidth()) {   //否则向左移动；
                        isInleft = false;
                        ninja.jump(isInleft);
                    }
                    //count++;
                }
                break;
            case Paused:
                //暂停下 点击恢复
                if (Gdx.input.justTouched()){
                    resume();
                    NinjaJump.outputLabel.setText("");
                }

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        switch(state){
            case Running:
                //update();

                ninja.update(dt);

                for(int i=0;i<obstacles.size;i++){
                    Obstacle obstacle = obstacles.get(i);
                    obstacle.update(dt);

                    if(obstacle.getPosition().y<-200){
                        obstacle.reposition(obstacle.getPosition().y+16*(Obstacle.OBS_GAP+Obstacle.OBS_H));
                        nums++;
                    }

                    if(obstacle.collides(ninja.getBounds())){
                        //isGameOver = true;
                        ninja.killed();

                        float t=Gdx.graphics.getDeltaTime();
                        do{
                            t=t+Gdx.graphics.getDeltaTime();
                        }while(t<30000);
                        if(t>30000)
                            gsm.set(new GameOverState(gsm));
                        dispose();
//                        try {
//                            Thread.sleep(2000);
//                            gsm.set(new GameOverState(gsm));
//                            dispose();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                    }
                }

                break;
            case Paused:
                //don't update
                break;
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.setProjectionMatrix(cam.combined);//set orthographic projection


        sb.begin();
        sb.draw(bg,0,0,NinjaJump.WIDTH,NinjaJump.HEIGHT);
        sb.draw(ninja.getRegion(), ninja.getPosition().x, ninja.getPosition().y);

        font.draw(sb,String.valueOf(count),240,700);
        font.setColor(Color.BLACK);
        font.getData().setScale(2.0f);

        //循环利用障碍物
        for(Obstacle obstacle :obstacles){
            sb.draw(obstacle.getObsRegion(),obstacle.getPosition().x,obstacle.getPosition().y);
        }

        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        ninja.dispose();
        font.dispose();
        for(Obstacle obstacle : obstacles){
            obstacle.dispose();
        }


    }


    public static void pause()
    {
        state = State.Paused;
    }

    public static void resume()
    {
        state = State.Running;
    }
}
