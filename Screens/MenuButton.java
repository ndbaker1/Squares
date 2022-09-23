/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A Button to interact with A Game Menu
 */
public class MenuButton extends Polygon{
	private Screen menuScreen;
	private String label;
	private boolean active;
	private int lx, ly;
	private final Font font = new Font("Haettenschweiler", Font.PLAIN, 30);
	/**
	 * Constructs a Button given a Command, label, x, y, width, and height
	 * @param l the String Label for the button
	 * @param x the top left x coordinate of the button
	 * @param y the top left y coordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 */
    public MenuButton(Screen ms, String l, int x, int y, int w, int h) {
    	super(new int[]{x,x,x+w,x+w}, new int[]{y,y+h,y+h,y}, 4);
    	active = true;
    	menuScreen = ms;
		label = l;
		FontMetrics metrics = ms.getFontMetrics(font);
		lx = x + w/2 - metrics.stringWidth(l)/2;
		ly = y+h/2+metrics.getHeight()/4;
    }
    /**
     * Sets whether the button is Active [should it change color if it is hovered or not]
     * @param a the boolean state of the button's active parameter
     */
    public void setActive(boolean a){
    	active = a;
    }
    /**
     * A method for the button to execute
     */
    public void execute(){};
    /**
     * A methods to update the button text
     */
    public void labelUpdate(){};
    /**
 	 * Sets the String that the button displays
 	 * @param l the new button label
 	 */ 
    public void setLabel(String l){
		label = l;
	}
	/**
 	 * Returns the Label of the menu button
 	 * @return the label of the button
 	 */   
    public String getLabel(){
    	return label;
    }
 	/**
 	 * Returns the screen that the button belongs to
 	 * @return the screen the button belongs to
 	 */   
    public Screen getScreen(){
    	return menuScreen;
    }
	/**
	 * Executes the command if the mouse is over the button
	 * @return whether or not the command was executed
	 */
	public boolean doCommand(){
		if(active && isHovered()){
			execute();
			return true;
		}
		return false;
	}
	
	/**
	 * Draws the Button and its Label to a given Graphics Object
	 * @param g the Graphics to draw onto
	 */
    public void draw(Graphics g){
    	labelUpdate();
    	if (active && isHovered())	{	g.setColor(new Color(255,255,255));   	}
		else						{	g.setColor(new Color(255,255,255,100));	}
    	g.fillPolygon(this);
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString(label, lx, ly);
    }
	/**
	 * Returns whether the button is currently hovered by the mouse
	 * @return if the mouse is hovering over the button
	 */
    private boolean isHovered(){
		return contains(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
	}
}
