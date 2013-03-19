/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class EntityPlayer extends EntityLiving {

    public EntityPlayer(SpaceOut spaceOut) {
        super(spaceOut, "res/player.png", true);
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public void update(int delta) {
        super.update(delta);
        
        EntityBall ball = spaceOut.getBall();
        
        if(this.isColliding(ball)) {
            ball.setPosition(ball.posX, this.posY - ball.getHeight() + 1);
            ball.bounceObject(this);
        }
        
        if(this.posX < 0) {
            this.velocityX = 0;
            this.posX = 1;
        } else if(this.posX >= SpaceOut.WIDTH - this.width) {
            this.velocityX = 0;
            this.posX = SpaceOut.WIDTH - (this.width - 1);
        }
    } 
}
