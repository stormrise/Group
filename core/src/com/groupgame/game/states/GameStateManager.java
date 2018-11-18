package com.groupgame.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states; //用堆栈来存储状态

    public GameStateManager(){
        states=new Stack<State>();

    }

    public void push(State state){  //插入状态
        states.push(state);

    }

    public void pop(){   //取出一个状态
        states.pop();
    }

    public void set (State state){  //取出和插入
        states.pop();
        states.push(state);
    }

    public void update(float dt){   //更新栈顶
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){   //栈顶子图
        states.peek().render(sb);
    }




}
