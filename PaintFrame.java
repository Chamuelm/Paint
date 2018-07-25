
/*
 * PaintFrame.java
 * Main code contains GUI components and data structures
 * 
 * Made by: Moshe Hamiel - 308238716
 */

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// PaintFrame class responsible for GUI and global data structure
public class PaintFrame extends JFrame {
	// Instance variables
	private final BorderLayout layout; // Main layout
	private MenuPanel menu; // Menu panel
	private PaintPanel paintPanel; // Paint panel
	private ArrayList<Shape> shapes; // Stores shapes had drawn on screen
	private Shape draggedShape; // Shape is being dragged right now
	boolean isDragging; // Flag indicate if is currently dragging shape
	private ShapeType chosenShape; // Stores chosen shape to draw
	private boolean filled; // Stores if user want filled shape
	private Color drawColor; // Stores chosen color to paint

	// Constructor for main frame
	public PaintFrame() {
		super("Paint");

		// Initialize variables
		shapes = new ArrayList<>();
		isDragging = false;
		draggedShape = null;
		chosenShape = ShapeType.RECTANGLE;
		filled = false;
		drawColor = Color.BLACK;

		// Set main layout
		layout = new BorderLayout();
		setLayout(layout);

		// Create and add menu panel
		menu = new MenuPanel();
		add(menu, BorderLayout.NORTH);

		// Create and add paint panel
		paintPanel = new PaintPanel();
		paintPanel.setBackground(Color.WHITE);
		add(paintPanel, BorderLayout.CENTER);
	}

	// This class maintain the menu panel and handle buttons events
	public class MenuPanel extends JPanel {
		// Menu buttons
		private final JButton[] shapeButtons;
		private ShapeType[] shapeTypes = ShapeType.values(); // Holds shape types from enum
		private final Icon[] shapeIcons;
		private final JButton[] cmdButtons;
		private final Icon[] cmdIcons;
		private final String[] cmdButtonsName = { "Choose Color", "Undo", "Clear", "Exit" };
		private final String[] cmdButtonsIconPath = { "color-palette.png", "undo.png" };
		private final JCheckBox fillCheckbox; // to select/deselect filled shape
		private final String iconsPath = "icons\\";
		// Buttons background color if selected (default is null)
		public final Color selectedBGColor = new Color(255, 200, 0);

		// Main constructor
		public MenuPanel() {
			super();
			setLayout(new FlowLayout(FlowLayout.LEFT));

			// Setup icons for shape buttons
			shapeIcons = new Icon[shapeTypes.length];
			for (int i = 0; i < shapeTypes.length; i++)
				shapeIcons[i] = getResizedIcon(iconsPath + shapeTypes[i].getIconPath(), 18);

			// Setup buttons for shapes
			ShapeButtonHandler shapesHandler = new ShapeButtonHandler();
			shapeButtons = new JButton[shapeTypes.length];
			for (int i = 0; i < shapeButtons.length; i++) {
				shapeButtons[i] = new JButton(shapeIcons[i]);
				shapeButtons[i].setToolTipText(shapeTypes[i].getName());
				shapeButtons[i].setPreferredSize(new Dimension(22, 20));
				shapeButtons[i].addActionListener(shapesHandler);
				add(shapeButtons[i]);
			}

			// Set default shape icon
			shapeButtons[0].setBackground(selectedBGColor);

			// Add Separator to menu
			add(new JLabel(new ImageIcon(iconsPath + "Seperator.png")));

			// Setup command buttons icons
			cmdIcons = new Icon[cmdButtonsIconPath.length];
			for (int i = 0; i < cmdIcons.length; i++)
				cmdIcons[i] = getResizedIcon(iconsPath + (cmdButtonsIconPath[i]), 13);

			// Setup command buttons
			CmdButtonHandler cmdButtonHandler = new CmdButtonHandler();
			cmdButtons = new JButton[cmdButtonsName.length];
			for (int i = 0; i < cmdButtons.length; i++) {
				if (i < cmdIcons.length) {
					cmdButtons[i] = new JButton(cmdIcons[i]);
					cmdButtons[i].setPreferredSize(new Dimension(22, 20));
				} else
					cmdButtons[i] = new JButton(cmdButtonsName[i]);

				cmdButtons[i].setToolTipText(cmdButtonsName[i]);
				cmdButtons[i].addActionListener(cmdButtonHandler);
			}

			// Add command buttons for Choose color and undo
			add(cmdButtons[0]); // Choose color
			add(cmdButtons[1]); // Undo

			// Add separator
			add(new JLabel(new ImageIcon(iconsPath + "Seperator.png")));

			// Add checkbox to select filled shape
			fillCheckbox = new JCheckBox("Fill");
			fillCheckbox.addItemListener(cmdButtonHandler);
			add(fillCheckbox);

			// Add separator
			add(new JLabel(new ImageIcon(iconsPath + "Seperator.png")));

			// Add command buttons for Clear and Exit
			add(cmdButtons[2]); // Clear
			add(cmdButtons[3]); // Exit

		}

		// getResizedIcon: get icon path and desired square size and return
		// Icon object of the icon in the desired size
		private ImageIcon getResizedIcon(String path, int size) {
			ImageIcon imageIcon = new ImageIcon(path); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it
			Image newimg = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
			imageIcon = new ImageIcon(newimg); // transform it back
			return imageIcon;
		}

		// Handle shape buttons action
		private class ShapeButtonHandler implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < shapeTypes.length; i++) {
					if (e.getSource() == shapeButtons[i]) {
						shapeButtons[i].setBackground(selectedBGColor);
						chosenShape = shapeTypes[i];
					} else {
						shapeButtons[i].setBackground(null);
					}
				}
			}
		} // End of class ShapeButtonHandler

		// Handle command buttons action
		private class CmdButtonHandler implements ActionListener, ItemListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == cmdButtons[0]) { // Choose color
					Color newColor = JColorChooser.showDialog(paintPanel, "Choose a color", drawColor);
					if (newColor != null)
						drawColor = newColor;
				} else if (e.getSource() == cmdButtons[1]) { // Undo
					if (shapes.size() > 0)
						shapes.remove(shapes.size() - 1);
					paintPanel.repaint();
				} else if (e.getSource() == cmdButtons[2]) { // Clear
					shapes.clear();
					paintPanel.repaint();
				} else if (e.getSource() == cmdButtons[3]) { // Exit
					System.exit(0);
				}
			} // End of actionPerformed

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (fillCheckbox.isSelected())
					filled = true;
				else
					filled = false;
			} // End of iteStateChanged
		} // End of class ShapeButtonHandler
	} // End of class MenuPanel

	// This class maintain the paint panel and handle draw (moouse) events
	public class PaintPanel extends JPanel {
		MouseHandler handler;

		// Main panel constructor
		public PaintPanel() {
			handler = new MouseHandler();
			addMouseMotionListener(handler);
			addMouseListener(handler);
		}

		// Paints all shapes on screen when refresh
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Paint all components in shapes
			for (Shape shape : shapes)
				shape.draw(g);

			g.setColor(drawColor);

			// Paint currently dragging shape
			if (isDragging && draggedShape != null)
				draggedShape.draw(g);
		}

		// Handle mouse events of drawing
		public class MouseHandler extends MouseInputAdapter {
			int startX;
			int startY;

			@Override
			public void mousePressed(MouseEvent e) {
				// Set coordinates for dragging event
				startX = e.getX();
				startY = e.getY();

				// Set true for painting the dragged shape
				isDragging = true;
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// When mouse is dragged it saves the current dragged shape for draw
				if (chosenShape == ShapeType.LINE)
					draggedShape = new Line(startX, startY, e.getX(), e.getY(), drawColor);
				else if ((startX != e.getX()) && (startY != e.getY())) {
					// Determine new points - (x1,y1) for left-up corner of shape
					// and (x2,y2) for right-down corner
					int x1, y1, x2, y2, h, w;
					if (startX < e.getX()) {
						x1 = startX;
						x2 = e.getX();
					} else {
						x1 = e.getX();
						x2 = startX;
					}

					if (startY < e.getY()) {
						y1 = startY;
						y2 = e.getY();
					} else {
						y1 = e.getY();
						y2 = startY;
					}

					h = y2 - y1; // Height
					w = x2 - x1; // Width

					switch (chosenShape) {
					case OVAL:
						draggedShape = new Oval(x1, y1, w, h, filled, drawColor);
						break;
					case RECTANGLE:
						draggedShape = new Rectangle(x1, y1, w, h, filled, drawColor);
						break;
					case ROUND_RECTANGLE:
						draggedShape = new RoundRectangle(x1, y1, w, h, filled, drawColor);
						break;
					default:
						break;
					}
				}
				paintPanel.repaint();
			} // End of mouseDragged

			@Override
			public void mouseReleased(MouseEvent e) {
				// Add latest dragged shape to shapes array and initialize variables
				shapes.add(draggedShape);
				isDragging = false;
			}// End of mouseReleased
		} // End of handler
	} // End of PaintPanel class
} // End of PaintFrame class
