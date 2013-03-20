/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class Effect {

    protected double posX, posY;
    protected Animation effectAnimation;
    protected FXManager manager;
    private boolean killed = false;

    public Effect(FXManager manager) {
        this.manager = manager;
        this.effectAnimation = new Animation();
        this.effectAnimation.setLooping(false);
        manager.addEffect(this);
    }

    public double getPosX() {
        return this.posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return this.posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void setPosition(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getWidth() {
        return this.effectAnimation.getCurrentFrame().getWidth();
    }

    public int getHeight() {
        return this.effectAnimation.getCurrentFrame().getHeight();
    }

    public void onEffectFinish() {
        this.manager.removeEffect(this);
    }

    public void render(Graphics g) {
        if (!this.killed) {
            effectAnimation.draw((int) this.posX, (int) this.posY);
        }
    }

    public void update(int delta) {
        if (!this.killed) {
            if (effectAnimation.isStopped()) {
                this.killed = true;
                this.onEffectFinish();
            }
        }
    }
}
