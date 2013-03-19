/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Graphics;

/**
 *
 * @author roza330
 */
public class EntityManager {

    private SpaceOut spaceOut;
    private List<Entity> entities = new ArrayList<>();
    private EnemyFormation enemyFormation;

    public EntityManager(SpaceOut spaceOut) {
        this.spaceOut = spaceOut;

        enemyFormation = new EnemyFormation(spaceOut);
        enemyFormation.createEnemies();
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void clearEntities() {
        this.entities.clear();
    }

    public EnemyFormation getCurrentFormation() {
        return this.enemyFormation;
    }

    public void renderEntities(Graphics g) {
        for (Entity entity : entities) {
            entity.render(g);
        }

        enemyFormation.renderFormation(g);
    }

    public void updateEntities(int delta) {
        for (Entity entity : entities) {
            entity.update(delta);
        }

        enemyFormation.updateFormation(delta);
    }
}
