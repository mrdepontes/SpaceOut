/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roza330
 */
public class BackgroundMusic extends Music {
    public static BackgroundMusic gameMusic;

    public BackgroundMusic(String ref) throws SlickException {
        super(ref);
    }
    
    public static void initMusic() {
        try {
            gameMusic = new BackgroundMusic("res/sound/theme.ogg");
        } catch(SlickException e) {
            
        }
    }
}
