/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.*;
import java.util.*;
public class slowDown extends Powerup
{
	/**
	 * Creates a Slowdown Powerup with given xpoints, ypoints, player, and Gamemode Refernce
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */
	public slowDown(int[]x, int[]y, Player p,CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/slowdown.png");
	}
	@Override
	public void update(ArrayList<Enemy> horde)
	{
		if(Game.key_Map.get("SHIFT")){
			for(Enemy e: horde)
			{
				e.slow(.7);
			}
			GameMode_REF.setActivePowerup(null);
		}	
	}
}