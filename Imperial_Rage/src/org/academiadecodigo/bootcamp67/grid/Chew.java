package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Chew extends Target {

    public Chew(double x, double y) {
        if (Math.random() < 0.3) {
            this.pic = new Picture(x,y, "chew.png");
            this.type = TargetType.CHEW;
            isAlive = true;
            draw();
        }

    }
}
