/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
/**
 * The Gamemode for random spawn
 */
public class RandomGameMode extends GameMode
{
	private int speed;//determines the speed of enemies spawned
	private double spawnDownTime;//determines the time in between spawns in seconds
	private int spawnTick;//in ms/10
	private int onScreen;
	
	
	/**
	 *Creates a Random object which handles the spawning algorithm for the Random gamemode
	 *as the game goes on difficulty will as well in the form of reduced spawn times, increased
	 *enemies and faster enemies.
	 */
	public RandomGameMode()
	{
		onScreen = 8;
		spawnDownTime = 1;
		spawnTick = (int)(spawnDownTime*Game.FPS);
		speed = 10;
	}
	
	/**
	 * spawns in enemies 
	 * increases speed every minute until a cap is reached
	 * increases quantity by a function until a soft cap is reached
	 * spawns more frequently by a function until a soft cap is reached.
	 */
	protected void hordeSpawnUpdate(){
		if(spawnTick++/Game.FPS > spawnDownTime/3 && horde.size() < onScreen)
		{
			int tx = Game.SCREEN_WIDTH/2;
			int ty = Game.SCREEN_HEIGHT/2;
			double radius = Math.sqrt(tx*tx + ty*ty);
			double loc = Math.random()*Math.PI*2;
			int r = (int)(Math.random()*100);
			int size = 60;
			if(r > 95)
				size*=3;	
			else if(r > 85)
				size*=2;
			else if(r > 50)
				size += size/2;
			if(Math.random() > 0.1){
				horde.add(new BasicEnemy(	size,
											speed,
											tx + (int)((radius + size)*Math.cos(loc)),
											ty + (int)((radius + size)*Math.sin(-loc)),
											loc + Math.PI + Math.random()*Math.PI/5 - Math.PI/10));	
			}else{
				horde.add(new ChasingEnemy(	size,
											speed,
											tx + (int)((radius + size)*Math.cos(loc)),
											ty + (int)((radius + size)*Math.sin(-loc)),
											loc + Math.PI + Math.random()*Math.PI/5 - Math.PI/10));
			}
			spawnTick = 0;	
				
		}
		//difficulty increase logic. All will be based on time
		//currently every 10 seconds the speed increases by 2,max 10 and 
		if(frames/Game.FPS%5 ==0 && speed < 30)
		{
			speed+= 1;
		}
		if(frames/Game.FPS%15 == 0)
		{
			onScreen++;
		}
	}
	@Override
	public void addScore(){
		Game.score_Set[0].add(frames/Game.FPS);
	}
	@Override
	public GameMode newGame()
	{
		return new RandomGameMode();
	}
	
	@Override
	public void update(){
		super.update();
		hordeSpawnUpdate();
	}
}