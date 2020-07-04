/*
 * Nicholas Baker, Michael Madrigal, Jonathan Zhao
 * Gallatin 2nd
 * 5/12/19
 */
 
import javax.sound.sampled.*;
import java.io.*;
import java.util.*;
import java.net.URL;

/**
 * Manages the sound in the game
 */
public class SoundManager{
	private float gain;
	private AudioInputStream audioInputSteam;
	private HashMap<String, AudioTrack> soundMap;
	
	/**
	 * Contructs a sound Manager that has a default volume gain value
	 */ 
    public SoundManager(float initialGain){
    	soundMap = new HashMap<String, AudioTrack>();
    	gain = initialGain;
    }
    
    /**
     * Loads a sound into the sound map given a name and a file path
     * @param soundName the name of the sound that will be used at the key in the map
     * @param filename the location of the sound file
     */
    public void LoadSound(String soundName,String filename){
    	try{
    		soundMap.put(soundName, new AudioTrack(getClass().getResource(filename)));
    	}catch(Exception e){
    		System.out.println ("SOUND FILE COULD NOT BE LOADED. " + e.getMessage());
    	}
    }
    
    /**
     * Plays a sound from the soundMap given the sound name in the map
     * @param soundName the name of the sound in the map
     */ 
    public void PlaySound(String soundName){
    	soundMap.get(soundName).play();
    }
    
     /**
     * Resumes a sound from the soundMap given the sound name in the map
     * @param soundName the name of the sound in the map
     */ 
    public void ResumeSound(String soundName){
    	soundMap.get(soundName).resume();
    }
    
    /**
     * Stops a sound from the soundMap given the sound name in the map
     * @param soundName the name of the sound in the map
     */ 
    public void StopSound(String soundName){
    	soundMap.get(soundName).stop();
    }
    /**
     * Sets an audiotrack to loop or not
     * @param soundName the name of the sound in the map
     * @param l loop or not
     */
     public void SetSoundLoop(String soundName, boolean l){
     	soundMap.get(soundName).setLoop(l);
     }
     
    /**
     * Pauses all the Tracks in the SoundMap
     */
    public void StopAllSounds(){
    	for(String key: soundMap.keySet()){
    		soundMap.get(key).stop();
    	}	
    }
    /**
     * Pauses all the Tracks in the SoundMap
     */
    public void PauseAllSounds(){
    	for(String key: soundMap.keySet()){
    		AudioTrack t = soundMap.get(key);
    		if(t.clip.isActive()){
    			t.pause();
    		}	
    	}	
    }
    /**
     * Starts all the Tracks in the SoundMap
     */
    public void ResumeAllSounds(){
    	for(String key: soundMap.keySet()){
    		AudioTrack t = soundMap.get(key);
    		if(t.isPaused){
    			t.resume();
    		}	
    	}	
    }
    
    /**
     * Restarts all the Tracks in the SoundMap to update with any changes
     */
    private void updateSoundMap(){
    	for(String key: soundMap.keySet()){
    		AudioTrack t = soundMap.get(key);
    		if(t.clip.isActive()){
    			t.pause();
    			t.resume();
    		}	
    	}
    }
    /**
     * sets the gain of the audio to make it louder or quiter
     * @param f the float to set the gain to
     */
    public void setGain(float f){
    	gain = f;
    }
    /**
     * alters the gain of the audio to make it louder or quiter
     * @param dif the float to increase or decrease the gain by
     */
    public void changeGain(float dif){
    	gain += (gain+dif > 5f || gain+dif < -80f? 0:dif);
    	updateSoundMap();
    }
    /**
     * Returns the current gain of the sound mananger
     * @return the current gain float of the manager
     */
    public float getGain(){
    	return gain;
    }
    
    /**
     * Holder of file and frame that plays the audio files
     */ 
    class AudioTrack{
		Clip clip;
		boolean isLooping, isPaused;
		/**
		 * Constructs and AudioTrack given a URL to the audio file
		 * @param u the URL to the file
		 */ 
		public AudioTrack(URL u){
			isLooping = isPaused = false;
			try{
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(u));	
			}catch(Exception e){
				System.out.println ("COULD NOT RESET AUDIO." + e.getMessage());
			}
		}
		
		/**
		 * Sets whether the sound should loop or not
		 * @param l if the sound should loop
		 */
		public void setLoop(boolean loop){
			isLooping = loop;
		}
		
		/**
		 * Plays the sound that is held by the Audiotrack after setting the gain
		 */ 
		public void play(){
			((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(gain);
		 	isPaused = false;
		 	clip.stop();
		 	clip.setMicrosecondPosition(0);
		 	clip.start();
		}
		/**
		 * Stops the audiotrack
		 */ 
		public void stop(){
			isPaused = false;
			clip.stop();
			clip.setMicrosecondPosition(0);
		}
		/**
		 * Pauses the audiotrack
		 */ 
		public void pause(){
			isPaused = true;
			clip.stop();
		}
		/**
		 * Resumes the audiotrack
		 */
		public void resume(){
		 	if(isPaused){
			 	((FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(gain);
			 	isPaused = false;
			 	clip.start();	
		 	}
		}
	}
}
