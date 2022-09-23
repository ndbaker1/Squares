/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.util.*;

/**
 * A Powerup that allows the player to drop a mine
 */
public class minePowerup extends Powerup
{
	/**
	 * Creates a Mine Powerup with given xpoints, ypoints, player, and Gamemode Refernce
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */
	public minePowerup( int[]x, int[]y, Player p, CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/mine.png");
	}
	@Override
	public void update(ArrayList<Enemy> a)
	{
		if(Game.key_Map.get("SHIFT")){
			int xp = player.getCenterX();
			int yp = player.getCenterY();
			int[]x = new int[]{xp-15,xp+15,xp+15,xp-15};
			int[]y = new int[]{yp-15,yp-15,yp+15,yp+15};
			GameMode_REF.mines.add(new Mine(x,y));
			Game.key_Map.put("SHIFT",false);
			GameMode_REF.setActivePowerup(null);
		}
	}
}
