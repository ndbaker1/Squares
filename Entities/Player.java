/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Entity that is controlled by the Player
 */
public class Player{
	public static  final int PLAYER_DIMENSIONS = 40; // PIXELS
	private static final int DASH_COOLDOWN = 3; //SECONDS
	private static final int DASH_RADIUS = 300;
	
	private int size, x, y, speed, dash_Tick, stunDuration;
	private double[] dash_Vec;
	private byte cooldownSequence;
	private boolean resetDash, stunned;
	
	private Queue<double[]> trail;
	private BufferedImage body;
	
	/**
	 * Constructs a Player with a given x and y value
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
    public Player(int x, int y) {
    	this.x = x;
    	this.y = y;
    	size = PLAYER_DIMENSIONS;
    	speed = 10;
    	dash_Vec = new double[5];
    	dash_Tick = stunDuration = 0;
    	cooldownSequence = 0;
    	resetDash = true;
    	stunned = false;
    	trail = new LinkedList<double[]>();
    	
		body = new BufferedImage(size*4, size*4, BufferedImage.TYPE_INT_ARGB);
		Graphics g = body.createGraphics();
		double interval = 150.0/40;
		for(int i = 0; i <= size; i++){
	   		g.setColor(new Color(0,255,255,(int)(interval*i)));
	   		g.drawOval(size+i,size+i, size*3 - i*2, size*3 - i*2);
	   	}
	   	g.setColor(Color.WHITE);
    	g.fillRect(2*size,2*size,size,size);
	   	g.dispose();
    }    
   	
   	/**
   	 * Updates all components of the Player
   	 */ 
   	public void update(){
		updateMovement();
		updateTrail();
   	}
   	/**
   	 * Updates the Movement and Dashing Ability of the Player
   	 */
	private void updateMovement(){
		if(dash_Vec[2]++ < 5){
			for (double i = 0; i < 10; i++){
				trail.add(new double[]{x + i*dash_Vec[0]/50,y + i*dash_Vec[1]/50, PLAYER_DIMENSIONS});
			}
			x+=(dash_Vec[0]/5);
			y+=(dash_Vec[1]/5);
		}if(!resetDash && dash_Vec[2] == 6){
			dash_Tick = (int)Game.FPS*DASH_COOLDOWN;
			resetDash = true;
		}
		if(dash_Tick-- < 0 && Game.key_Map.get("SPACE")){
			stunned = false;
			Game.key_Map.put("SPACE", false);
			Game.soundManager.PlaySound("DASH");
			dash_Vec = dashParams();
		}
		if(!stunned){
			if (Game.getInputMode()){	moveMouse();	}
			else					{	moveWASD();		}
		}else{
			if(stunDuration-- < 0)
				stunned = false;
		}
	}
	/**
     * Stuns the Player and makes their speed 0
     * @param stunTime the time the stun lasts
     */ 
    public void stun( double stunTime ){
    	stunned = true;
    	stunDuration = (int)(stunTime*Game.FPS);
    }
    
    /**
     * Returns if the Player is stunned
     * @return if the Player is stunned
     */
    public boolean stunned(){
    	return stunned;
    }
	/**
	 * Gives the Dash_Vec array instructions on how to move the player for a dash
	 */
	private double[] dashParams(){
		resetDash = false;
		double dx = -getCenterX()+MouseInfo.getPointerInfo().getLocation().x;
   		double dy = -getCenterY()+MouseInfo.getPointerInfo().getLocation().y;
		double dir = Math.atan2(dy, dx);
		dx = dx < 0 ? Math.max(dx, Math.cos(dir)*DASH_RADIUS):Math.min(dx, Math.cos(dir)*DASH_RADIUS);
		dy = dy < 0 ? Math.max(dy, Math.sin(dir)*DASH_RADIUS):Math.min(dy, Math.sin(dir)*DASH_RADIUS);
		return new double[]{ dx, dy, 0 };
	}
	
	/**
	 * Updates only the Trail of the Player
	 */
	private void updateTrail(){
		synchronized(trail){
			trail.add(new double[]{x,y,size});
	   		double factor = 0.5;
	   		for(double[] shadow: trail){
	   			shadow[0] += factor/2;
	   			shadow[1] += factor/2;
	   			shadow[2] -= factor;
	   		}if(trail.peek()[2] < 0.1){
	   			trail.remove();
	   		}
		}
	}
	/**
	 * Draws the Trail of the Player
	 * @param g the Graphics to draw onto
	 */
	private void drawTrail(Graphics g){
		synchronized(trail){
			for(double[] s: trail){
	   			g.setColor(new Color((int)(128*(Math.cos(s[0]/20.0)+1)%255),(int)(128*(Math.sin(s[0]/20.0)+1)%255),(int)(128*(Math.sin(s[1]/20.0)+1)%255), 16));
	   			g.fillRect((int)s[0],(int)s[1],(int)s[2],(int)s[2]);
	   		}
		}
	}
	
	/**
	 * Moves the player in accordance with the direction key controls
	 */
   	public void moveWASD(){
   		int HDIR = 0; // horizontal factor
   		int VDIR = 0; // vertical factor
   		if(Game.key_Map.get("UP")	&&	getCenterY() > 0)					{ VDIR++; }
   		if(Game.key_Map.get("DOWN")	&&	getCenterY() < Game.SCREEN_HEIGHT)	{ VDIR--; }
   		if(Game.key_Map.get("RIGHT")&&	getCenterX() < Game.SCREEN_WIDTH)	{ HDIR++; }
   		if(Game.key_Map.get("LEFT")	&&	getCenterX() > 0) 					{ HDIR--; }
		if(HDIR != 0 || VDIR != 0){
			move(Math.atan2(VDIR,HDIR),speed);
   		}
   	}
   	/**
   	 * Moves the player towards the Mouse position
   	 */
   	public void moveMouse(){
   		int xTarget = (int)MouseInfo.getPointerInfo().getLocation().x;
   		int yTarget = (int)MouseInfo.getPointerInfo().getLocation().y;
   		int dx = xTarget-getCenterX();
   		int dy = getCenterY()-yTarget;
   		move(Math.atan2(dy, dx), Math.min(Math.sqrt(dy*dy+dx*dx), speed));		
   	}
	/**
	 * Returns whether the Player is currently Dashing
	 * @return if the Player is currently dashing
	 */
	public boolean isDashing(){
		return dash_Vec[2] > 0 && dash_Vec[2] < 5;
	}
	/**
	 * Resets the Cooldown of the Dash back to Zero and modifies how the cooldown is drawn
	 */
	public void resetDashCooldown(){
		resetDash = true;
		dash_Tick = 0;
		cooldownSequence = 20;
	}
	/**
	 * Draws the Cooldown Bar of the Player's Dash ability
	 * @param g the Graphics to draw onto
	 */
   	public void drawDashCooldown(Graphics g){
   		if(cooldownSequence > 0){
   			g.setColor(new Color(150+cooldownSequence*5,150+cooldownSequence*5,150+cooldownSequence--*5));
   		}else{
   			g.setColor(new Color(150,150,150));	
   		}
		g.drawRect(50,Game.SCREEN_HEIGHT-80,400,40);
		g.fillRect(50,Game.SCREEN_HEIGHT-80,(int)(400*(1 - Math.max(dash_Tick/Game.FPS, 0)/DASH_COOLDOWN)),40);
	}
	/**
	 * Draws all components of the Player
	 * @param g the Graphics to draw onto
	 */ 
   	public void draw(Graphics g){
		drawTrail(g);
		g.drawImage(body,x-size*2,y-size*2,null);
   		g.setColor(new Color(255,255,255,50));
   		g.drawOval(getCenterX() - DASH_RADIUS, getCenterY() - DASH_RADIUS, DASH_RADIUS*2, DASH_RADIUS*2);
   	}
   	/**
     * Moves the Square in a given direction given an angle
	 * @param dir the direction that the square will move
     */
    public void move(double dir, double speed){
    	x+=Math.cos(dir)*speed;
    	y-=Math.sin(dir)*speed;
    }
    /**
	 * Sets the speed of the square's motion
	 * @param s the new speed
	 */
    public void setSpeed(int s) { speed = s; }
	/**
	 * Sets the size of the square
	 * @param s the new size
	 */
    public void setSize(int s) 	{ size = s; }
	/**
	 * Sets the X-value of the square
	 * @param X the x value
	 */
   	public void setX(int X) { x = X; }
	/**
	 * Sets the Y-value of the square
	 * @param Y the y value
	 */
    public void setY(int Y) { y = Y; }
    /**
	 * Returns the speed of the Square
	 * @return the current speed of the Square
	 */
    public int getSpeed(){ return speed; }
	/**
	 * Returns the size of the Square
	 * @return the current size of the Square
	 */
    public int getSize() { return size; }
	/**
	 * Returns the X-value of the Square
	 * @return the current x of the Square
	 */
    public int getX() { return x; }
	/**
	 * Returns the Y-value of the Square
	 * @return the current y of the Square
	 */
    public int getY() { return y; }
    /**
	 * Returns the X-Center of the Square
	 * @return the current Center x of the Square
	 */
    public int getCenterX() { return x+size/2; }
	/**
	 * Returns the Y-Center of the Square
	 * @return the current Center y of the Square
	 */
    public int getCenterY() { return y+size/2; }
    
}
