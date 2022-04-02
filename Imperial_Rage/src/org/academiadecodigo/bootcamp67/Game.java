package org.academiadecodigo.bootcamp67;

import org.academiadecodigo.bootcamp67.saber.Saber;
import org.academiadecodigo.bootcamp67.sound.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.bootcamp67.control.Controls;
import org.academiadecodigo.bootcamp67.grid.SimpleGrid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    static Saber saber = new Saber();
    static Controls controls = new Controls(saber);
    static SimpleGrid grid;
    private final int totalTime = 30;
    private int counter = totalTime;
    private boolean isPlaying = false;
    Sound music = new Sound();
    Sound saberSound = new Sound();
    private int highestScore;
    private Picture jawa;
    private Picture luke;
    private Picture r2d2;
    private Picture yoda;
    private Picture chew;


    //TODO criar as propriedades de texto do score e do timer

    private Text gameTime = new Text(600,60,""+ counter);
    private Text gameScore = new Text(1100, 60, "Score: 00" + score);
    private Text bestScore;
    public static int score = 0;



    public Game(){
        //TODO trocar esta musica
        music.startMusic("imperialMarch8bit.wav");
        loadHighScore();
        bestScore = new Text(100, 60, "Highest Score: " + highestScore);
    }

    public class Reminder {
        Timer timer;

        public Reminder(int seconds) {
            timer = new Timer();
            timer.schedule(new RemindTask(),seconds * 1000L);
        }

        class RemindTask extends TimerTask {

            public void run() {
                if (counter >= 0){
                    grid.refresh();
                    setGameTime(counter);
                    setGameScore(score);
                    counter--;
                    new Reminder(1);
                    return;
                }
                if(score > highestScore){
                    saveHighScore();
                    highestScore = score;
                }
                System.out.println(highestScore);
//                isPlaying = false;
                grid.clear();
            }
        }
    }

    public void loadHighScore(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("highScore.txt"));
            highestScore = Integer.parseInt(br.readLine());
        } catch (FileNotFoundException e) {
            highestScore = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void saveHighScore(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("highScore.txt"));
                bw.write("" + score);
                bw.flush();
                bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void play(){
        if(!isPlaying) {
            //TODO tirar o game over screen para começar de novo
            grid = new SimpleGrid(200);
            saberSound.misc("lightsaber.wav");
            controls.init();
            grid.init();
            counter = totalTime;
            music.stopMusic();
            music = new Sound();
            bestScore.grow(50,40);
            bestScore.setColor(Color.ORANGE);
            bestScore.draw();
            gameTime.setColor(Color.YELLOW);
            gameTime.grow(50,40);
            gameScore.setColor(Color.RED);
            gameScore.grow(50,40);
            gameScore.draw();
            gameTime.draw();
            yoda = new Picture(100,150, "YodaPoints.png");
            luke = new Picture(300, 150,"LukePoints.png");
            chew = new Picture(700, 150, "ChewPoints.png");
            r2d2 = new Picture(500, 150, "r2D2Points.png");
            jawa = new Picture(900, 150, "jawa_points1.png");
            yoda.draw();
            luke.draw();
            chew.draw();
            r2d2.draw();
            jawa.draw();
            //TODO trocar esta muscia
            music.startMusic("gameMusic.wav");
            new Reminder(1);
            isPlaying = true;
        }
    }

    public void setGameTime(int gameTime) {
        if (counter < 10){
            this.gameTime.setText("0" + gameTime);
            return;
        }
        this.gameTime.setText("" + gameTime);
    }

    public void setGameScore(int gameScore) {
        if(score < 10){
            this.gameScore.setText("Score: 00" + gameScore);
            return;
        }if(score > 99){
            this.gameScore.setText("Score: "+ gameScore);
            return;
        }
        this.gameScore.setText("Score: 0"+ gameScore);
    }


}
