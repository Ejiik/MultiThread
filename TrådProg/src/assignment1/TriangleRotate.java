package assignment1;

import java.util.Random;
/**
 * Class that runs a thread, randomizing coordinates for a triangle and then calls drawTriangle-method in the gui.
 * 
 * @author VaskSlem
 *
 */
public class TriangleRotate extends Thread{

	private GUI gui;					// gui that holds the drawTriangle-method.
	private int[] point0 = new int[2];
	private int[] point1 = new int[2];
	private int[] point2 = new int[2];
	private int xMove;					//randomly generated int deciding horizontal movement of the triangle.
	private int yMove;					//randomly generated int deciding vertical movement of the triangle.
	private int xNewPos;				//int storing the triangles new position along the X-axis for every new repetition of the outer loop.
	private int yNewPos;				//int storing the triangles new position along the Y-axis for every new repetition of the outer loop.
	volatile private boolean running = true;
	
	public TriangleRotate(GUI gui){
		this.gui = gui;
	}
	
	public void run(){
		
		Random rand = new Random();
		xNewPos = 0;
		yNewPos = 0;
		
		while(running){
			
			point0[0] = 100 +xNewPos;
			point0[1] = 50 +yNewPos;
	
			point1[0] = 50 +xNewPos;
			point1[1] = 100 +yNewPos;
		
			point2[0] = 150 +xNewPos;
			point2[1] = 100 +yNewPos;
			
			for(int i=0; i<50;i++){
				
				xMove = rand.nextInt(5) -2;
				yMove = rand.nextInt(5) -2;
				
				point0[0] -=1;
				point0[1] +=1;
		
				point1[0] +=2;
			
				point2[0] -=1;
				point2[1] -=1;
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				/*
				 * if-statements for moving the triangle in a random pattern.
				 * Added simply because it makes it a bit more exciting.
				 */
				
				if((point2[0] < 175) && (point2[1] < 175) && (point1[0] > 25) && (point0[1] > 25)){
					point0[0] +=xMove;
					point0[1] +=yMove;
		
					point1[0] +=xMove;
					point1[1] +=yMove;
			
					point2[0] +=xMove;
					point2[1] +=yMove;
					
					xNewPos +=xMove;
					yNewPos +=yMove;
				} if(point2[0] > 175){
					point0[0] -=2;
					
					point1[0] -=2;
					
					point2[0] -=2;
					
					xNewPos -=2;
				} if(point1[0] < 25){
					point0[0] +=2;
		
					point1[0] +=2;
			
					point2[0] +=2;
					
					xNewPos +=2;
				} if(point2[1] > 175){
					point0[1] -=2;
		
					point1[1] -=2;
			
					point2[1] -=2;
					
					yNewPos -=2;
				} if(point0[1] < 25){
					point0[1] +=2;
		
					point1[1] +=2;
			
					point2[1] +=2;
					
					yNewPos +=2;
				}
					
				gui.drawTriangle(point0[0], point0[1], point1[0], point1[1], point2[0], point2[1]);
				if(!running){
					break;
				}
				
			}
		}
	}
	
	/**
	 * Method for stopping the loop of this thread.
	 */
	public void shutdown(){
		running = false;
	}
	
	
	
}
