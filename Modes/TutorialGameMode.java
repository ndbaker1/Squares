/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.util.HashSet;
import java.awt.*;
/**
 * The tutorial Gamemode
 */
public class TutorialGameMode extends GameMode{	
	public TutorialGameMode()
	{
		setFont(new Font("Haettenschweiler", Font.PLAIN, 60));
	}
	public void drawSpecifics(Graphics g){
		g.setColor(Color.WHITE);
		double time = currentTimeInSeconds();
		//Spawning Timings
		if(time == 16){
			horde.add(new BasicEnemy(40,0,Game.SCREEN_WIDTH/2-60,210,0));
			horde.add(new ChasingEnemy(40,0,Game.SCREEN_WIDTH/2+60,210,0));
		}else if(time == 21){
			horde.clear();
			horde.add(new BasicEnemy(40,3,Game.SCREEN_WIDTH/2-60,210,3*Math.PI/2));
			horde.add(new ChasingEnemy(40,3,Game.SCREEN_WIDTH/2+60,210,3*Math.PI/2));
		}else if(time == 30){
			horde.clear();
		}else if(time == 54){
			horde.addAll(getWallForm(50,5,1,10));
		}else if(time == 62){
			
		}
		//Text Timings
		if(time > 1 && time < 4){
			drawLines("Welcome to the Squares Tutorial.",g);
		}else if(time > 5 && time < 9){
			drawLines("That White Square is you,\nYou can move it with your mouse or your keyboard.",g);
		}else if(time > 10 && time < 12){
			drawLines("Enemies are Red Squares,\nDon't let them touch you",g);
		}else if(time > 13 && time < 16){
			drawLines("Enemies will spawn in the spaces below in\n"+(16-(int)time)+" seconds\n[     ]    [     ]",g);
		}else if(time > 17 && time < 19){
			drawLines("Not so scary right?",g);
		}else if(time > 19 && time < 22){
			drawLines("Now let's make them move!",g);
		}else if(time > 23 && time < 26){
			drawLines("Careful,\nOne of them is a Chasing Enemy",g);
		}else if(time > 28 && time < 31){
			drawLines("Now that you've seen the Enemies,\nLet's move on to the basics!",g);
		}else if(time > 32 && time < 38){
			drawLines("Your player can perform a Dash\nby either Left-clicking or pressing the Space key",g);
		}else if(time > 39 && time < 44){
			drawLines("The Dash goes to the position of the Cursor,\nBut cannot be farther than that white ring around you",g);
		}else if(time > 45 && time < 50){
			drawLines("This is useful because you are unable\nto get hit by Enemies during the Dash",g);
		}else if(time > 51 && time < 55){
			drawLines("Also, if you Dash through an Enemy\nYour Dash gets reset!",g);
		}else if(time > 56 && time < 60){
			drawLines("Quick!\nDash through the incoming wall!",g);
		}else if(time > 62){
			drawLines("The Tutorial is Over!\nPress either \"ESCAPE\" or \"TAB\" to enter the navigation screen where\nyou can change your settings or go back to the main menu",g);
		}
	}
	/**
	 * Returns the current time in seconds
	 * @return the time in seconds
	 */
	private double currentTimeInSeconds(){
		return frames/Game.FPS;
	}
	/**
	 * Draws text on separate lines
	 * @param g the Graphics to draw to 
	 */
	private void drawLines(String s, Graphics g){
		String[] lines = s.split("\n");
		FontMetrics fm = getFontMetrics(getFont());
		for(int k = 0; k < lines.length; k++){
			g.drawString(lines[k], (Game.SCREEN_WIDTH-fm.stringWidth(lines[k]))/2, 100+fm.getHeight()*k);
		}
	}
	/**
	 * Forms walls for the levels * 0 for default speed, size, and type
	 * @param size the size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param form the direction the wall will come from [1-4 for walls ## 1 for the left wall]
	 * @param space the space between Enemies
	 * @return a Set of Enemies forming the wall
	 */
	private static HashSet<Enemy> getWallForm(int size, int speed,int form, int space)
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
		int s = size == 0?Enemy.DEFAULT_SIZE:size;
		if(form == 1)//from left
		{
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,-s,i,0));
			}
		}
		if(form == 2)//from top
		{
			double dir = Math.PI/2*3;
			int y = -s;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		if(form == 3)//from right
		{
			double dir = Math.PI;
			int x = Game.SCREEN_WIDTH + s;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,x,i,dir));
			}
		}
		if(form == 4)//from bottom
		{
			double dir = Math.PI/2;
			int y = Game.SCREEN_HEIGHT + s;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		return list;
	}
	@Override
	public GameMode newGame(){
		return new TutorialGameMode();
	}
	public void addScore(){}
}