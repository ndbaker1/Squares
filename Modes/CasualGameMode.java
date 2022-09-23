/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.util.ArrayList;
import java.awt.*;

/**
 * The Gamemode with powerups
 */
public class CasualGameMode extends RandomGameMode{
	private ArrayList<Powerup> powerups;
	public ArrayList<Mine>mines;
	private Powerup currentPowerup, activePowerup;
	private int powerupQuantity, despawnTime, spawnPace, maxOnScreen, betweenSpawns;
	
	/**
	 * Creates a Casual GameMode
	 */
    public CasualGameMode() {
    	powerups = new ArrayList<Powerup>();
    	mines = new ArrayList<Mine>();
    	maxOnScreen = 3;
    	spawnPace = 1;//seconds
    	betweenSpawns = (int)(Game.FPS/2);//frames
    } 
    @Override
    public void drawComponents(Graphics g)
    { 
    	super.drawComponents(g);
    	for(Powerup p:powerups)
    		p.drawPickup(g);
    	for(Mine bomb: mines)
    		bomb.draw(g);
    	if(activePowerup != null){
    		activePowerup.drawUsing(g);
    	}
    }
    @Override
    public void drawSpecifics( Graphics g ){
    	super.drawSpecifics(g);
		drawLegend(g);
	}
	/**
	 * Updates logic for all Powerups
	 */
   	public void powerupUpdate(){
   		if(frames%betweenSpawns == 0 && powerups.size() < maxOnScreen)
   		{
   			int x = (int)(Math.random()*(Game.SCREEN_WIDTH-100))+50;
   			int y = (int)(Math.random()*(Game.SCREEN_HEIGHT-300))+50;
   			int[]xCord = new int[]{x,x+40,x+40,x};
   			int[]yCord = new int[]{y,y,y+40,y+40};
   			
			double r = Math.random()*100;
			if(r>80)
			{
				powerups.add(new slowDown(xCord, yCord, player, this));
			}
			else if(r>64)
			{
				powerups.add(new minePowerup(xCord, yCord, player, this));
			}
			else  if(r>48)
			{   					
				powerups.add(new Shield		(xCord,yCord,player, this));   					
			}
			else if (r>32)
			{
				powerups.add(new Shine		(xCord,yCord,player, this));   					
			}
			else if (r>16)
			{
				powerups.add(new Recharge	(xCord,yCord,player, this));
			}
			else{
				powerups.add(new Speed		(xCord,yCord,player, this));	
			}   	
   		}
   		
   		/* UPATES AND CHECKS COLLISION FOR POWERUPS ON THE GROUND */
   		for(int i = powerups.size()-1 ; i >= 0 ;i--)
   		{
   			if( powerups.get(i).collision(player))
   			{   										
   				currentPowerup = powerups.remove(i);
   			}
   			else if(powerups.get(i).despawnTimer() < 0)
   			{ 
   				powerups.remove(i); 					 				
   			}   		 			
   		}
   		/* MINE UPDATE */
   		for(int i = mines.size()-1; i>=0; i--)
   		{
   		  	if(mines.get(i).update(horde))
   		  	{
   		  		mines.remove(i);
   		  	}
   		}
   		/* UPDATES THE CURRENT POWERUP */
   		if(activePowerup != null ){
   			activePowerup.update(horde);
		}
		/* ACTIVATES THE CURRENT POWERUP */
		if(currentPowerup != null && Game.key_Map.get("SHIFT")){
			if(activePowerup != null){
				activePowerup.forceDisable();
			}
			activePowerup = currentPowerup;
			currentPowerup = null;
			
		} 
	} 		
   	/**
   	 * Changes the current powerup
   	 * @param c the powerup
   	 */
   	public void setCurrentPowerup(Powerup c){
   		currentPowerup = c;
   	}
   	/**
   	 * Changes the active powerup
   	 * @param c the powerup
   	 */
   	public void setActivePowerup(Powerup a){
   		activePowerup = a;
   	}
   	@Override
   	public void addScore(){
   		Game.score_Set[2].add(frames/Game.FPS);
   	}
   	@Override
   	public GameMode newGame()
	{
		return new CasualGameMode();
	}
   	
   	@Override
	public void update(){
		frames++;
		player.update();
		powerupUpdate();
		hordeUpdate();
		hordeSpawnUpdate();
	}
	
	/**
	 * Draws an indicator for the powerups
	 * @param g the Graphics Object
	 */
	public void drawLegend(Graphics g){
		g.setColor(new Color(255,255,255,100));
		g.fillRect(Game.SCREEN_WIDTH-120,Game.SCREEN_HEIGHT-200,120,200);
		g.setColor(Color.WHITE);
		g.drawString("Active Powerup",Game.SCREEN_WIDTH-110,Game.SCREEN_HEIGHT-180);
		g.drawString("Current Powerup",Game.SCREEN_WIDTH-115,Game.SCREEN_HEIGHT-100);
		if(currentPowerup != null){
			g.drawImage(currentPowerup.getImage(), Game.SCREEN_WIDTH-80,Game.SCREEN_HEIGHT-80,null);
		}
		if(activePowerup != null){
			g.drawImage(activePowerup.getImage(), Game.SCREEN_WIDTH-80,Game.SCREEN_HEIGHT-160,null);
		}
	}
}