package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    Clip clip1;
    Clip clip2;

    public Music() {
        playMusic();
    }

    public void startMusic(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip1 = AudioSystem.getClip();
                clip1.open(audioInput);
                clip1.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file.");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        clip1.close();
    }

    public void playMusic() {
        if (clip1 != null) {
            clip1.stop();
        }
        startMusic("src/Sounds/SPACE_EXPLORE.wav");
    }

    public void playGameSounds(String musicLocation) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip2 = AudioSystem.getClip();
                clip2.open(audioInput);
                clip2.start();
            } else {
                System.out.println("Can't find file.");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public void playItemPickUpSound() {
        playGameSounds("src/Sounds/ITEMPICKUP.wav");
    }

    public void playItemDropSound() {
        playGameSounds("src/Sounds/ITEMDROP.wav");
    }
}
