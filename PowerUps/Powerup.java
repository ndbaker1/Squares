/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * A Powerup that the player can pick up in the Casual Game Mode
 */
public abstract class Powerup extends Polygon
{
	private final int POWERUP_DESPAWN_TIME = 4;
	
	protected Player player;
	private int lifespan;
	private Image icon;
	protected CasualGameMode GameMode_REF;
	
	/**
	 * Creates a powerup with x points, y points, a player reference, Gamemode reference, and link to the icon image
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 * @param image_link the location of the file for the powerup image
	 */
	public Powerup( int[]x, int[]y, Player p, CasualGameMode GMREF, String image_link)
	{
		super(x,y,4);
		try{	icon = ImageIO.read(getClass().getResource(image_link)); }
		catch(Exception e){	System.out.println(e);	}
		lifespan = POWERUP_DESPAWN_TIME*(int)Game.FPS;
		player = p;
		GameMode_REF = GMREF;
	}
	
	/**
	 * Return the remaining life in frames of the Powerup
	 * @return the lifespan of the powerup
	 */
	public int despawnTimer(){
		return lifespan;
	}
	/**
	 * Checks for collisions with the player and decreases the lifespan
	 * @param p the Player to check collision with
	 */
	public boolean collision(Player p)
	{	
		lifespan--;	
		return intersects(p.getX(),p.getY(),p.getSize(),p.getSize());	
	}
	/**
	 * Draws the Powerup's image on the ground
	 * @param g the Graphic Object to draw onto
	 */
	public void drawPickup(Graphics g){
		Rectangle rect = getBounds();
		g.drawImage(icon,rect.x, rect.y,null);
		if	((lifespan<Game.FPS && lifespan %10 == 0) ||
    		 	(lifespan<2*Game.FPS && lifespan %20 == 0)){	
    					g.setColor(new Color(255,255,255,200));
    					g.fillRect(rect.x,rect.y,rect.width,rect.height);
    	}
		
	}
	/**
	 * Return the image that the powerup is drawn with
	 * @return the image the powerup holds
	 */
	public Image getImage(){
		return icon;
	}
	/**
	 * Updates the Powerup's Logic
	 */
	public void update(ArrayList<Enemy> a){}
	/**
	 * Prematurely ends the Powerup's effects
	 */
	public void forceDisable(){}
	/**
	 * Draws the graphics involved with using the Powerup
	 * @param g the Graphics to draw onto
	 */
	public void drawUsing(Graphics g){}
}