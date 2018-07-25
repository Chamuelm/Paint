// Enum contains shape types and icons
public enum ShapeType {
	RECTANGLE("Rectangle", "rectangle.png"),
	ROUND_RECTANGLE("Round Rectangle", "roundRectangle.png"),
	OVAL("Oval", "oval.png"),
	LINE("Line", "line.png");
	
	// Instance variables
	private final String name;
	private final String iconPath;
	
	//Constructor
	ShapeType(String name, String iconPath) {
		this.name = name;
		this.iconPath = iconPath;
	}
	
	// Accessors
	public String getName() {
		return name;
	}
	
	public String getIconPath() {
		return iconPath;
	}
} // End of enum ShapeType