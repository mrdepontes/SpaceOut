/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;

/**
 *
 * @author roza330
 */
public abstract class Entity {

    Random rand = new Random();
    protected SpaceOut spaceOut;
    protected Animation[] animations;
    protected Animation playingAnimation, lastAnimation;
    protected long lastFrame;
    protected Rectangle boundingBox;
    protected Image defaultSprite;
    protected double posX, posY, width, height;

    public Entity(SpaceOut spaceOut, String spriteLoc, boolean addToManager) {
        try {
            this.spaceOut = spaceOut;
            defaultSprite = new Image(spriteLoc);
            this.width = defaultSprite.getWidth();
            this.height = defaultSprite.getHeight();

            if (addToManager) {
                spaceOut.getEntityManager().addEntity(this);
            }

            this.boundingBox = new Rectangle((int) this.posX, (int) this.posY, (int) this.width, (int) this.height);
        } catch (SlickException e) {
        }
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPosition(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void playAnimation(int animation) {
        this.playingAnimation = this.animations[animation];
    }

    public Rectangle getBounds() {
        this.boundingBox = new Rectangle((int) this.posX, (int) this.posY, (int) this.width, (int) this.height);
        return this.boundingBox;
    }

    public void setSprite(Image sprite) {
        defaultSprite = sprite;
    }

    public void update(int delta) {
        if (this.playingAnimation != null) {
            if (this.playingAnimation.isStopped()) {
                this.lastAnimation = this.playingAnimation;
                this.playingAnimation = null;
            }
        }
    }

    public void render(Graphics g) {
        if (this.playingAnimation != null) {
            this.playingAnimation.draw((float) this.posX, (float) this.posY, g.getColor());
            lastFrame = System.nanoTime();
        } else {
            this.defaultSprite.draw((float) this.posX, (float) this.posY, (float) this.width, (float) this.height, g.getColor());
            lastFrame = System.nanoTime();
        }
    }

    public abstract boolean isColliding(Entity entity);

    public abstract boolean isCollidingSide(Entity entity, int side);
}
