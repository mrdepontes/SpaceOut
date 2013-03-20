/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class EnemyFormation {

    public static final int FORMATION_Y_GAP = 10;
    public static final int FORMATION_X_GAP = 3;
    public static List<EntityEnemy> enemies = new ArrayList<>();
    public static List<EntityBrick> bricks = new ArrayList<>();
    private SpaceOut spaceOut;
    private int moveDelay = 500;
    private long lastMove;
    private boolean moveDirection = false; //false - right, true - left
    private long lastShoot;
    private boolean canShoot;
    private int shootDelay = 5000;

    public EnemyFormation(SpaceOut spaceOut) {
        this.spaceOut = spaceOut;
    }

    public void createEnemies() {
        this.createBombers(20);
    }

    private void createBombers(int yStart) {
        EntityEnemy referenceEnemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_BOMBER);

        int enemyAmountX = SpaceOut.WIDTH / (int) referenceEnemy.getWidth() - 10;

        for (int x = 2; x < enemyAmountX; x++) {
            EntityEnemy enemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_BOMBER);
            enemy.setPosition(x * (referenceEnemy.getWidth() + FORMATION_X_GAP), 20);
            enemies.add(enemy);
        }

        this.createFillers(yStart + (int) referenceEnemy.height + FORMATION_Y_GAP);
    }

    private void createFillers(int yStart) {
        EntityEnemy referenceEnemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_FILLER);

        int enemyAmountX = SpaceOut.WIDTH / (int) referenceEnemy.getWidth() - 10;
        int enemyAmountY = 3;

        for (int y = 0; y < enemyAmountY; y++) {
            for (int x = 2; x < enemyAmountX; x++) {
                EntityEnemy enemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_FILLER);
                enemy.setPosition(x * (referenceEnemy.getWidth() + FORMATION_X_GAP), yStart);
                enemies.add(enemy);
            }

            yStart += referenceEnemy.height + FORMATION_Y_GAP;
        }

        this.createShooters(yStart);
    }

    private void createShooters(int yStart) {
        EntityEnemy referenceEnemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_SHOOTER);

        int enemyAmountX = SpaceOut.WIDTH / (int) referenceEnemy.getWidth() - 10;
        int enemyAmountY = 2;

        for (int y = 0; y < enemyAmountY; y++) {
            for (int x = 2; x < enemyAmountX; x++) {
                EntityEnemy enemy = new EntityEnemy(spaceOut, this, EntityEnemy.TYPE_SHOOTER);
                enemy.setPosition(x * (referenceEnemy.getWidth() + FORMATION_X_GAP), yStart);
                enemies.add(enemy);
            }

            yStart += referenceEnemy.height + FORMATION_Y_GAP;
        }
    }

    public void createBricks() {
        EntityBrick referenceBrick = new EntityBrick(spaceOut, EntityEnemy.TYPE_FILLER);

        int enemyAmountX = SpaceOut.WIDTH / (int) referenceBrick.getWidth() - 4;
        int enemyAmountY = 3;

        int yStart = 20;

        for (int x = 2; x < enemyAmountX; x++) {
            EntityBrick brick = new EntityBrick(spaceOut, EntityEnemy.TYPE_BOMBER);
            brick.setPosition(x * (referenceBrick.getWidth() + FORMATION_X_GAP), yStart);
            bricks.add(brick);
        }

        yStart += (int) referenceBrick.height + FORMATION_Y_GAP;

        for (int y = 0; y < enemyAmountY; y++) {
            for (int x = 2; x < enemyAmountX; x++) {
                EntityBrick brick = new EntityBrick(spaceOut, EntityEnemy.TYPE_FILLER);
                brick.setPosition(x * (referenceBrick.getWidth() + FORMATION_X_GAP), yStart);
                bricks.add(brick);
            }

            yStart += referenceBrick.height + FORMATION_Y_GAP;
        }

        enemyAmountY = 2;

        for (int y = 0; y < enemyAmountY; y++) {
            for (int x = 2; x < enemyAmountX; x++) {
                EntityBrick brick = new EntityBrick(spaceOut, EntityEnemy.TYPE_FILLER);
                brick.setPosition(x * (referenceBrick.getWidth() + FORMATION_X_GAP), yStart);
                bricks.add(brick);
            }

            yStart += referenceBrick.height + FORMATION_Y_GAP;
        }
    }

    public void moveFormation() {
        if (System.currentTimeMillis() - this.lastMove > this.moveDelay) {
            if (this.moveDirection == false) {
                lastMove = System.currentTimeMillis();

                for (EntityEnemy enemy : enemies) {
                    enemy.posX += 10;
                }

            } else if (this.moveDirection == true) {
                lastMove = System.currentTimeMillis();

                for (EntityEnemy enemy : enemies) {
                    enemy.posX -= 10;
                }
            }
        }
    }

    public void removeEnemy(EntityEnemy enemy) {
        if (enemies.contains(enemy)) {
            enemies.remove(enemy);
        }
    }

    public void renderFormation(Graphics g) {
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isDead) {
                enemies.get(i).render(g);
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (!bricks.get(i).isDead) {
                bricks.get(i).render(g);
            }
        }
    }

    public void updateFormation(int delta) {
        this.moveFormation();

        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isDead) {
                enemies.get(i).update(delta);
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (!bricks.get(i).isDead) {
                bricks.get(i).update(delta);
            }
        }

        if (enemies.isEmpty() && bricks.isEmpty()) {
            this.createBricks();

            EntityBall theBall = spaceOut.getBall();
            theBall.width = 16;
            theBall.height = 16;
        }

        if (!enemies.isEmpty()) {
            if (System.currentTimeMillis() - this.lastShoot > this.shootDelay) {
                this.canShoot = true;

                Random rand = new Random();
                EntityEnemy enemy = enemies.get(rand.nextInt(enemies.size()));

                this.shoot(enemy);
            }
        }
    }

    private void shoot(EntityEnemy enemy) {
        if (enemy.getType() == EntityEnemy.TYPE_BOMBER) {
            EntityBullet bullet = new EntityBullet(spaceOut, BulletType.BULLET_BOMBER, enemy);
            bullet.setPosition(enemy.posX + enemy.width / 2 - bullet.width, enemy.posY + enemy.height);
            bullet.setVelocityY(0.5);
        } else if (enemy.getType() == EntityEnemy.TYPE_SHOOTER) {
            EntityBullet bullet = new EntityBullet(spaceOut, BulletType.BULLET_SHOOTER, enemy);
            bullet.setPosition(enemy.posX + enemy.width / 2 - bullet.width, enemy.posY + enemy.height);
            bullet.setVelocityY(0.5);
        }

        this.lastShoot = System.currentTimeMillis();
        this.canShoot = false;
    }

    public void changeDirection(boolean direction) {
        this.moveDirection = direction;

        for (EntityEnemy enemy : enemies) {
            if (direction == true) {
                enemy.posX -= 3;
            } else {
                enemy.posX += 3;
            }

            enemy.posY += 10;
        }
    }
}
