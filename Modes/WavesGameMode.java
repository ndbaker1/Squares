/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
import java.util.*;
import java.util.concurrent.atomic.*;

/**
 * A Gammode that Contains waves of Enemies
 */ 
public class WavesGameMode extends GameMode
{
	private final int TIMESTAMP_LAST_WAVE = introTime + 214;
	private static HashMap<Integer, HashSet<Enemy>> waves;
	private static int intros = 0;
	private static int introTime = 43;//seconds

	/**
	 * Creates a Wave Gamemode
	 */
	public WavesGameMode()
	{
	    waves = wavesClone(Game.waves);
	}
	/**
	 * Loads all the Enemies into a given HashMap
	 * @param waves the HashMap to load all enemies into
	 * @param AString the string reference to update in accordance with the current state of the loading
	 */
	public static void loadLevels(HashMap<Integer, HashSet<Enemy>> waves, AtomicReference<String> AString){
		HashSet<Enemy> temp = new HashSet<Enemy>();
    	AString.set("Loading Intro . . .");
   		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'c','b','b','b','b','b','b','b','b','b'}//S
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(1,temp);
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b'}//Q
																	,{'b','b','b','x','x','x','x','b','b','b'}
																	,{'b','b','x','x','x','x','x','x','b','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','x','x','b'}
																	,{'b','x','x','b','b','b','b','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','b','x','x','x','x','x','b','b','b'}
																	,{'b','b','b','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(7,temp);
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b'}//U
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(13,temp);
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b'}//A
																	,{'b','b','b','x','x','x','x','b','b','b'}
																	,{'b','b','x','x','x','x','x','x','b','b'}
																	,{'b','b','x','x','b','b','x','x','b','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','b','b','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','x','x','b'}
																	,{'b','x','x','b','b','b','b','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(19,temp);
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b'}//R
																	,{'b','x','x','x','x','x','x','b','b','b'}
																	,{'b','x','x','x','x','x','x','x','b','b'}
																	,{'b','x','x','b','b','b','x','x','b','b'}
																	,{'b','x','x','b','b','b','x','x','b','b'}
																	,{'b','x','x','x','x','x','x','x','b','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','b','b','b','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(25,temp);
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]{{'b','b','b','b','b','b','b','b','b','b'}//E
																,{'b','b','b','b','b','x','x','x','x','b'}
																,{'b','x','x','x','x','x','x','x','x','b'}
																,{'b','x','x','x','x','x','x','x','x','b'}
																,{'b','b','b','b','b','x','x','x','x','b'}
																,{'b','b','b','b','b','x','x','x','x','b'}
																,{'b','x','x','x','x','x','x','x','x','b'}
																,{'b','x','x','x','x','x','x','x','x','b'}
																,{'b','b','b','b','b','x','x','x','x','b'}
																,{'b','b','b','b','b','b','b','b','b','b'}},0,0,0));
		waves.put(31,temp);
		temp = new HashSet<Enemy>();

   		temp.addAll(getCustomFormation(100,5,Math.PI,new char[][]	{{'c','b','b','b','b','b','b','b','b','b'}//S
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b'}},10,0,0));
		waves.put(37,temp);
//////////////////////////////////////////////////levels logic/////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
		AString.set("Loading Wave One . . .");//Enemies coming in regularly
		temp = new HashSet<Enemy>();
		temp.add(new BasicEnemy(60,10,Game.SCREEN_WIDTH,Game.SCREEN_HEIGHT/2-50,Math.PI));
		temp.add(new BasicEnemy(60,10,-300,Game.SCREEN_HEIGHT+100,Math.PI/4));
		temp.add(new BasicEnemy(60,10,+200,Game.SCREEN_HEIGHT+50,Math.PI*3/8));
		waves.put(1+introTime,temp);

		AString.set("Loading Wave Two . . .");
		temp = new HashSet<Enemy>();
		temp.add(new BasicEnemy(60,10,Game.SCREEN_WIDTH/2,Game.SCREEN_HEIGHT,Math.PI/6));
		temp.add(new BasicEnemy(60,10,Game.SCREEN_WIDTH+50,Game.SCREEN_HEIGHT*3/4,Math.PI));
		temp.add(new BasicEnemy(60,10,-70,Game.SCREEN_HEIGHT/4,Math.PI*2));
		waves.put(2+introTime,temp);

		AString.set("Loading Wave Three . . .");//The mini walls
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(60, 7,Math.PI,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,0));
		temp.addAll(getCustomFormation(60, 7,Math.PI,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,-200,300));
		temp.addAll(getCustomFormation(60, 7,Math.PI,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,200,600));
		waves.put(5+introTime,temp);

		AString.set("Loading Wave Four . . .");//The mini walls coming in from corners
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(60, 7,Math.PI*3/4,new char[][]{{'x','x','b'},{'x','b','x'},{'b','x','x'}},10,0,0));
		temp.addAll(getCustomFormation(60, 7,Math.PI/4,new char[][]{{'b','x','x'},{'x','b','x'},{'x','x','b'}},10,0,300));
		temp.addAll(getCustomFormation(60, 7,-Math.PI/4,new char[][]{{'x','x','b'},{'x','b','x'},{'b','x','x'}},10,0,600));
		temp.addAll(getCustomFormation(60, 7,-Math.PI*3/4,new char[][]{{'b','x','x'},{'x','b','x'},{'x','x','b'}},10,0,900));
		waves.put(9+introTime,temp);

		AString.set("Loading Wave Five . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(60, 7,Math.PI*3/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,0));
		temp.addAll(getCustomFormation(60, 7,Math.PI/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,300));
		temp.addAll(getCustomFormation(60, 7,-Math.PI/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,600));
		temp.addAll(getCustomFormation(60, 7,-Math.PI*3/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,900));
		waves.put(15+introTime,temp);

		AString.set("Loading Wave Six . . .");//The surge of mini walls
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(60, 12,Math.PI*3/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,0));
		temp.addAll(getCustomFormation(60, 12,Math.PI/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,200));
		temp.addAll(getCustomFormation(60, 12,-Math.PI/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,400));
		temp.addAll(getCustomFormation(60, 12,-Math.PI*3/4,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,600));
		temp.addAll(getCustomFormation(60, 12,0,new char[][]{{'x','x','x'},{'b','b','b'},{'x','x','x'}},10,0,800));
		temp.addAll(getCustomFormation(60, 12,Math.PI/2,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,800));
		temp.addAll(getCustomFormation(60, 12,Math.PI,new char[][]{{'x','x','x'},{'b','b','b'},{'x','x','x'}},10,0,800));
		temp.addAll(getCustomFormation(60, 12,Math.PI*3/2,new char[][]{{'x','b','x'},{'x','b','x'},{'x','b','x'}},10,0,800));
		waves.put(21+introTime,temp);

		AString.set("Loading Wave Seven . . .");//The first spiral wave
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, 0, Math.PI*2, Math.PI/6, 20,1));
		waves.put(25+introTime,temp);

		AString.set("Loading Wave Eight . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, -Math.PI/4, Math.PI/4, Math.PI/14, 20,1));//Enemies in arc waves rapidly
		waves.put(30+introTime,temp);

		AString.set("Loading Wave Nine . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, Math.PI*3/4, Math.PI*5/4, Math.PI/12, 20,1));
		waves.put(33+introTime,temp);

		AString.set("Loading Wave Ten . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, -Math.PI/4, Math.PI/4, Math.PI/14, 20,1));
		waves.put(35+introTime,temp);

		AString.set("Loading Wave Eleven . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, Math.PI*3/4, Math.PI*5/4, Math.PI/12, 20,1));
		waves.put(36+introTime,temp);

		AString.set("Loading Wave Twelve . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(60, 12, -Math.PI/4, Math.PI/4, Math.PI/14, 20,1));
		waves.put(37+introTime,temp);

		AString.set("Loading Wave Thirteen . . .");//The first hole into more walls with holes
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 5, 3,4,100));
		waves.put(43+introTime,temp);

		AString.set("Loading Wave Fourteen . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 5, 3,1,100));
		waves.put(49+introTime,temp);

		AString.set("Loading Wave Fifteen . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 5, 3,2,100));
		waves.put(52+introTime,temp);

		AString.set("Loading Wave Sixteen . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 6, 3,1,100));
		waves.put(55+introTime,temp);

		AString.set("Loading Wave Seventeen . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 6, 3,4,100));
		waves.put(57+introTime,temp);

		AString.set("Loading Wave Eighteen . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getHoleForm(200, 6, 3,1,100));
		waves.put(58+introTime,temp);

		AString.set("Loading Wave Nineteen . . .");// The first wall for the player to dash through. Waves after this may or may not require the dash.
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(150, 5, 3,0));
		waves.put(60+introTime,temp);

		AString.set("Loading Wave Twenty . . .");//walls begin to get smaller and faster and more frequent
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(100, 5, 4,20));
		waves.put(65+introTime,temp);

		AString.set("Loading Wave Twenty-One . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(60, 5, 1,10));
		waves.put(66+introTime,temp);

		AString.set("Loading Wave Twenty-Two . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(20, 7, 2,5));
		waves.put(67+introTime,temp);

		AString.set("Loading Wave Twenty-Three . . .");//walls get faster for the climax
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(20, 9, 3,0));
		waves.put(68+introTime,temp);

		AString.set("Loading Wave Twenty-Four . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(20, 12, 1,0));
		temp.addAll(getWallForm(20, 12, 3,0));
		waves.put(70+introTime,temp);

		AString.set("Loading Wave Twenty-Five . . .");//climax. Walls of various types and speeds.
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(20, 12, 2,0));
		temp.addAll(getWallForm(20, 12, 4,0));
		waves.put(73+introTime,temp);

		AString.set("Loading Wave Twenty-Six . . .");
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(20, 12, 3,0));
		temp.addAll(getWallForm(20, 10, 2,0));
		waves.put(74+introTime,temp);

		AString.set("Loading Wave Twenty-Seven . . .");//fast diagonal walls. Still climaxing
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(20, 14, 1,0));
		temp.addAll(getDiagonalWallForm(20, 14, 2,0));
		temp.addAll(getDiagonalWallForm(20, 14, 3,0));
		temp.addAll(getDiagonalWallForm(20, 14, 4,0));
		waves.put(75+introTime,temp);

		AString.set("Loading Wave Twenty-Eight . . .");//Slow diagonal encasement.
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(60, 5, 1,10));
		temp.addAll(getDiagonalWallForm(60, 5, 2,10));
		temp.addAll(getDiagonalWallForm(60, 5, 3,10));
		temp.addAll(getDiagonalWallForm(60, 5, 4,10));
		waves.put(76+introTime,temp);

		AString.set("Loading Wave Twenty-Nine . . .");//Refresh and introduce the chasing enemy
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(0,5,Math.PI,new char[][] {{'b','b','b','b','b'}
																,{'b','x','x','x','b'}
																,{'b','x','c','x','b'}
																,{'b','x','x','x','b'}
																,{'b','b','b','b','b'}}, 20, 0, 0));
		waves.put(84+introTime,temp);

		AString.set("Loading Wave Thirty . . .");//More chasers and empty 'cages'
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(0,8,Math.PI/6,new char[][] 	{{'b','b','b','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','c','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		temp.addAll(getCustomFormation(0,8,-Math.PI/4,new char[][] 	{{'b','b','x','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		waves.put(90+introTime,temp);

		AString.set("Loading Wave Thirty-One . . .");//More chasers and empty 'cages'
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(0,8,Math.PI*3/2,new char[][] {{'b','b','b','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','x'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		temp.addAll(getCustomFormation(0,8,Math.PI*3/4,new char[][] {{'b','b','x','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		waves.put(96+introTime,temp);

		AString.set("Loading Wave Thirty-Two . . .");//Even More chasers and empty 'cages'
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(0,10,Math.PI/6,new char[][] {{'b','b','b','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','x'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		temp.addAll(getCustomFormation(0,10,-Math.PI*5/6,new char[][] {{'b','b','x','b','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		temp.addAll(getCustomFormation(0,10,-Math.PI/2,new char[][] {{'b','b','b','b','b'}
																	,{'b','x','x','x','b'}
																	,{'x','x','x','x','b'}
																	,{'b','x','x','x','b'}
																	,{'b','b','b','b','b'}}, 20, 0, 0));
		waves.put(99+introTime,temp);

		AString.set("Loading Wave Thirty-Three . . .");//Even More chasers and empty 'cages'
		temp = new HashSet<Enemy>();
		for(int i = 1;i<=4;i++)
		{
			temp.addAll(getCustomFormation(0,10,Math.PI*(2*i-1)/4,new char[][] 	{{'b','b','b','b','b'}
																				,{'x','x','x','x','b'}
																				,{'b','x','x','x','b'}
																				,{'b','x','x','x','x'}
																				,{'x','b','b','b','b'}}, 20, 0, 0));
		}
		temp.add(new ChasingEnemy(60, 14,Game.SCREEN_WIDTH,200,Math.PI/4 ));
		temp.add(new ChasingEnemy(60, 14,-100,-50,Math.PI*3/4 ));
		temp.add(new ChasingEnemy(60, 14,-60,Game.SCREEN_HEIGHT*3/4,Math.PI*5/4 ));
		temp.add(new ChasingEnemy(60, 14,Game.SCREEN_WIDTH+100,Game.SCREEN_HEIGHT,Math.PI*7/4 ));
		waves.put(102+introTime,temp);

		AString.set("Loading Wave Thirty-Four . . .");//Climax of empty cages
		temp = new HashSet<Enemy>();
		for(int i = 1;i<=8;i++)
		{
			temp.addAll(getCustomFormation(0,11,Math.PI*i/4,new char[][] 	{{'x','b','x','b','x'}
																			,{'b','x','x','x','b'}
																			,{'x','x','x','x','b'}
																			,{'b','x','x','x','x'}
																			,{'b','x','b','x','b'}}, 20, 0, 0));
		}
		waves.put(105+introTime,temp);

		AString.set("Loading Wave Thirty-Five . . .");//Encirclement of chasers
		temp = new HashSet<Enemy>();
		temp.addAll(getCircleForm(0,9,0,Math.PI*2,Math.PI/6,0,2));
		waves.put(109+introTime,temp);

		AString.set("Loading Wave Thirty-Six . . .");//Maze of Enemies
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(20,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','b','x','x','x','x','x','b','x','x','x','x','x','x','x','x','b','x','b'}
																	,{'b','x','b','x','x','x','x','x','b','x','b','b','x','x','x','x','x','x','x','b'}
																	,{'b','x','b','x','x','x','x','x','b','x','x','b','b','x','x','b','x','x','x','b'}
																	,{'b','x','b','x','c','x','x','x','b','x','x','x','x','x','x','b','b','x','x','b'}
																	,{'b','x','b','x','b','x','x','x','x','x','x','x','x','x','x','x','b','x','x','b'}
																	,{'b','x','b','x','x','x','x','x','x','x','x','b','b','x','x','x','x','b','x','b'}
																	,{'b','x','b','x','b','b','b','b','x','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','b','x','b','c','b','b','x','x','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','c','x','b','b','b','b','b','b','b','b','c','b','b','b','b','b'}
																	,{'b','x','x','x','b','b','b','b','b','x','x','b','b','c','b','b','b','b','b','b'}
																	,{'b','x','b','x','b','x','c','b','b','b','b','x','b','b','b','b','b','b','b','b'}
																	,{'b','x','b','x','b','b','b','b','b','b','c','x','b','b','b','b','b','b','b','b'}
																	,{'b','x','b','x','x','c','x','x','c','x','c','x','x','c','x','c','x','c','x','b'}
																	,{'b','x','x','x','x','c','x','x','c','x','c','x','x','c','x','x','x','c','x','b'}
																	,{'b','x','x','x','x','c','x','x','c','x','c','x','x','c','x','x','x','c','x','x'}
																	,{'b','x','x','x','x','c','x','x','c','x','c','x','x','c','x','x','x','x','x','x'}
																	,{'b','x','x','x','x','c','x','x','c','x','c','x','x','c','x','x','x','c','x','b'}
																	,{'b','x','x','x','x','x','x','x','x','x','c','x','x','x','x','c','x','c','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}},40,0,0));
		waves.put(114+introTime,temp);

		AString.set("Loading Wave Thirty-Seven . . .");//Maze two
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(20,5,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','b','x','x','b','x','b','b','x','x','b','x','x','b','x','x','x','b'}
																	,{'b','x','x','b','x','x','b','x','b','b','x','x','b','x','x','b','x','x','x','b'}
																	,{'x','x','x','b','x','x','b','x','b','b','x','x','b','x','x','b','x','b','x','b'}
																	,{'b','x','x','b','x','x','b','x','x','b','x','b','b','x','x','b','x','x','x','b'}
																	,{'b','x','x','b','x','x','b','x','x','b','x','b','b','x','x','b','x','x','x','b'}
																	,{'x','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','b','b','x','x','b','x','x','x','x','b','c','c','c','x','x','x','x'}
																	,{'x','x','x','x','b','x','x','b','x','x','x','x','c','x','x','x','x','x','x','x'}
																	,{'b','x','x','x','c','x','x','b','x','x','x','x','c','x','x','x','c','x','x','x'}
																	,{'b','x','x','x','b','x','x','c','x','x','x','x','c','x','x','x','c','x','x','x'}
																	,{'x','x','x','x','b','x','x','b','x','x','x','x','c','x','x','x','x','x','x','x'}
																	,{'b','x','x','b','b','x','x','b','x','x','x','x','b','c','c','c','x','x','x','x'}
																	,{'b','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'x','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','x','x','x','b','x','x','b','x','x','x','b','x','x','x','x','b'}
																	,{'b','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}},40,0,0));
		waves.put(121+introTime,temp);

		AString.set("Loading Wave Thirty-Eight . . .");//Maze three
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(20,3,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}},40,0,0));
		waves.put(128+introTime,temp);

		AString.set("Loading Wave Thirty-Nine . . .");//Maze three Extended
		temp = new HashSet<Enemy>();
		temp.addAll(getCustomFormation(20,3,Math.PI,new char[][]	{{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','x','x','x','b','x','x','x','b','x','x','x','b','x','x','b','b','x','x','b'}
																	,{'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'}},40,0,0));
		temp.addAll(getWallForm(30,4,2,0));
		waves.put(137+introTime,temp);

		AString.set("Loading Wave Thirty-Nine . . .");//first wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(30,4,2,0));
		waves.put(135+introTime,temp);

		AString.set("Loading Wave Forty . . .");//Second wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(30,4,4,0));
		waves.put(136+introTime,temp);

		AString.set("Loading Wave Forty-One . . .");//Fourth wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getWallForm(30,4,4,0));
		waves.put(138+introTime,temp);

		AString.set("Loading Wave Forty-Two . . .");//first diagonal wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(30,6,1,0));
		waves.put(141+introTime,temp);

		AString.set("Loading Wave Forty-Three . . .");//Second diagonal wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(30,6,3,0));
		waves.put(142+introTime,temp);

		AString.set("Loading Wave Forty-Four . . .");//Third diagonal wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(30,6,4,0));
		waves.put(143+introTime,temp);

		AString.set("Loading Wave Forty-Five . . .");//Fourth diagonal wall during maze
		temp = new HashSet<Enemy>();
		temp.addAll(getDiagonalWallForm(30,6,2,0));
		waves.put(144+introTime,temp);

		AString.set("Loading Wave Forty-Six . . .");//small sqaures from all sides
		temp = new HashSet<Enemy>();
		for(int i = 1;i<=4;i++)
		{
			temp.addAll(getCustomFormation(20,3,Math.PI*i/2,new char[][]	{{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																			,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																			,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}},60,0,0));
		}
		waves.put(148+introTime,temp);

		AString.set("Loading Wave Forty-Seven . . .");//small sqaures from all Corners
		temp = new HashSet<Enemy>();
		for(int i = 1;i<=4;i++)
		{
			temp.addAll(getCustomFormation(20,5,Math.PI*(2*i-1)/4,new char[][]	{{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}
																				,{'x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x'}
																				,{'b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b','x','b'}},90,0,0));
		}
		waves.put(163+introTime,temp);

		AString.set("Loading Wave Forty-Eight . . .");//Walls incoming
		temp = new HashSet<Enemy>();
		for(int i = 0;i<60;i++)
		{
			temp.addAll(getWallForm(30,14,i%4+1,20));
			waves.put(180+introTime+i/4,temp);
		}

		AString.set("Loading Wave Forty-Nine . . .");//Diagonal Walls incoming
		temp = new HashSet<Enemy>();
		for(int i = 0;i<60;i++)
		{
			temp.addAll(getDiagonalWallForm(30,15,i%4+1,20));
			waves.put(199+introTime+i/4,temp);
		}

		AString.set("Sakusen Kaishi");
	}


	/**
	 * Returns a clone of the Enemy waves from the Games's Master Copy
	 * @param waves_template the waves to copy
	 * @return the copied HashMap of waves
	 */
	private HashMap<Integer, HashSet<Enemy>> wavesClone(HashMap<Integer, HashSet<Enemy>> waves_template){//remove the number in offset+number. Currently 75
		HashMap<Integer, HashSet<Enemy>> clone = new HashMap<Integer, HashSet<Enemy>>();
		int offset = (intros-- > 0?0:introTime);
		for(int i = offset;i++ <= TIMESTAMP_LAST_WAVE;){
		    if(waves_template.containsKey(i)){
    		    HashSet<Enemy> temp = new HashSet<Enemy>();
    			for(Enemy e: waves_template.get(i)){
    				temp.add(e.clone());
    			}
    			clone.put(i-offset,temp);
		    }
		}
		return clone;
	}

	/**
	 * Forms walls for the levels. Input 0 for default speed, size, and type
	 * @param size the size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param form the direction the wall will come from [1-4 for walls ## 1 for the left wall]
	 * @param space the space between Enemies
	 * @return a Set of Enemies forming the wall
	 */
	private static HashSet<Enemy> getWallForm(int size, int speed,int form, int space)
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
		int s = size == 0?Enemy.DEFAULT_SIZE:size;
		if(form == 1)//from left
		{
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,-s,i,0));
			}
		}
		if(form == 2)//from top
		{
			double dir = Math.PI/2*3;
			int y = -s;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		if(form == 3)//from right
		{
			double dir = Math.PI;
			int x = Game.SCREEN_WIDTH + s;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,x,i,dir));
			}
		}
		if(form == 4)//from bottom
		{
			double dir = Math.PI/2;
			int y = Game.SCREEN_HEIGHT + s;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s + space)
			{
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		return list;
	}
	/**
	 * Forms diagonal walls for the levels *0 for default speed, size, and type
	 * @param size the size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param form the direction the wall will come from [1-4 for walls ## 1 for the upper left wall]
	 * @param space the space between Enemies
	 * @return a Set of Enemies forming the wall
	 */
	private static HashSet<Enemy> getDiagonalWallForm(int size, int speed,int form, int space)
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		if(form == 1)
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int x = 0; x < Game.SCREEN_WIDTH;x+= s + space )
			{
				list.add(new BasicEnemy(s,speed==0?Enemy.DEFAULT_SPEED:speed,
										x-Game.SCREEN_WIDTH/2,
										-(int)((double)Game.SCREEN_HEIGHT/Game.SCREEN_WIDTH*x) + Game.SCREEN_HEIGHT/2,
										-Math.atan2(Game.SCREEN_HEIGHT,Game.SCREEN_WIDTH)));
			}
		}
		if(form == 2)
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int x = 0; x < Game.SCREEN_WIDTH;x+= s + space )
			{
				list.add(new BasicEnemy(s,speed==0?Enemy.DEFAULT_SPEED:speed,
										x+Game.SCREEN_WIDTH/2,
										(int)((double)Game.SCREEN_HEIGHT/Game.SCREEN_WIDTH*x) - Game.SCREEN_HEIGHT/2,
										Math.atan2(Game.SCREEN_HEIGHT,Game.SCREEN_WIDTH)-Math.PI));
			}
		}
		if(form == 3)
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int x = 0; x < Game.SCREEN_WIDTH;x+= s + space )
			{
				list.add(new BasicEnemy(s,speed==0?Enemy.DEFAULT_SPEED:speed,
										x+Game.SCREEN_WIDTH/2,
										-(int)((double)Game.SCREEN_HEIGHT/Game.SCREEN_WIDTH*x) + Game.SCREEN_HEIGHT/2*3,
										-Math.atan2(Game.SCREEN_HEIGHT,Game.SCREEN_WIDTH) + Math.PI));
			}
		}
		if(form == 4)
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int x = 0; x < Game.SCREEN_WIDTH;x+= s + space )
			{
				list.add(new BasicEnemy(s,speed==0?Enemy.DEFAULT_SPEED:speed,
										x-Game.SCREEN_WIDTH/2,
										(int)((double)Game.SCREEN_HEIGHT/Game.SCREEN_WIDTH*x) + Game.SCREEN_HEIGHT/2,
										Math.atan2(Game.SCREEN_HEIGHT,Game.SCREEN_WIDTH)));
			}
		}

		return list;
	}
	/**
	 * Makes a Wall with a hole in it that you don't have to dash through
	 * @param size the size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param form the direction the wall will come from [1-4 for walls ## 1 for the upper left wall]
	 * @param holeIndex the position where the hole will be
	 * @param holeSize the size of the hole
	 * @return the Set of Enemies forming the wall
	 */
	private static HashSet<Enemy> getHoleForm(int size, int speed,int form, int holeIndex, int holeSize)
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		if(form == 1)//from left
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s)
			{
				if(i==holeIndex*s)
					i+=holeSize;
				list.add(new BasicEnemy(s,speed == 0?Enemy.DEFAULT_SPEED:speed,-s,i,0));
			}
		}
		if(form == 2)//from top
		{
			double dir = Math.PI/2*3;
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			int y = -s;
			int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s)
			{
				if(i==holeIndex*s)
					i+=holeSize;
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		if(form == 3)//from right
		{
			double dir = Math.PI;
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			int x = Game.SCREEN_WIDTH + s;
			int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s)
			{
				if(i==holeIndex*s)
					i+=holeSize;
				list.add(new BasicEnemy(s,spd,x,i,dir));

			}
		}
		if(form == 4)//from bottom
		{
			double dir = Math.PI/2;
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			int y = Game.SCREEN_HEIGHT + s;
			int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s)
			{
				if(i==holeIndex*s)
					i+=holeSize;
				list.add(new BasicEnemy(s,spd,i,y,dir));
			}
		}
		return list;
	}
	/**
	 * Makes a Wall with a hole in it that you have to dash through
	 * __to make the wall impenetrable make the squares large enough__
	 * @param size the size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param form the direction the wall will come from [1-4 for walls ## 1 for the upper left wall]
	 * @param holeIndex the position where the hole will be
	 * @param holeSize the size of the hole
	 * @param bridgeWidth the width of the bridge
	 * @return the Set of Enemies forming the wall
	 */
	private static HashSet<Enemy> getBarrierForm(int size, int speed,int form, int holeIndex, int holeSize, int bridgeWidth)
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
		if(form == 1)//from left
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s)
			{
				if(i==holeIndex*s)
				{
					for(int j = i-bridgeWidth;j<i+holeSize;j+=bridgeWidth)
						list.add(new BasicEnemy(bridgeWidth,spd,-s,j,0));
					i+=holeSize;
				}
				list.add(new BasicEnemy(s,spd,-s,i,0));
			}
		}
		if(form == 2)//from top
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s)
			{
				if(i==holeIndex*s)
				{
					for(int j = i-bridgeWidth;j<i+holeSize;j+=bridgeWidth)
						list.add(new BasicEnemy(bridgeWidth,spd,j,-s,Math.PI/2*3));
					i+=holeSize;
				}
				list.add(new BasicEnemy(s,spd,i,-s,Math.PI/2*3));
			}
		}
		if(form == 3)//from right
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int i = 0; i < Game.SCREEN_HEIGHT + s; i+= s)
			{
				if(i==holeIndex*s)
				{
					for(int j = i-bridgeWidth;j<i+holeSize;j+=bridgeWidth)
						list.add(new BasicEnemy(bridgeWidth,spd,Game.SCREEN_WIDTH + s,j,Math.PI));
					i+=holeSize;
				}
				list.add(new BasicEnemy(s,spd,Game.SCREEN_WIDTH + s,i,Math.PI));

			}
		}
		if(form == 4)//from bottom
		{
			int s = size == 0?Enemy.DEFAULT_SIZE:size;
			for(int i = 0; i < Game.SCREEN_WIDTH + s; i+= s)
			{
				if(i==holeIndex*s)
				{
					for(int j = i-bridgeWidth;j<i+holeSize;j+=bridgeWidth)
						list.add(new BasicEnemy(bridgeWidth,spd,j,Game.SCREEN_HEIGHT + s,Math.PI/2));
					i+=holeSize;
				}
				list.add(new BasicEnemy(s,spd,i,Game.SCREEN_HEIGHT + s,Math.PI/2));
			}
		}
		return list;
	}
	/**
	 * Makes a Circle of Enemies
	 * @param size the Size of the Enemies
	 * @param speed the speed of the Enemies
	 * @param start TODO
	 * @param end TODO
	 * @param space The angle space the between the Enemies in Radians
	 * @param spiral TODO
	 */
	private static HashSet<Enemy> getCircleForm(int size, int speed, double start, double end, double space, int spiral,int type)//rotation will rotate it left from positive x axis
	{
		HashSet<Enemy> list = new HashSet<Enemy>();
		double radius = Math.sqrt(Math.pow(Game.SCREEN_HEIGHT/2,2) + Math.pow(Game.SCREEN_WIDTH/2,2));
		double r = radius;
		int s = size == 0?Enemy.DEFAULT_SIZE:size;
		int j = 1;
		for(double i = start;i<=end;i+= (Math.sqrt(2)*s)/radius + space)
		{
			r += j*spiral;
			int y = (int)(Math.sin(-i)*r+Game.SCREEN_HEIGHT/2);
			int x = (int)(Math.cos(i)*r+Game.SCREEN_WIDTH/2);
			double dir = i+Math.PI;
			if(type == 1)
				list.add( new BasicEnemy 	(s,speed == 0?Enemy.DEFAULT_SPEED:speed
											,x,y,dir));
			else
				list.add( new ChasingEnemy 	(s,speed == 0?Enemy.DEFAULT_SPEED:speed
											,x,y,dir));
			j++;
		}
		return list;
	}
	/**
	 *gets a custom formation of uniform squares.
	 *They may be of different types but will have the same speed, size, and direction
	 *They originally will be placed so that they will cross the center of the screen but can be offset
	 *The string should be treated as a 2D array of characters with rows the amount of rows being given.
	 *offset will adjust how far off the center the formation will be. It offsets it by the given magnitude on a tangent line
	 *the array must be a square
	 * @param size
	 * @param speed
	 * @param direction
	 * @param data
	 * @param space
	 * @param offSet
	 */
	private static HashSet<Enemy> getCustomFormation(int size, int speed, double direction, char[][] data, int space, int offSet,int depth)
	{
		HashSet list = new HashSet<Enemy>();
		int spd = speed == 0?Enemy.DEFAULT_SPEED:speed;
		int s = size == 0?Enemy.DEFAULT_SIZE:size;
		double radius = Math.sqrt(Math.pow(Game.SCREEN_HEIGHT/2,2) + Math.pow(Game.SCREEN_WIDTH/2,2)) + (space + size)*data.length + depth;
		int y = (int)(Math.sin(-direction+Math.PI)*radius+Game.SCREEN_HEIGHT/2 - Math.sin(-direction + Math.PI/2)*offSet - (s*(data.length-1)+(data.length-1)*space)/2);
		int x = (int)(Math.cos(direction+Math.PI)*radius+Game.SCREEN_WIDTH/2 + Math.cos(direction + Math.PI/2)*offSet - (s*(data.length-1)+(data.length-1)*space)/2);
		int tx = x;
		for(int i = 0; i <data.length*data.length;i++)
		{
			if(data[i/data.length][i%data.length] == 'b')
				list.add(new BasicEnemy(s,spd,tx,y,direction));
            else if(data[i/data.length][i%data.length] == 'c')
				list.add(new ChasingEnemy(s,spd,tx,y,direction));
            if(i!=0 && (i+1)%data.length==0)
			{
				y+=space+s;
				tx=x;
			}
			else
				tx+=space+s;
		}
		return list;
	}
	
	@Override
	public void addScore()
	{
		Game.score_Set[1].add(frames/Game.FPS);
	}
	@Override
	public GameMode newGame()
	{
		return new WavesGameMode();
	}

	@Override
	public void update()
	{
		super.update();
		int i  = (frames/Game.FPS)%1 == 0 ? (int)(frames/Game.FPS) : -1;
		if(i != -1 && waves.get(i) != null)
		{
			horde.addAll(waves.remove(i));
		}
	}
}