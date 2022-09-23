/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.util.ArrayList;
import javax.imageio.*;

/**
 * A Shine Powerup that can stun enemies
 */
public class Shine extends Powerup
{
	private Polygon outsideSquare;
	
	/**
	 * Creates a Shine Powerup with given xpoints, ypoints, player, and Gamemode Refernce
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */
	public Shine( int[]x, int[]y, Player p, CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/shine.png");
	}
	@Override
	public void drawUsing(Graphics g)
	{
		int y = player.getY();
		int x = player.getX();
		int[]newX = new int[]{x-80, x+120, x+120, x-80};
		int[]newY = new int[]{y-80, y-80, y+120, y+120};
		outsideSquare = new Polygon(newX,newY,4);
		g.setColor(new Color(0,0,150));
		g.fillPolygon(outsideSquare);				
	}
	/**
	 * Checks if any Enemies collide with the Shine
	 * @param horde the Horde to check collision with the Shine
	 * @return if the Shine made contact with any enemies
	 */
	public boolean collisionWithShine(ArrayList<Enemy> horde)
	{
		boolean activated = false;
		for( int i = horde.size()-1; i>=0; i--)		
		{
			Enemy currentEnemy = horde.get(i);
			if(!currentEnemy.stunned() && currentEnemy.intersects(outsideSquare.getBounds()))
			{
				currentEnemy.stun();
				activated = true;
			}
		}
		return activated;
	}
	@Override
	public void update(ArrayList<Enemy> horde)
	{
		if(Game.key_Map.get("SHIFT") || !player.stunned()){
			if(collisionWithShine(horde)){
				GameMode_REF.setCurrentPowerup(this);
			}
			GameMode_REF.setActivePowerup(null);
			player.stun(0.5);
			Game.key_Map.put("SHIFT",false);
		}
	}
}