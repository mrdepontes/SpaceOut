/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class FXManager {
    private List<Effect> effects = new ArrayList<>();
    
    private SpaceOut spaceOut;
    
    public FXManager(SpaceOut spaceOut) {
        this.spaceOut = spaceOut;
    }
    
    public void addEffect(Effect effect) {
        effects.add(effect);
    }
    
    public void removeEffect(Effect effect) {
        effects.remove(effect);
    }
    
    public void clearEffects() {
        effects.clear();
    }
    
    public void renderEffects(Graphics g) {
        for(int i = 0; i < effects.size(); i++) {
            Effect effect = effects.get(i);
            effect.render(g);
        }
    }
    
    public void updateEffects(int delta) {
        for(int i = 0; i < effects.size(); i++) {
            Effect effect = effects.get(i);
            effect.update(delta);
        }
    }
    
}
