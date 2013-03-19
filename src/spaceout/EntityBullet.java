/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

/**
 *
 * @author roza330
 */
public class EntityBullet extends EntityLiving {
    
    private EntityLiving shooter;

    public EntityBullet(SpaceOut spaceOut, int bulletType, EntityLiving shooter) {
        super(spaceOut, "/res/bullet.png", true);
        this.shooter = shooter;
        
        if(bulletType == BulletType.BULLET_BOMBER) {
            
        } else if(bulletType == BulletType.BULLET_SHOOTER) {
            this.setSprite("/res/shooterBullet.png");
        }
    }
}
