package org.academiadecodigo.bootcamp67.menu;
import org.academiadecodigo.bootcamp67.Game;
import org.academiadecodigo.bootcamp67.grid.SimpleGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {
    private Picture start;
    private Keyboard keyboard;
    private Game game;

    public Menu(){
        start = new Picture(SimpleGrid.PADDING,0, "menu.png");
        keyboard = new Keyboard(this);

        game = new Game();

        start.draw();
        KeyboardEvent start = new KeyboardEvent();
        start.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        start.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(start);
        KeyboardEvent esc = new KeyboardEvent();
        esc.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        esc.setKey(27);
        keyboard.addEventListener(esc);
    }




    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()){
            case KeyboardEvent.KEY_SPACE:
                game.play();
                break;
            case 27:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
