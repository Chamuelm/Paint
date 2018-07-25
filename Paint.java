/*
 * Paint.java
 * Contain main driver class for paint software
 * 
 * Made by: Moshe Hamiel - 308238716
 */
import javax.swing.JFrame;

// Main driver class for paint software
public class Paint {

	public static void main(String[] args) {
		PaintFrame window = new PaintFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(450, 400);
		window.setVisible(true);
	}

}
