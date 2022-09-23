/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.image.BufferedImage;
import java.awt.*;

/**
 * An Enemy that chases the player
 */
public class ChasingEnemy extends Enemy{
	private final int CHASE_RADIUS_SQUARED = 300*300;
	/**
	 * Creates an enemy with a size, speed, xcenter, ycenter, and direction
	 * @param s the size of the enemy
	 * @param spd the speed of the enemy
	 * @param x the xcenter of the enemy
	 * @param y the ycenter of the enemy
	 * @param d the direction of the enemy
	 */
    public ChasingEnemy(int s, int spd, int x, int y, double d) {
    	super(s,spd,d,new Color(128,0,0));
    	setCorners(x-s/2,y-s/2);
    }
    
    /**
     * Sets the corners on the Polygon shape of the Square
     * @param x the center x of the square
     * @param y the center y of the square
     */
    public void setCorners(int x, int y){
    	addPoint(x,y);
    	addPoint(x+size,y);
    	addPoint(x+size,y+size);
    	addPoint(x,y+size);
    }
    
    @Override
    public void update(){
    	if(Game.getPlayer() != null){
	    	double dx = Game.getPlayer().getCenterX()-getX();
			double dy = getY()-Game.getPlayer().getCenterY();
	    	if(CHASE_RADIUS_SQUARED > dx*dx + dy*dy){
				double angleToPlayer = (Math.atan2(dy,dx)+Math.PI*2)%(Math.PI*2);
				double dir = (direction+Math.PI*2)%(Math.PI*2);
				direction = (direction + 0.04*(Math.abs(dir - angleToPlayer) > Math.PI ? 1 : -1)*(dir > angleToPlayer ? 1 : -1))%(Math.PI*2);
	    	}
    	}
    	super.update();
    }
    /**
     * Returns a clone of the Enemy
     * @return a new Chasing Enemy
     */
    public Enemy clone()
    {
    	return new ChasingEnemy(size,speed,getX(),getY(),direction);
    }
}