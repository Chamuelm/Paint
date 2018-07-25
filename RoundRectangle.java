/*
 * RoundRectangle.java
 * Contain information of drawn round rectangle
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import java.awt.Color;
import java.awt.Graphics;

// This class represents round rectangle to be drawn on the screen
public class RoundRectangle extends Rectangle {

	public RoundRectangle(int x, int y, int width, int height, boolean filled, Color color) {
		super(x, y, width, height, filled, color);
	}

	// Paint rectangle on screen
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		if (this.getFilled())
			g.fillRoundRect(getX(), getY(), getWidth(), getHeight(), getWidth()/10, getHeight()/10);
		else
			g.drawRoundRect(getX(), getY(), getWidth(), getHeight(), getWidth()/10, getHeight()/10);
	}
}
