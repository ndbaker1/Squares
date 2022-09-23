/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Stack;
import java.util.Comparator;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Holds all the Game variables and any necessary resources
 * and Manages the GameState
 */
public class Game{
	public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final HashMap<Integer, HashSet<Enemy>> waves = new HashMap<Integer, HashSet<Enemy>>();
	private static HashMap<Integer, Image> basicLights = new HashMap<Integer, Image>();
	private static HashMap<Integer, Image> chaserLights = new HashMap<Integer, Image>();
	public static final double FPS = 60;

	public static SoundManager soundManager;
	public static HashMap<String, Boolean> key_Map;
	public static TreeSet<Double>[] score_Set;
	public static boolean INPUT_MODE = true;
	public static boolean SMOOTH_LIGHTING = true;

	private static Stack<Screen> GameStates;
	private static JFrame frame;

	public static void main(String[] args){
		score_Set = new TreeSet[3];
		for(int i = 0; i < 3;i++){
			score_Set[i] = new TreeSet<Double>(new Comparator<Double>(){
				public int compare(Double one, Double two){
					return two.compareTo(one);
				}
			});
		}

		key_Map = new HashMap<String, Boolean>();
		refreshKeys();
		
		soundManager = new SoundManager(-30f);
		// 	Songs Used: 	
		//	Digitalism	>>	The Pulse
		//	Nitro Fun	>>	Final Boss 
		soundManager.LoadSound("MENU1", "music/Digitalism_-_The_Pulse.wav");
		soundManager.SetSoundLoop("MENU1", true);
		soundManager.LoadSound("MENU2", "music/Nitro Fun-Final Boss.wav");
		soundManager.SetSoundLoop("MENU2", true);
		soundManager.LoadSound("DASH", "music/dash.wav");

		frame = new JFrame("SQUARES");
		frame.setFocusTraversalKeysEnabled(false);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));

		GameStates = new Stack<Screen>();
		addGameState(new LoadingScreen());

		while(true){
			try{
				long starttime = System.currentTimeMillis();
				GameStates.peek().run();
				long frametime = System.currentTimeMillis() - starttime;//how long it takes to repaint
				if(1000/Game.FPS - frametime > 0){ //1000/FPS = miliseconds/frame
					Thread.currentThread().sleep(Math.round(1000/Game.FPS) - frametime);
				}else{
					System.out.println("Frametime Exceeded Desired");
				}
			}catch(Exception e){
				System.out.println ("<ERROR IN THE MAIN LOOP> -> Message : "+e.getMessage()+"\n<CURRENT GAMESTATE> -> "+(GameStates.empty()?"EMPTY[null]":GameStates.peek()));
				if(GameStates.peek() == null){
					System.out.println ("<Clearing GameState Stack . . . >");
					GameStates.clear();
					System.out.println ("<Adding New MenuScreen . . . >");
					addGameState(new MenuScreen());
				}
			}
		}
	}

	/**
	 * Returns the Player if it Exists
	 * @return the player of the Game
	 */
	public static Player getPlayer(){
		if(GameStates.peek() != null && GameStates.peek() instanceof GameMode)
			return ((GameMode)GameStates.peek()).getPlayer();
		return null;
	}


	/**
	 * Resets all the keys in the keymap to a false state/ unpressed
	 */
	public static void refreshKeys(){
		key_Map.put("RIGHT",false);
		key_Map.put("LEFT",	false);
		key_Map.put("UP",	false);
		key_Map.put("DOWN",	false);
		key_Map.put("SPACE",false);
		key_Map.put("SHIFT",false);
	}

	/**
	 * Removes the top Screen of the GameState stack and updates the window
	 */
	public static void removeGameState(){
		GameStates.pop().close();
		GameStates.peek().resume();
		moveGameState();
	}
	/**
	 * Adds a Screen to the GameState stack and updates the window
	 * @param s the Screen to add to the Stack
	 */
	public static void addGameState(Screen s){
		if (GameStates.size() > 0) GameStates.peek().pause();
		s.initialize();
		GameStates.push(s);
		moveGameState();
	}

	/**
	 * Moves the top of the GameState stack to the Jframe
	 * and initializes the current top Screen before doing so
	 */
	private static void moveGameState(){
		frame.getContentPane().removeAll();
		Screen s = GameStates.peek();
		frame.add(s);
		frame.setVisible(true);
		s.requestFocus();
	}

	/**
	 *gets an image of the appropriate size and color from the bank
	 *if it is the first instance the specified image is required then a new Image
	 *will be made and added to the bank for future use.
	 *@param s the size of the square that will be using the image
	 *@param c the color of the square needed
	 */
    public static Image getImage(int s,Color c)
    {
		if(c.getRed()==255){
			if(!basicLights.containsKey(s))
			{
				BufferedImage bImage = new BufferedImage(s*4, s*4, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bImage.getGraphics();
				for(int i = 0; i <= s; i++){
		   		g.setColor(new Color(255,0,0,(int)(150.0/s*i)));
		   		g.drawOval(i,i, s*3 - i*2, s*3 - i*2);
		   		}
				basicLights.put(s,bImage);
			}
			return basicLights.get(s);
		}
		else{
			if(!chaserLights.containsKey(s))
			{
				BufferedImage bImage = new BufferedImage(s*4, s*4, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bImage.getGraphics();
				for(int i = 0; i <= s; i++){
		   		g.setColor(new Color(128,0,0,(int)(150.0/s*i)));
		   		g.drawOval(i,i, s*3 - i*2, s*3 - i*2);
		   		}
				chaserLights.put(s,bImage);
			}
			return chaserLights.get(s);
		}
    }

	/**
	 * Toggles the input mode between true(Mouse) and false(Keyboard)
	 */
	public static void toggleInputMode(){
		INPUT_MODE = !INPUT_MODE;
	}
	/**
	 * Returns the current Input Mode
	 * @return the current input mode
	 */
	public static boolean getInputMode(){
		return INPUT_MODE;
	}
}