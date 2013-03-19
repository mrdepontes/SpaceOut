/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class EntityBrick extends EntityLiving {
    private Color brickColour;

    public EntityBrick(SpaceOut spaceOut, int enemyType) {
        super(spaceOut, "res/brick.png", false);
        
        if(enemyType == EntityEnemy.TYPE_BOMBER) {
            this.brickColour = Color.magenta;
        } else if(enemyType == EntityEnemy.TYPE_FILLER) {
            this.brickColour = Color.cyan;
        } else if(enemyType == EntityEnemy.TYPE_SHOOTER) {
            this.brickColour = Color.red;
        }
    }
    
    public void render(Graphics g) {
        g.setColor(brickColour);
        super.render(g);
    }
    
    public void update(int delta) {
        if(this.isColliding(spaceOut.getBall())) {
            this.kill();
            spaceOut.getBall().bounceObject(this);
        }
    }
}
