/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.imageio.*;
import java.util.concurrent.atomic.*;

/**
 * A screen that displays information while the game loads
 */
public class LoadingScreen extends Screen{
	private MenuButton startButton;
	private Image background;
	private AtomicReference<String> loading_reference;
	/**
	 * Creates a Loading Screen
	 */
	public LoadingScreen(){ 
		try					{	background = ImageIO.read(getClass().getResource("img/load_picture.png"));	}
		catch(Exception e)	{	System.out.println (e+" Loading Screen ERROR");	}
		loading_reference = new AtomicReference<String>();
		startButton = new MenuButton(this,"SAKUSEN KAISHI", Game.SCREEN_WIDTH/2 - 200,3*Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				Game.addGameState(new MenuScreen());
			}
			public void labelUpdate(){
				setLabel(loading_reference.get());
			}
		};
		startButton.setActive(false);
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)	{	System.exit(0); }
			}
		});
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if (e.getButton() == 1){
					startButton.doCommand();				
				}
			}
		});
		
		new Thread(() -> {
			WavesGameMode.loadLevels(Game.waves, loading_reference);
			startButton.setActive(true);		
		}).start();
	}
	@Override
	public void paintComponent( Graphics g ){
		g.drawImage(background,0,0,null);
		startButton.draw(g);
		drawCursor(g);
	}
}