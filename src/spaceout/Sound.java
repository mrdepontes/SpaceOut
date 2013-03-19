/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author roza330
 */
public class Sound {

    public static Sound ballBounce = new Sound("res/sound/ballBounce.wav");
    public static Sound killEnemy = new Sound("res/sound/killEnemy.wav");
    private String loc;

    public Sound(String loc) {
        this.loc = loc;
    }

    public void play(boolean loop) {
        Audio audio = null;
        try {
            audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(loc));
        } catch (IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (loop) {
            audio.playAsMusic(1f, 0.1f, true);
        } else {
            audio.playAsSoundEffect(1f, 0.05f, false);
        }

        SoundStore.get().poll(0);
    }
}
