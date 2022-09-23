/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Base Screen that contains the ability to draw a custom cursor
 */ 
public abstract class Screen extends JPanel{
	
	private double angle;
	private BufferedImage cursor, cursor_img;
	
	/**
	 * Constructs a Screen and creates the cursor image
	 */
	public Screen(){
		setFocusTraversalKeysEnabled(false);
		setFont(new Font("Haettenschweiler", Font.PLAIN, 20));
		cursor = new BufferedImage(60,60,BufferedImage.TYPE_INT_ARGB);
		cursor_img = new BufferedImage(30,30,BufferedImage.TYPE_INT_RGB);
		Graphics g = cursor_img.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(3,3,24,24);
		g.dispose();
	}
	
	/**
	 * Draws the cursor to a given graphics object
	 * @param g_ the Graphics to draw the cursor on
	 */
    public void drawCursor(Graphics g){
		Graphics2D g2 = cursor.createGraphics();
		angle+=0.1;
		g2.setComposite(AlphaComposite.Src);
		g2.setColor(new Color(0, 0, 0, 0));
		g2.fillRect(0, 0, 60, 60);
    	g2.rotate(angle, 30, 30);
    	g2.drawImage(cursor_img, 15,15,null);
		g2.rotate(-angle, 30, 30);
		g2.dispose();
		g.drawImage(cursor, MouseInfo.getPointerInfo().getLocation().x - 30, MouseInfo.getPointerInfo().getLocation().y - 30, null);
    }
    /**
     * Returns a buffered image of the current screen's graphics
     * @return a buffered image of the current screen
     */
    public BufferedImage getScreenShot(){
    	BufferedImage b = new BufferedImage(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    	Graphics bG = b.createGraphics();
    	bG.setFont(getFont());
    	paintComponent(bG);
    	return b;
    }
    
    /**
     * Draws any MenuButtons to the Screen
     * @param g the Graphics to draw the Buttons to
     * @param layout the MenuButton array to reference
     */ 
    public void drawButtonLayout(Graphics g, MenuButton[] layout){
    	for(int i = 0; i < layout.length; i++){
			layout[i].draw(g);
		}
    }
    /**
     * Method to run the current screen 
     */
    public void run(){
    	repaint(0,0,Game.SCREEN_WIDTH,Game.SCREEN_HEIGHT);
    }
    /**
     * Method to initialize the current screen's properties 
     */
    public void initialize(){}
    /**
     * Method to pause the current screen's properties 
     */
    public void pause(){}
    /**
     * Method to pause the current screen's properties 
     */
    public void resume(){}
    /**
     * Method to clean up the previous screen's properties
     */
    public void close(){}
}