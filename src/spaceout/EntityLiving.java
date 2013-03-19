/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.awt.Rectangle;
import org.newdawn.slick.*;

/**
 *
 * @author roza330
 */
public class EntityLiving extends Entity {

    protected double velocityX, velocityY;
    protected boolean isDead = false;

    public EntityLiving(SpaceOut spaceOut, String spriteLoc, boolean addToManager) {
        super(spaceOut, spriteLoc, addToManager);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isColliding(Entity entity) {
        this.boundingBox = new Rectangle((int) this.posX, (int) this.posY, (int) this.width, (int) this.height);
        return this.boundingBox.intersects(entity.getBounds());
    }

    /**
     * Checks whether a certain side is in collision
     *
     * @param entity The entity to check collision with
     * @param sideNumber Which side to check collision with (0 - NORTH, 1 -
     * SOUTH, 2 - EAST, 3 - WEST)
     */
    public boolean isCollidingSide(Entity entity, int sideNumber) {
        this.boundingBox = new Rectangle((int) this.posX, (int) this.posY, (int) this.width, (int) this.height);

        BoundingBox box = new BoundingBox(this.boundingBox);
        Rectangle hitSide = box.getSides(32)[sideNumber];
        return hitSide.intersects(entity.getBounds());
    }

    public void move(int delta) {
        this.posX += delta * this.velocityX;
        this.posY += delta * this.velocityY;
    }

    public void render(Graphics g) {
        if (!isDead) {
            super.render(g);
        }
        
        g.setColor(Color.white);
    }

    public void update(int delta) {
        if (!isDead) {
            super.update(delta);
            this.boundingBox = new Rectangle((int) this.posX, (int) this.posY, (int) this.width, (int) this.height);

            move(delta);
        }
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void kill() {
        this.isDead = true;
        this.onDeath();
        
        if(this instanceof EntityEnemy) {
            spaceOut.getEntityManager().getCurrentFormation().removeEnemy((EntityEnemy) this);
        }
    }
    
    public void onDeath() {
        
    }
}
