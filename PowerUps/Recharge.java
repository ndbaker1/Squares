/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * A Recharge powerup that refills the player's dash
 */
public class Recharge extends Powerup
{
	/**
	 * Creates a Recharge powerup with x points, y points, player, and a Gamemode Reference
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */
	public Recharge(int[]x, int[]y, Player p,CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/recharge.png");
	}
	@Override
	public void update(ArrayList<Enemy>a){
		if(Game.key_Map.get("SHIFT")){
			player.resetDashCooldown();
			GameMode_REF.setActivePowerup(null);
			Game.key_Map.put("SHIFT",false);
		}
	}
}