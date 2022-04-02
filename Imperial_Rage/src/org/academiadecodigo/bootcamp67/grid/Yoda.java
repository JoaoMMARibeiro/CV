package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Yoda extends Target{

    public Yoda(double x, double y) {
        if (Math.random() < 0.05) {
            this.pic = new Picture(x,y, "babyYoda.png");
            this.type = TargetType.YODA;
            isAlive = true;
            draw();
        }
    }
}
