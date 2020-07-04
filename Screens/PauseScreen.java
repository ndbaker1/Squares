/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Screen that allows navigation through the Game and pauses the foreground
 */
public class PauseScreen extends Screen{
	private BufferedImage background;
	private MenuButton[] buttonLayout;
	/**
	 * Creates a Pause Screen and displays options
	 */ 
	public PauseScreen(BufferedImage b){
		background = b;
		Graphics dimmedBackground = background.getGraphics();
		dimmedBackground.setColor(new Color(0,0,0,150));
		dimmedBackground.fillRect(0,0,background.getWidth(),background.getHeight());
		dimmedBackground.dispose();
		
		buttonLayout = new MenuButton[3];
		buttonLayout[0] = new MenuButton(this, "RESUME", 4*Game.SCREEN_WIDTH/5 - 200,Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
			}
		};
		buttonLayout[1] = new MenuButton(this, "SETTINGS", 4*Game.SCREEN_WIDTH/5 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 200){
			public void execute(){
				Game.addGameState(new SettingsScreen());
			}
		};
		buttonLayout[2] = new MenuButton(this, "EXIT TO MAIN MENU", 4*Game.SCREEN_WIDTH/5 - 200,3*Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.removeGameState();
			}
		};
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ESCAPE)	{	Game.removeGameState(); }
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
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,null);
		drawButtonLayout(g, buttonLayout);
		drawCursor(g);
	}
	public void initialize(){
		Game.soundManager.PauseAllSounds();
	}
}