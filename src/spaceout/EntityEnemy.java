/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author roza330
 */
public class EntityEnemy extends EntityLiving {

    public static final int ANIM_MOVE = 0;
    public static final int ANIM_EXPLODE = 1;
    
    public static final int TYPE_SHOOTER = 0;
    public static final int TYPE_FILLER = 1;
    public static final int TYPE_BOMBER = 2;
    private EnemyFormation enemyFormation;
    
    private int type;
    private int direction = 0; //0 - right, 1 - left
    private long lastMove = 0;
    private int moveDelay = 500;
    
    private double startX, startY;
    private boolean firstPositionSet = true;

    public EntityEnemy(SpaceOut spaceOut, EnemyFormation enemyFormation, int type) {
        super(spaceOut, "res/defEnemy.png", false);
        this.enemyFormation = enemyFormation;
        this.type = type;

        this.loadAnimation();
    }

    private void loadAnimation() {
        try {
            SpriteSheet sheet = new SpriteSheet(new Image("res/enemyAnim.png"), 24, 16);
            this.animations = new Animation[2];

            if (type == TYPE_SHOOTER) {
                Animation move = new Animation();
                move.addFrame(sheet.getSprite(0, 0), moveDelay);
                move.addFrame(sheet.getSprite(1, 0), moveDelay);
                this.animations[0] = move;
            } else if (type == TYPE_FILLER) {
                Animation move = new Animation();
                move.addFrame(sheet.getSprite(0, 1), moveDelay);
                move.addFrame(sheet.getSprite(1, 1), moveDelay);
                this.animations[0] = move;
            } else if (type == TYPE_BOMBER) {
                Animation move = new Animation();
                move.addFrame(sheet.getSprite(0, 2), moveDelay);
                move.addFrame(sheet.getSprite(1, 2), moveDelay);
                this.animations[0] = move;
            }

            this.playAnimation(ANIM_MOVE);
            
            Animation explosion = new Animation();
            explosion.addFrame(new Image("res/explosion.png"), 500);
            explosion.setLooping(false);
            
            this.animations[1] = explosion;

        } catch (SlickException e) {
        }
    }

    public void setPosition(double posX, double posY) {
        if(!firstPositionSet) {
            super.setPosition(posX, posY);
        } else {
            super.setPosition(posX, posY);
            this.startX = posX;
            this.startY = posY;
        }
    }
    
    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
        this.loadAnimation();
    }
    
    public void onDeath() {
        EntityBrick brick = new EntityBrick(spaceOut, this.type);
        brick.setPosition(startX, startY);
    }

    public void update(int delta) {
        super.update(delta);

        if (this.isColliding(spaceOut.getBall())) {
            this.playingAnimation = this.animations[ANIM_EXPLODE];
            Sound.killEnemy.play(false);
            spaceOut.getBall().bounceObject(this);
        }

        if (this.posX <= this.width * 2) {
            this.enemyFormation.changeDirection(false);
        } else if (this.posX >= SpaceOut.WIDTH - (this.width * 2)) {
            this.enemyFormation.changeDirection(true);
        }
        
        if(lastAnimation == this.animations[ANIM_EXPLODE]) {
            this.kill();
        }
    }
}
