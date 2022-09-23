
/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Enemy is an individual enemy square. Movement and drawing of squares will be
 * handled here
 * Hitboxing will be handled here
 */
public abstract class Enemy extends Polygon {
    public static final int DEFAULT_SIZE = 60;
    public static final int DEFAULT_SPEED = 2;

    protected Color color;
    protected int initialspeed, speed, size, stunDuration, slowDuration;
    protected double direction;
    private boolean seen;

    /**
     * Creates an enemy with a given size, speed, x, y, and direction
     *
     * @param s   the size of the enemy
     * @param spd the speed of the enemy
     * @param dir the dir of the enemy
     * @param c   the Color of the Enemy
     */
    public Enemy(int s, int spd, double dir, Color c) {
        color = c;
        size = s;
        initialspeed = speed = spd;
        direction = dir;
        stunDuration = 0;
        slowDuration = 0;
    }

    /**
     * Updates the Components of the Enemy
     */
    public void update() {
        if (slowDuration-- <= 0) {
            speed = initialspeed;
        }
        if (!stunned()) {
            move();
        } else if (stunDuration-- <= 0) {
            speed = initialspeed;
        }
    }

    /**
     * Returns the Enemy to its original speed
     */
    public void revertSpeed() {
        speed = initialspeed;
    }

    /**
     * Slows the Enemy by a scalar factor
     *
     * @param factor the fraction to multiply the speed by
     */
    public void slow(double factor) {
        slowDuration = 3 * (int) Game.FPS;
        if (speed * factor < 2)
            speed = 2;
        else
            speed = (int) (factor * speed);
    }

    /**
     * Stuns an Enemy and make their speed 0
     */
    public void stun() {
        speed = 0;
        stunDuration = 5 * (int) Game.FPS;
    }

    /**
     * Returns if the enemy is stunned
     *
     * @return if the enemy is stunned
     */
    public boolean stunned() {
        return stunDuration > 0;
    }

    /**
     * Draws the enemy to the screen
     *
     * @param g the Graphics to draw the enemy to
     */
    public void drawBody(Graphics g) {
        if (!stunned())
            g.setColor(color);
        else if ((stunDuration < Game.FPS && stunDuration % 10 == 0) ||
                (stunDuration < 2 * Game.FPS && stunDuration % 20 == 0) ||
                (stunDuration < 3 * Game.FPS && stunDuration % 35 == 0)) {
            g.setColor(Color.LIGHT_GRAY);
        } else
            g.setColor(Color.DARK_GRAY);
        g.fillPolygon(this);
    }

    /**
     * Draws the enemy lighting to the screen
     *
     * @param g the Graphics to draw the enemy to
     */
    public void drawLight(Graphics g) {
        if (!stunned())
            g.drawImage(Game.getImage(size, color), getX() - 3 * size / 2, getY() - 3 * size / 2, null);
        // for(int i = 0; i <= size; i++){
        // g.setColor(new Color(color.getRed(),0,0,(int)(150.0/size*i)));
        // g.drawOval(i+getX()-3*size/2,i+getY()-3*size/2, size*3 - i*2, size*3 - i*2);
        // }
    }

    /**
     * Moves the Enemy in its direction with its own speed
     */
    private void move() {
        translate((int) (Math.cos(direction) * speed), (int) (-Math.sin(direction) * speed));
    }

    /**
     * Checks whether the Enemy in shown on the Screen
     *
     * @return is the enemy is on the screen
     */
    public boolean isOnScreen() {
        return intersects(-200, -200, 400 + Game.SCREEN_WIDTH, 400 + Game.SCREEN_HEIGHT);
    }

    /**
     * Sets the whether the Enemy has been seen or not
     *
     * @param boolean if the enemy has been seen
     */
    public void setHasBeenSeen(boolean seen) {
        this.seen = seen;
    }

    /**
     * Gets whether the Enemy has been seen yet
     *
     * @return if the Enemy has been seen
     */
    public boolean hasBeenSeen() {
        return seen;
    }

    /**
     * Approximate collision with a line
     *
     * Fast Approximate if player is dashing through an enemy
     *
     * @param x  starting x coordinate of the line
     * @param y  starting y coordinate of the line
     * @param dx x change of the line
     * @param dy y change of the line
     */
    public boolean approximateCollidesWithLine(double x, double y, double dx, double dy) {
        double steps = 40;
        double dx_step = dx / steps;
        double dy_step = dy / steps;
        for (double i = 0; i < steps; i++) {
            double cdx = getX() - (x + i * dx_step);
            double cdy = getY() - (y + i * dy_step);
            if (cdx * cdx + cdy * cdy < size * size) {
                return true;
            }
        }
        return false;
    }

    /**
     * Collision detection between an enemy and player
     *
     * @param p the Player that needs to be checked for collision
     */
    public boolean collision(Player p) {
        return intersects(p.getX(), p.getY(), p.getSize(), p.getSize());
    }

    /**
     * Collision detection between an enemy and polygon
     *
     * @param p the polygon to check for collision
     */
    public boolean collision(Polygon p) {
        return intersects(p.getBounds2D());
    }

    /**
     * Sets the speed of the current enemy
     *
     * @param s the new speed
     */
    public void setSpeed(int s) {
        speed = s;
    }

    /**
     * Gets the speed of the current enemy
     *
     * @return the current speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * gets the center x of the enemy
     *
     * @return the center x coord of the enemy
     */
    public int getX() {
        return (int) getBounds2D().getCenterX();
    }

    /**
     * gets the center y of the enemy
     *
     * @return the center y coord of the enemy
     */
    public int getY() {
        return (int) getBounds2D().getCenterY();
    }

    public abstract Enemy clone();
}