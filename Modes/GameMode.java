
/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A Screen that is designated to Handle a Game Mode
 */
public abstract class GameMode extends Screen {
	//////////////////////////////////////////////////
	//////// ** Game and Graphics Components **////////
	//////////////////////////////////////////////////
	protected int frames = 0;
	private boolean isAlive;
	/////////////////////////////
	///// Entity Components /////
	/////////////////////////////
	protected Player player;
	protected ArrayList<Enemy> horde;
	////////////////////////
	// Graphic Components //
	////////////////////////
	private BufferedImage lightingMap;
	private Graphics lightG;

	public GameMode() {
		lightingMap = new BufferedImage(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		lightG = lightingMap.createGraphics();

		player = new Player(Game.SCREEN_WIDTH / 2 - Player.PLAYER_DIMENSIONS / 2,
				Game.SCREEN_HEIGHT / 2 - Player.PLAYER_DIMENSIONS / 2);
		horde = new ArrayList<Enemy>();
		isAlive = true;

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1)
					Game.key_Map.put("SPACE", true);
			}

			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1)
					Game.key_Map.put("SPACE", false);
			}
		});
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Game.addGameState(new PauseScreen(getScreenShot()));
				}
				if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
					Game.key_Map.put("UP", true);
				}
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
					Game.key_Map.put("LEFT", true);
				}
				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Game.key_Map.put("RIGHT", true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					Game.key_Map.put("DOWN", true);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					Game.key_Map.put("SPACE", true);
				}
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					Game.key_Map.put("SHIFT", true);
				}
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
					Game.key_Map.put("UP", false);
				}
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
					Game.key_Map.put("LEFT", false);
				}
				if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Game.key_Map.put("RIGHT", false);
				}
				if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					Game.key_Map.put("DOWN", false);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					Game.key_Map.put("SPACE", false);
				}
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					Game.key_Map.put("SHIFT", false);
				}
			}
		});

	}

	/**
	 * Returns a new Object of the current Gamemode
	 *
	 * @return A Gamemode copy
	 */
	public abstract GameMode newGame();

	/**
	 * Returns the Player if it Exists
	 *
	 * @return the player of the Game
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Adds a HighScore to a designated ScoreBoard
	 */
	public abstract void addScore();

	/**
	 * Updates all the Components inside the Game [Player, Enemies]
	 */
	public void update() {
		frames++;
		player.update();
		hordeUpdate();
	}

	/**
	 * Moves the Horde of Enemies and Removes them if they are far enough off the
	 * screen
	 */
	protected void hordeUpdate() {
		for (int i = horde.size() - 1; isAlive && i >= 0 && horde.size() > i; i--) {
			Enemy e = horde.get(i);

			if (player.isDashing()) {
				if (e.approximateCollidesWithLine(
						player.dash_vec[3],
						player.dash_vec[4],
						player.dash_vec[0],
						player.dash_vec[1])) {
					player.resetDashCooldown();
				}
			} else if (!e.stunned() && e.collision(player)) {
				isAlive = false;
				Game.removeGameState();
				Game.addGameState(new DeathScreen(getScreenShot(), this));
				horde.clear();
				addScore();
			}
			if (e.isOnScreen()) {
				e.setHasBeenSeen(true);
			} else if (e.hasBeenSeen()) {
				horde.remove(i);
			}
			e.update();
		}
	}

	/**
	 * Draws all the Components of the game onto the Screen
	 *
	 * @param g the GRaphics Object
	 */
	public void drawComponents(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		for (Enemy e : horde) {
			if (Game.SMOOTH_LIGHTING)
				e.drawLight(g);
			e.drawBody(g);
		}
		player.draw(g);
		player.drawDashCooldown(g);

		drawCursor(g);
	}

	/**
	 * Draws any score and words onto the screen
	 *
	 * @param g the Graphics Object
	 */
	public void drawSpecifics(Graphics g) {
		g.setColor(Color.white);
		g.drawString(String.format("Game Runtime: %.3f", frames / Game.FPS), getWidth() / 2, 100);
	}

	@Override
	public void paintComponent(Graphics g) {
		drawComponents(g);
		drawSpecifics(g);
		update();
	}

	public void run() {
		repaint(0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
	}

	public void initialize() {
		Game.soundManager.PauseAllSounds();
		Game.soundManager.PlaySound("MENU2");
		Game.refreshKeys();
	}

	public void close() {
		Game.soundManager.PauseAllSounds();
		Game.soundManager.StopSound("MENU2");
		Game.soundManager.PlaySound("MENU1");
	}

	public void pause() {
	}

	public void resume() {
		Game.soundManager.ResumeSound("MENU2");
		Game.refreshKeys();
	}
}
