package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Jawa extends Target {

    public Jawa(double x, double y) {
        if (Math.random() < 0.3) {
            this.pic = new Picture(x,y, "jawa.png");
            this.type = TargetType.JAWA;
            isAlive = true;
            draw();
        }
    }
}
