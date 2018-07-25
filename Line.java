/*
 * Line.java
 * Contain information of drawn line
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import java.awt.Color;
import java.awt.Graphics;

// This class represent line to be drawn on screen
public class Line extends Shape {
	private int x2; // X coordinate for second point in line 
	private int y2; // Y coordinate for second point in line 

	public Line(int x, int y, int x2, int y2, Color color) {
		setX(x);
		setY(y);
		this.x2 = x2;
		this.y2 = y2;
		setColor(color);
	}

	// Paint line on screen
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawLine(getX(), getY(), x2, y2);
	}

}
