/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.aaaaaaaaaaaa
 */
package spaceout;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author roza330
 */
public class SpaceOut extends BasicGame {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    
    private EntityManager entityManager;
    private FXManager fxManager;
    
    private EntityPlayer thePlayer;
    private EntityBall theBall;
    
    public SpaceOut() {
        super("Space Out");
    }
    
    public void init(GameContainer gc) {
        BackgroundMusic.initMusic();
        
        entityManager = new EntityManager(this);
        fxManager = new FXManager(this);
        
        thePlayer = new EntityPlayer(this);
        thePlayer.setPosition(20, HEIGHT - thePlayer.height - 5);
        
        theBall = new EntityBall(this);
        theBall.setPosition(50, 20);
        
        BackgroundMusic.gameMusic.loop(1, 0.5f);
    }
    
    public void render(GameContainer gc, Graphics g) {
        entityManager.renderEntities(g);
        fxManager.renderEffects(g);
    }
    
    public void update(GameContainer gc, int delta) {
        this.input(gc);
        entityManager.updateEntities(delta);
        fxManager.updateEffects(delta);
    }
    
    public void input(GameContainer gc) {
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_A)) {
            thePlayer.setVelocityX(-0.5);
        } else if(input.isKeyDown(Input.KEY_D)) {
            thePlayer.setVelocityX(0.5);
        } else {
            thePlayer.setVelocityX(0);
        }
        
        if(input.isKeyPressed(Input.KEY_9)) {
            EnemyFormation.enemies.clear();
        }
     }
    
    public void notifyGameOver() {
        //TODO complete method
    }
    
    public EntityPlayer getPlayer() {
        return this.thePlayer;
    }
    
    public EntityBall getBall() {
        return this.theBall;
    }
    
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
    
    public FXManager getFXManager() {
        return this.fxManager;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SpaceOut());
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setSmoothDeltas(true);
            app.setTargetFrameRate(60);
            app.start();
        } catch(SlickException e) {
            
        }
    }
}
