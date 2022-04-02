package org.academiadecodigo.bootcamp67.control;
import org.academiadecodigo.bootcamp67.Game;
import org.academiadecodigo.bootcamp67.grid.SimpleGrid;
import org.academiadecodigo.bootcamp67.grid.Target;
import org.academiadecodigo.bootcamp67.saber.Saber;
import org.academiadecodigo.bootcamp67.sound.Sound;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

public class Controls implements MouseHandler {

    private final Saber saber;
    private final Mouse mouse;


    public Controls(Saber hammer) {
        this.saber = hammer;
        mouse = new Mouse(this);
    }

    public void init() {
        MouseEvent eventClicked = new MouseEvent(saber.getX(), saber.getY(), MouseEventType.MOUSE_CLICKED);
        MouseEvent eventMoved = new MouseEvent(saber.getX(), saber.getY(), MouseEventType.MOUSE_MOVED);

        mouse.addEventListener(eventClicked.getEventType());
        mouse.addEventListener(eventMoved.getEventType());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Sound hit = new Sound();
        Sound misc = new Sound();
        switch ((int) (Math.random() * 3)) {
            case 0:
                misc.misc("saber1.wav");
                break;
            case 1:
                misc.misc("saber2.wav");
            break;
            case 2:
                misc.misc("saber3.wav");
                break;
        }
        this.saber.mouseClicked(mouseEvent);

        for (Target target : SimpleGrid.targets) {
            if (target != null) {
                if (target.isAlive()) {
                    if (target.getX() < mouseEvent.getX() && target.getMaxX() > mouseEvent.getX()) {
                        if (target.getY() < mouseEvent.getY() && target.getMaxY() > mouseEvent.getY()) {
                            switch (target.getType()) {
                                case CHEW:
                                    hit.misc("chewbacca1.wav");
                                    Game.score +=3;
                                    break;
                                case JAWA:
                                    hit.misc("jawa3.wav");
                                    Game.score++;
                                    break;
                                case YODA:
                                    hit.misc("yodaLaugh.wav");
                                    Game.score +=10;
                                    break;
                                case LUKE:
                                    hit.misc("luke.wav");
                                    Game.score += 6;
                                    break;
                                case R2D2:
                                    hit.misc("r2d2.wav");
                                    Game.score += 4;
                                    break;

                                //TODO acrescentar o sons do restantes targets
                            }
                            target.delete();
                        }
                    }
                }
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.saber.mouseMove(mouseEvent);
    }

}


