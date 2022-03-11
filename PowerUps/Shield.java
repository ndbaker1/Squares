/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.util.*;
import javax.imageio.*;

/**
 * A Powerup that gives the player a temporary shield
 */
public class Shield extends Powerup
{
	private boolean active;
	private Polygon outsideSquare;
	/**
	 * Creats a shield powerup with given xpoints, ypoints, player, and Gamemode Reference
	 * @param x the x points of the Enemy
	 * @param y the y points of the Enemy
	 * @param p the Player reference in the Casual Game Mode
	 * @param GMREF the CasualGameMode Reference
	 */ 
	public Shield(int[]x, int[]y,Player p, CasualGameMode GMREF)
	{
		super(x,y,p,GMREF,"img/shield.png");	
		active = false;
	}
	@Override
	public void update(ArrayList<Enemy> a){
		if(!active && Game.key_Map.get("SHIFT")){
			active = true;
			Game.key_Map.put("SHIFT",false);
		}
		else if(active && collisionWithShield(a)){
			GameMode_REF.setActivePowerup(null);
		}
	}
	@Override
	public void drawUsing(Graphics g)
	{
		int y = player.getY();
		int[]newX = new int[]{player.getX() -20 , player.getX()+60, player.getX()+60, player.getX()-20};
		int[]newY = new int[]{y-20, y-20, y +60, y+60		};
		outsideSquare = new Polygon(newX,newY,4);
		int[]newX2 = new int[]{player.getX()-10, player.getX()+50, player.getX()+50, player.getX()-10};
		int[]newY2 = new int[]{y-10, y-10, y +50, y+50		};
		Polygon outsideSquare2 = new Polygon(newX2, newY2, 4);
		g.setColor(new Color(255,255,0,100));
		g.fillPolygon(outsideSquare);
	}
	/**
	 * Handles collision of the shield with an Enemy
	 * @param horde the Horde to check collision with the Player
	 * @return if the Shield made contact with and Enemy
	 */
	public boolean collisionWithShield(ArrayList<Enemy> horde)
	{
		boolean ret = false;
		if(!player.isDashing())
		{
			for( int i = horde.size()-1; i>=0; i--)
			{
				if(horde.get(i).intersects(outsideSquare.getBounds2D().getX(),outsideSquare.getBounds2D().getY(),80,80))
				{
					horde.remove(i);
					ret = true;
				}	
			}
		}
		return ret;
	}
}