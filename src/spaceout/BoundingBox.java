/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceout;

import java.awt.Rectangle;

/**
 *
 * @author roza330
 */
public class BoundingBox {
    private Rectangle box;
    private int posX, posY, width, height;
    
    public BoundingBox(Rectangle box) {
        this.box = box;
        
        this.posX = box.x;
        this.posY = box.y;
        this.width = box.width;
        this.height = box.height;
    }
    
    public Rectangle getBox() {
        return this.box;
    }
    
    /*
     * Returns four sides of the bounding box.
     * NORTH [0]
     * SOUTH [1]
     * EAST [2]
     * WEST [3]
     */
    public Rectangle[] getSides(int splitValue) {
        Rectangle[] sides = new Rectangle[4];
        
        Rectangle north = new Rectangle(this.posX, this.posY, this.width, this.height / splitValue);
        sides[0] = north;
        
        Rectangle south = new Rectangle(this.posX, this.posY + (this.height - this.height / splitValue), this.width, this.height);
        sides[1] = south;
        
        Rectangle east = new Rectangle(this.posX + (this.width - this.width / splitValue), this.posY, this.width, this.height);
        sides[2] = east;
        
        Rectangle west = new Rectangle(this.posX, this.posY, this.width / splitValue, this.height);
        sides[3] = west;
        
        return sides;
    }
}
