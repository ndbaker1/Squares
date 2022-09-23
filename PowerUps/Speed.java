/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * A Powerup that provides the Player with extra speed
 */
public class Speed extends Powerup
{
	private final int ORIGINAL_SPEED = 10;
	
	private int duration;
	private boolean active;
	/**
	 * Creates a Speed Powerup with given xpoints, ypoints, player, and Gamemode Refernce
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */
	public Speed( int[]x, int[]y, Player p, CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/speed.png");
		duration = 2*(int)Game.FPS;
		active = false;
	}
	@Override
	public void update(ArrayList<Enemy> a)
	{
		if(!active && Game.key_Map.get("SHIFT")){
			active = true;
			player.setSpeed(ORIGINAL_SPEED*2);
			Game.key_Map.put("SHIFT",false);
		}else if (active){
			if(duration-- < 0)
			{		
				player.setSpeed(ORIGINAL_SPEED);
				GameMode_REF.setActivePowerup(null);
			}
		}
	}
	@Override
	public void forceDisable()
	{
		player.setSpeed(ORIGINAL_SPEED);
	}
}