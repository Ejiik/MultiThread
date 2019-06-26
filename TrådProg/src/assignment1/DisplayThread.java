package assignment1;

import java.util.Random;
/**
 * Class that runs a thread, calling the GUI-class to draw a JLabel at random coordinates.
 * @author Erik Johansson
 *
 */
public class DisplayThread extends Thread{

	private GUI gui;
	private volatile boolean running = true; // boolean used to control the thread, stopping it if false.
	
	
	/**
	 * Constructor
	 * @param gui the Graphical User Interface which is to be used to display the JLabel.
	 */
	public DisplayThread(GUI gui){
		this.gui = gui;
	}
	
	
	public void run() {
		Random rand = new Random();
		int x;
		int y;
		while(running){
			x = rand.nextInt(100);
			y = rand.nextInt(180);
			System.out.println(running);
			gui.displayText(x, y);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	/**
	 * Method for stopping the loop of this thread.
	 * 
	 */
	public void shutdown(){
		running = false;
	}

}
