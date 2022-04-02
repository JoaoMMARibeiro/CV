package org.academiadecodigo.bootcamp67.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private Clip background;
    private Clip clip;

    public void startMusic(String path) {
        try {
            URL wavFile = getClass().getClassLoader().getResource(path);
            background = AudioSystem.getClip();
            background.open(AudioSystem.getAudioInputStream(wavFile));
            background.start();
            background.loop(Clip.LOOP_CONTINUOUSLY);
//            background = AudioSystem.getClip();
//            ais = AudioSystem.getAudioInputStream(new File(path));
//            background.open(ais);
//            background.loop(Clip.LOOP_CONTINUOUSLY);
//            background.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    public void stopMusic(){
        background.stop();
    }




    public void misc(String path){
        try {
        URL wavFile = getClass().getClassLoader().getResource(path);
        clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(wavFile));
        clip.start();
    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
        e.printStackTrace();
        }
    }
}
