package com.groupgame.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract  class State {
    protected OrthographicCamera cam;  //使用camera 定位
    protected Vector3 mouse; //位置描述
    protected GameStateManager gsm;  //管理游戏状态

    protected State(GameStateManager gsm){
        this.gsm=gsm;
        this.cam=new OrthographicCamera();
        this.mouse=new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);  //时间间隔
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();// 释放
}
