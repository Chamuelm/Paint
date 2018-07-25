/*
 * Shape.java
 * Abstract class represent shape to be drawn on panel
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import java.awt.Color;
import java.awt.Graphics;

// This class represent shape to be drawn on the screen
// It is abstract class represent rectangle, round rectangle, oval or line
public abstract class Shape {
	private int x; // Location on screen of the object - X axis
	private int y; // Location on screen of the object - Y axis
	private Color color; // Color of current shape

	public void draw(Graphics g) {
		g.setColor(getColor());
	}
	
	protected int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}
	
	protected int getY() {
		return y;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	protected Color getColor() {
		return color;
	}
	
	protected void setColor(Color color) {
		this.color = color;
	}
}
