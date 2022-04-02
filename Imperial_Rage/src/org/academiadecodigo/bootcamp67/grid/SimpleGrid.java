package org.academiadecodigo.bootcamp67.grid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGrid{
    public static final int PADDING = 10;
    private static int cellSize;
    private static Picture background;
    private Picture hole;
    public static Target[] targets = new Target[15];


    public SimpleGrid(int cellSize){
        SimpleGrid.cellSize = cellSize;
        background = new Picture(PADDING, 0, "stage.jpg");
        background.draw();


    }

    public void init(){
        double col = cellSize  /1.6;
        double row = cellSize * 1.8;
        for (int i = 0; i < targets.length; i++) {
            hole = new Picture (PADDING + col,PADDING + row, "hole.png");
            hole.draw();
            col += cellSize;
            if (col + cellSize >= getWidth()) {
                col = cellSize  /1.6;
                row += cellSize*0.8;
            }
        }
    }

    public void refresh() {
        double col = cellSize / 1.6;
        double row = cellSize * 1.8;
        for (int i = 0; i < targets.length; i++) {
            if(targets[i] != null){
                targets[i].delete();
            }
            targets[i] = TargetFactory.getNewTarget(PADDING + col, PADDING + row);
            col += cellSize;
            if (col + cellSize >= getWidth()) {
                col = cellSize /1.6;
                row += cellSize * 0.8;
            }}
    }

    public void clear(){
        for (Target target : targets) {
            target.delete();
        }
    }

    public static int getWidth() {
        return background.getMaxX();
    }

    public static int getHeight() {
        return background.getMaxY();
    }

}
