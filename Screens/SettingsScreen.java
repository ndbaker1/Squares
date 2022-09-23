/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.event.*;
import java.awt.*;

/**
 * A Screen to allow changes in the Game Settings
 */
public class SettingsScreen extends Screen {
	private MenuButton[] buttonLayout; 
	/**
	 * Creates a Screen to allow changes to settings
	 */
    public SettingsScreen() {
   		buttonLayout = new MenuButton[6];
   		/////////////////////////////
		/// SETTING SCREEN BUTTONS //
		/////////////////////////////
		buttonLayout[0] = new MenuButton(this, "BACK", Game.SCREEN_WIDTH - 250, 50, 200, 100){
			public void execute(){
				Game.removeGameState();			
			}
		};
		buttonLayout[1] = new MenuButton(this,"TOGGLE INPUT MODE: WORDS", Game.SCREEN_WIDTH/2 - 200,3*Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void execute(){
				Game.toggleInputMode();
			}
			public void labelUpdate(){
				setLabel("TOGGLE INPUT MODE: " + (!Game.getInputMode()?"KEYBOARD":"MOUSE"));
			}
		};
		buttonLayout[2] = new MenuButton(this,"VOLUME: NUM", Game.SCREEN_WIDTH/2 - 200,Game.SCREEN_HEIGHT/2 - 100, 400, 100){
			public void labelUpdate(){
				setLabel("VOLUME: "+Game.soundManager.getGain());
				if(Game.soundManager.getGain() >= 5){
					setLabel(getLabel()+"[MAX]");
				}else if(Game.soundManager.getGain() <= -80){
					setLabel(getLabel()+"[MIN]");
				}
			}
		};
		buttonLayout[2].setActive(false);
		buttonLayout[3] = new MenuButton(this,"INCREASE VOLUME", Game.SCREEN_WIDTH/2 - 200,Game.SCREEN_HEIGHT/2, 200, 100){
			public void execute(){
				Game.soundManager.changeGain(5f);
			}
		};
		buttonLayout[4] = new MenuButton(this,"DECREASE VOLUME", Game.SCREEN_WIDTH/2,Game.SCREEN_HEIGHT/2, 200, 100){
			public void execute(){
				Game.soundManager.changeGain(-5f);
			}
		};
		buttonLayout[5] = new MenuButton(this,"TOGGLE SMOOTH LIGHTING: ON", Game.SCREEN_WIDTH/2 - 200,Game.SCREEN_HEIGHT/4 - 100, 400, 200){
			public void labelUpdate(){
				setLabel("TOGGLE SMOOTH LIGHTING: "+(Game.SMOOTH_LIGHTING?"ON":"OFF"));
			}
			public void execute(){
				Game.SMOOTH_LIGHTING = !Game.SMOOTH_LIGHTING;
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
    public void paintComponent(Graphics g){
    	g.setColor(new Color(30,0,30));
		g.fillRect(0,0,getWidth(),getHeight());
    	drawButtonLayout(g, buttonLayout);
    	drawCursor(g);
    }
}