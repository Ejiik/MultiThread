package assignment1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 * 
 * Custom made JPanel, used to override the paintComponent-method in order to draw a Triangle at given coordinates.
 * @author VaskSlem
 *
 */
public class DrawTrianglePanel extends JPanel{

	private int[] pos = new int[6];

	/**
	 * Method receiving int coordinates and calling the repaint method, which draws a triangle from given coordinates.
	 * @param x0 position of point 1 along the X-axis
	 * @param y0 position of point 1 along the Y-axis
	 * @param x1 position of point 2 along the X-axis
	 * @param y1 position of point 2 along the Y-axis
	 * @param x2 position of point 3 along the X-axis
	 * @param y2 position of point 3 along the Y-axis
	 */
	public void drawTriangle(int x0,int y0, int x1, int y1, int x2, int y2 ){
		pos[0] = x0;
		pos[1] = y0;
		pos[2] = x1;
		pos[3] = y1;
		pos[4] = x2;
		pos[5] = y2;
		repaint();
	}
	
	/**
	 * overridden paintComponent-method that has been customized to draw a triangle.
	 * gets called when the JPanels repaint-method is called.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.drawLine(pos[0], pos[1], pos[2], pos[3]);
		g2.drawLine(pos[2], pos[3], pos[4], pos[5]);
		g2.drawLine(pos[4], pos[5], pos[0], pos[1]);
		
	}
}
