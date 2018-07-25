/*
 * Rectangle.java
 * Contain information of drawn rectangle
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import java.awt.Color;
import java.awt.Graphics;

//This class holds rectangle data to be drawn on screen
public class Rectangle extends Shape{
	private final boolean filled;
	private final int width;
	private final int height;
	
	public Rectangle(int x, int y, int width, int height, boolean filled, Color color) {
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
		this.filled = filled;
		setColor(color);
	}
	
	// Protected get methods
	protected int getWidth() {
		return width;
	}
	
	protected int getHeight() {
		return height;
	}
	
	protected boolean getFilled() {
		return filled;
	}

	// Paint rectangle on screen
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (filled)
			g.fillRect(getX(), getY(), width, height);
		else
			g.drawRect(getX(), getY(), width, height);
	}

}
