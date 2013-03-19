/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.util.Random;

/**
 *
 * @author roza330
 */
public class EntityBall extends EntityLiving {
    public EntityBall(SpaceOut spaceOut) {
        super(spaceOut, "res/ball.png", true);
        this.setVelocityX(0.5);
        this.setVelocityY(-0.5);
        
        this.width = 8;
        this.height = 8;
    }
    
    public void bounceObject(EntityLiving entity) {
        this.velocityY = -this.velocityY;
        
        Sound.ballBounce.play(false);
    }

    public void update(int delta) {
        super.update(delta);
        
        if(this.posX < 0) {
            this.posX = 1;
            this.velocityX = -this.velocityX;
            Sound.ballBounce.play(false);
        } else if(this.posX >= SpaceOut.WIDTH - this.width) {
            this.posX = SpaceOut.WIDTH - (this.width - 1);
            this.velocityX = -this.velocityX;
            Sound.ballBounce.play(false);
        }
        
        if(this.posY < 0) {
            this.velocityY = -this.velocityY;
            this.posY = 1;
            Sound.ballBounce.play(false);
        } else if(this.posY >= SpaceOut.HEIGHT - this.height) {
            this.velocityY = -this.velocityY;
            this.posY = SpaceOut.HEIGHT - (this.height - 1);
            Sound.ballBounce.play(false);
        }
    }
}
