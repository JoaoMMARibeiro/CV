package org.academiadecodigo.bootcamp67.grid;

public class TargetFactory {

    public static Target getNewTarget(double x, double y){

        Target target = null;
        int randomTarget = (int) (Math.random()*5);
        switch (randomTarget){
            case 0:
                target = new Chew(x,y);
                break;
            case 1:
                target = new Jawa(x,y);
                break;
            case 2:
                target = new Yoda(x,y);
                break;
            case 3:
                target = new R2D2(x,y);
                break;
            case 4:
                target = new Luke(x,y);
                break;
        }
        return target;
    }

}
