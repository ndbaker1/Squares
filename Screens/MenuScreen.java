/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;

/**
 * A Screen used as the main menu of the Game
 */
public class MenuScreen extends Screen{

	private Player player;
	private ScoreBoard SB;
	private MenuButton[] buttonLayout;
	/**
	 * Creates a Screen and adds menu buttons
	 */
	public MenuScreen(){
		player = new Player(Game.SCREEN_WIDTH/2, -300);
		SB = new ScoreBoard();
		
		buttonLayout = new MenuButton[3];
		
		/////////////////////////////
		// STARTING SCREEN BUTTONS //
		/////////////////////////////
		buttonLayout[0] = new MenuButton(this,"PLAY", Game.SCREEN_WIDTH/2 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 200){
			public void execute(){
				Game.addGameState(new PlayScreen());
			}
		};
		buttonLayout[1] = new MenuButton(this,"EXIT GAME", Game.SCREEN_WIDTH/2 - 200,3*Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				System.exit(0);
			}
		};
		buttonLayout[2] = new MenuButton(this,"SETTINGS", Game.SCREEN_WIDTH - 250, 50, 200, 100){
			public void execute(){
				Game.addGameState(new SettingsScreen());
			}
		};
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)		{	Game.key_Map.put("UP", true);		}
				if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)	{	Game.key_Map.put("LEFT", true);		}
				if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)	{	Game.key_Map.put("RIGHT", true);	}
				if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)	{	Game.key_Map.put("DOWN", true);		}
			}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)		{	Game.key_Map.put("UP", false);		}
				if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)	{	Game.key_Map.put("LEFT", false);	}
				if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)	{	Game.key_Map.put("RIGHT", false);	}
				if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)	{	Game.key_Map.put("DOWN", false);	}
			}
		});
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if (e.getButton() == 1){
					for(MenuButton m: buttonLayout){
						m.doCommand();
					}					
				}
			}	
		});
	}
	
	@Override
	public void paintComponent( Graphics g ){
		player.update();
		g.setColor(new Color(30,0,30));
		g.fillRect(0,0,getWidth(),getHeight());
		drawButtonLayout(g, buttonLayout);
		player.draw(g);
		SB.draw(g);
		drawCursor(g);
	}
	
	public void initialize(){
		Game.soundManager.PlaySound("MENU1");
	}
	public void resume(){
		Game.soundManager.ResumeSound("MENU1");
		Game.refreshKeys();
	}
}

/**
 * A Small Class for the Scoreboard of the Game
 */ 
class ScoreBoard{
	private final int num_scores = 8;
	/**
	 * Draws the Random Mode Score portion of the Scoreboard
	 * @param g the Graphics to draw onto
	 */
	private void drawRandomScore(Graphics g){
		g.setColor(new Color(255,255,255,200));
		g.fillRect(50,50,200,(Game.SCREEN_HEIGHT-100)/3);
		g.setColor(Color.black);
		g.drawString("RANDOM:", 109, 100);
		g.drawLine(55, 101, 245, 101);
		for(int i = 0; i < Game.score_Set[0].size() && i < num_scores; i++){
			g.drawString(String.format("Seconds: %.3f", Game.score_Set[0].toArray()[i]), 80, 150+30*i);
		}
	}
	/**
	 * Draws the Wave Mode Score portion of the Scoreboard
	 * @param g the Graphics to draw onto
	 */
	private void drawWavesScore(Graphics g){
		g.setColor(new Color(255,255,255,200));
		g.fillRect(50,50+(Game.SCREEN_HEIGHT-100)/3,200,(Game.SCREEN_HEIGHT-100)/3);
		g.setColor(Color.black);
		g.drawString("WAVES:", 112, 100+(Game.SCREEN_HEIGHT-100)/3);
		g.drawLine(55, 101+(Game.SCREEN_HEIGHT-100)/3, 245, 101+(Game.SCREEN_HEIGHT-100)/3);
		for(int i = 0; i < Game.score_Set[1].size() && i < num_scores; i++){
			g.drawString(String.format("Seconds: %.3f",Game.score_Set[1].toArray()[i]), 80, 150+(Game.SCREEN_HEIGHT-100)/3+30*i);
		}
	}
	/**
	 * Draws the Casual Mode Score portion of the Scoreboard
	 * @param g the Graphics to draw onto
	 */
	private void drawCasualScore(Graphics g){
		g.setColor(new Color(255,255,255,200));
		g.fillRect(50,49+2*(Game.SCREEN_HEIGHT-100)/3,200,(Game.SCREEN_HEIGHT-100)/3);
		g.setColor(Color.black);
		g.drawString("CASUAL:", 110, 100+2*((Game.SCREEN_HEIGHT-100)/3));
		g.drawLine(55, 101+2*((Game.SCREEN_HEIGHT-100)/3), 245, 101+2*((Game.SCREEN_HEIGHT-100)/3));
		for(int i = 0; i < Game.score_Set[2].size() && i < num_scores; i++){
			g.drawString(String.format("Seconds: %.3f",Game.score_Set[2].toArray()[i]), 80, 150+2*((Game.SCREEN_HEIGHT-100)/3)+30*i);
		}
	}
	/**
	 * Draws the Scoreboards of all Modes to the screen
	 * @param g the Graphics of the screen to draw onto
	 */
	public void draw(Graphics g){
		drawRandomScore(g);
		drawWavesScore(g);
		drawCasualScore(g);		
	}
	
}