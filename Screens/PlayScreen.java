/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.event.*;
import java.awt.*;
import javax.imageio.*;

/**
 * A Screen that Displays modes for the Game to play
 */
public class PlayScreen extends Screen{
	private MenuButton[] buttonLayout;
	private Image background;
	
	/**
	 * Creates a Screen to allow for a choice in game mode
	 */
	public PlayScreen(){
		try					{	background = ImageIO.read(getClass().getResource("img/play_screen.png"));	}
		catch(Exception e)	{	System.out.println (e+" Play Screen ERROR");	}
		buttonLayout = new MenuButton[5];
			
		////////////////////////
		/// GAMEMODE BUTTONS ///
		////////////////////////
		buttonLayout[0] = new MenuButton(this, "WAVES", Game.SCREEN_WIDTH/2 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.addGameState(new WavesGameMode());
			}
		};
		buttonLayout[1] = new MenuButton(this, "RANDOM SPAWN", Game.SCREEN_WIDTH/4 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.addGameState(new RandomGameMode());
			}
		};
		buttonLayout[2] = new MenuButton(this, "CASUAL", 3*Game.SCREEN_WIDTH/4 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.addGameState(new CasualGameMode());			
			}
		};
		buttonLayout[3] = new MenuButton(this, "BACK TO MAIN MENU", Game.SCREEN_WIDTH - 250, 50, 200, 100){
			public void execute(){
				Game.removeGameState();			
			}
		};
		buttonLayout[4] = new MenuButton(this, "TUTORIAL", Game.SCREEN_WIDTH/2 - 200, Game.SCREEN_HEIGHT/2 + 120, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.addGameState(new TutorialGameMode());	
			}
		};
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)	{	Game.removeGameState(); }
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
		g.drawImage(background,0,0,null);
		
		drawButtonLayout(g, buttonLayout);
		drawCursor(g);
	} 
}