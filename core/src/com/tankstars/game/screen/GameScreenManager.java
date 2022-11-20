package com.tankstars.game.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Stack;

public class GameScreenManager {
    //This is the screen which basically manages the different screens by
    // popping one and pushing other like things here actually
    private Stack<Screen> screen;

    public GameScreenManager() {
        this.screen = new Stack<Screen>();
    }

    public void push(Screen screen){

        this.screen.push(screen);
    }

    public void pop(){
        //Actuaaly the thing is that every time we are going to pop out the state from the game state manager stack
        //we are not going to use it again therefore in order to avoid the memory leak we are  going to be disposing it
        this.screen.pop();
    }

    public void set(Screen screen){ //here we need to instantly pop the current state and push a state.

        this.screen.pop();
        this.screen.push(screen);
    }

    public void update(float dt){
        this.screen.peek(); // here dt is the change in the time between two renders
    }

    public void render(SpriteBatch batch){ // it renders everything to our screen

        this.screen.peek();
    }

}
