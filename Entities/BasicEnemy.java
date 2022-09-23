/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * An Enemy that can only move straight
 */
public class BasicEnemy extends Enemy{
	/**
	 * Creates an enemy with a size, speed, xcenter, ycenter, and direction
	 * @param s the size of the enemy
	 * @param spd the speed of the enemy
	 * @param x the xcenter of the enemy
	 * @param y the ycenter of the enemy
	 * @param d the direction of the enemy
	 */
    public BasicEnemy(int s, int spd, int x, int y, double d) {
    	super(s,spd,d,Color.red);
    	setCorners(x,y);
    }
    
    /**
     * Sets the corners on the Polygon shape of the Square
     * @param x the center x of the square
     * @param y the center y of the square
     */
    private void setCorners(int x, int y){
    	double cornerLength = size * Math.sqrt(2)/2;
    	for(int i = 0; i < 4; i++){
    		addPoint((int)(x + (Math.cos(direction + Math.PI/4*(1+2*i))*cornerLength)),
    				 (int)(y + (Math.sin(-(direction + Math.PI/4*(1+2*i)))*cornerLength)));
    	}
    }
    
    /**
     * Returns a clone of the Enemy
     * @return a new Basic Enemy
     */
    public Enemy clone()
    {
    	return new BasicEnemy(size,speed,getX(),getY(),direction);
    }
    
}