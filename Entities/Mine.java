/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.util.*;
import java.awt.*;
/**
 * Mine is a square that kill and enemy on hit
 */
public class Mine extends Polygon{
	/**
	 * Creates a mine with a given x array and y array
	 * @param x the x set of points
	 * @param y the y set of points
	 */
    public Mine(int[] x, int[] y)
    {
    	super(x,y,4);
    }
    
    /**
     * Updates the Mine logic with the horde
     * @param horde the horde of Enemies
     */
    public boolean update(ArrayList<Enemy>horde)
    {
    	boolean ret = false;
    	for(int i = horde.size()-1; i>=0; i--)
    	{
    		if(horde.get(i).collision(this))
    		{
    			horde.remove(i);
    			ret = true;
    		}		
    	}
    	return ret;
    }
    
    /**
     * Draws the Mine to the Screen
     * @param g the Graphics to draw onto
     */
    public void draw(Graphics g)
    {
    	g.setColor(new Color(90,41,21));
    	g.fillPolygon(this);
    	Rectangle r = getBounds();
    	g.setColor(new Color(128,0,0));
    	g.fillRect((int)r.getCenterX()-10,(int)r.getCenterY()-10,20,20);
    }
    
}