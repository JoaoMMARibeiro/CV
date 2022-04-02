package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class R2D2 extends Target{

    public R2D2(double x, double y) {
        if (Math.random() < 0.3) {
            this.pic = new Picture(x,y, "r2d2.png");
            this.type = TargetType.R2D2;
            isAlive = true;
            draw();
        }

    }
}
