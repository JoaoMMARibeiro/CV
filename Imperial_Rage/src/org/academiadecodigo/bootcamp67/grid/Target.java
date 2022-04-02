package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;


abstract public class Target {

    Picture pic;
    boolean isAlive = false;
    TargetType type;


    public TargetType getType(){
        return type;
    }

    public void draw() {
        pic.draw();
    }

    public double getX() {
        return pic.getX();
    }

    public double getMaxX() {
        return  pic.getMaxX();
    }

    public double getY() {
    return pic.getY();
    }

    public double getMaxY() {
        return pic.getMaxY();
    }

    public void delete() {
        if(isAlive) {
            pic.delete();
            isAlive = false;
        }
    }

    public boolean isAlive(){
        return isAlive;
    }
}
