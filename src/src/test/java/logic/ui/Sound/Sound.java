package logic.ui.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private static final String SOUND_PATH = "/Users/waaedazzam/IdeaProjects/Final-Project-Selenium/src/src/test/java/logic/ui/Sound/cheeseSearchVoice.wav";

    public void startVoice(){
        PlayMusic(SOUND_PATH);
    }

    private void PlayMusic(String soundPath) {
        try {
            File file = new File(soundPath);
            if (file.exists()){
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                Thread.sleep(500);
                clip.start();
                long durationMilliseconds = (long) (clip.getMicrosecondLength() / 1000.0);
                Thread.sleep(durationMilliseconds);
            }else {
                System.out.println("File Not Found");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
