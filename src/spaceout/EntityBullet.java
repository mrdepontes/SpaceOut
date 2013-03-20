/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author roza330
 */
public class EntityBullet extends EntityLiving {

    private EntityLiving shooter;

    public EntityBullet(SpaceOut spaceOut, int bulletType, EntityLiving shooter) {
        super(spaceOut, "res/bullet.png", true);
        this.shooter = shooter;

        try {
            Image shooterBullet = new Image("res/shooterBullet.png");
            
            if (bulletType == BulletType.BULLET_BOMBER) {
                //TODO add bomber bullets
            } else if (bulletType == BulletType.BULLET_SHOOTER) {
                this.setSprite(shooterBullet);
            }
        } catch (SlickException e) {
        }
    }
    
    public void update(int delta) {
        super.update(delta);
        
        if(this.isColliding(spaceOut.getPlayer())) {
            EntityPlayer player = spaceOut.getPlayer(); 
            EffectBulletHit effect = new EffectBulletHit(spaceOut.getFXManager());
            effect.setPosition(this.posX, player.posY - effect.getHeight());
            
            this.kill();
        }
    }
}
