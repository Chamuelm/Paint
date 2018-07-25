/*
 * Oval.java
 * Contain information of drawn oval
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import java.awt.Color;
import java.awt.Graphics;

// This class represents oval to be drawn on the screen
public class Oval extends Shape {
	private int width;
	private int height;
	private boolean filled; 

	public Oval(int x, int y, int width, int height, boolean filled, Color color) {
		setX(x);
		setY(y);
		this.width = width;
		this.height = height;
		this.filled = filled;
		setColor(color);
	}

	// Paint oval on screen
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (filled)
			g.fillOval(getX(), getY(), width, height);
		else
			g.drawOval(getX(), getY(), width, height);

	}

}
