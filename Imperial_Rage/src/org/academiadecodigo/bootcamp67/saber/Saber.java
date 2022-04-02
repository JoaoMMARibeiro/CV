package org.academiadecodigo.bootcamp67.saber;

import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import static org.academiadecodigo.bootcamp67.grid.SimpleGrid.getHeight;
import static org.academiadecodigo.bootcamp67.grid.SimpleGrid.getWidth;

public class Saber implements MouseHandler {
    public static Picture saberPic1;
    public static Picture saberPic2;
    private double x;
    private double y;
    private boolean hit = false;

    public Saber(){
        saberPic1 = new Picture(0, -150, "redSaber1.png");
        saberPic2 = new Picture(-110,-30, "redSaber2.png");
        saberPic1.draw();
    }

    public void mouseMove(MouseEvent event) {
        int pX = 100;
        int pY = 40;
        double x = event.getX();
        double y = event.getY();
        if(x > getWidth()-pX)x=getWidth()-pX;
        if(y > getHeight()-pY )y=getHeight()-pY;
        double newX = x - this.getX();
        double newY = y - this.getY();
        saberPic1.translate(newX, newY);
        if(!hit){
            saberPic1.delete();
            saberPic1.draw();
        }
        if(hit){
            saberPic2.delete();
            saberPic2.draw();
        }
        saberPic2.translate(newX, newY);
        this.setX(this.getX() + newX);
        this.setY(this.getY() + newY);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(!hit){
        saberPic1.delete();
        saberPic2.draw();
        hit = true;
        } else {
            saberPic2.delete();
            saberPic1.draw();
            hit = false;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
