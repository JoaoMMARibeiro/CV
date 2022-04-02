package org.academiadecodigo.bootcamp67.grid;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Luke extends Target{

    public Luke(double x, double y) {
        if (Math.random() < 0.1) {
            this.pic = new Picture(x,y, "luke.png");
            this.type = TargetType.LUKE;
            isAlive = true;
            draw();
        }

    }
}
