/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author roza330
 */
public class EffectBulletHit extends Effect {

    public EffectBulletHit(FXManager manager) {
        super(manager);

        try {
            SpriteSheet sheet = new SpriteSheet(new Image("res/bulletHit.png"), 12, 6);
            this.effectAnimation.addFrame(sheet.getSprite(0, 0), 25);
            this.effectAnimation.addFrame(sheet.getSprite(1, 0), 25);
            this.effectAnimation.addFrame(sheet.getSprite(2, 0), 25);
            this.effectAnimation.addFrame(sheet.getSprite(3, 0), 25);
        } catch (SlickException e) {
        }
    }
}
