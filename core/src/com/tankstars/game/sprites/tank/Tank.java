package com.tankstars.game.sprites.tank;

import com.badlogic.gdx.graphics.Texture;

public class Tank {

    private double health;
    private Texture tank;

    public void setHealth(double health) {
        this.health = health;
    }

    public void setTank(Texture tank) {
        this.tank = tank;
    }

    public double getHealth() {
        return health;
    }

    public Texture getTank() {
        return tank;
    }

    public Tank(Texture tank, double health){
        this.health=health;
        this.tank=tank;
    }

    public void walk(){

    }
    public void shoot(){

    }
    public void decompose(){
        tank.dispose();
    }
}
