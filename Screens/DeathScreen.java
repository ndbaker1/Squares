/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Screen that marks the Player's Death in Game
 */
public class DeathScreen extends Screen{
	private BufferedImage background;
	private MenuButton returnButton;
	private MenuButton retryButton;
	/**
	 * Creates a Death Screen
	 */ 
	public DeathScreen(BufferedImage b,GameMode mode){
		background = b;
		Graphics dimG = background.getGraphics();
		dimG.setColor(new Color(0,0,0,150));
		dimG.fillRect(0,0,background.getWidth(),background.getHeight());
		dimG.dispose();
		
		returnButton = new MenuButton(this, "RETURN TO MAIN MENU", Game.SCREEN_WIDTH/2 - 200,3*Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				Game.removeGameState();
			}
		};
		retryButton = new MenuButton(this, "RETRY", Game.SCREEN_WIDTH/2 - 200,3*Game.SCREEN_HEIGHT/4 - 320, 400, 200){
			public void execute(){
				Game.removeGameState();
				Game.addGameState(mode.newGame());
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
					returnButton.doCommand();	
					retryButton.doCommand();		
				}
			}	
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(background,0,0,null);
		returnButton.draw(g);
		retryButton.draw(g);
		drawCursor(g);
		g.setColor(Color.white);
		g.drawString("GAME OVER", Game.SCREEN_WIDTH/2-40,Game.SCREEN_HEIGHT/2-400);
	}
	public void initialize(){
		Game.soundManager.PauseAllSounds();
	}
	public void close(){
		Game.soundManager.PlaySound("MENU1");
	}
}