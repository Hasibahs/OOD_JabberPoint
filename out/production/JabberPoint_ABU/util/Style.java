package util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * The Style class provides predefined styles for various text elements within an application.
 * Each style is characterized by indentation, color, font, and leading space between lines.
 */
public class Style {
	private static final List<Style> PREDEFINED_STYLES = new ArrayList<>();
	private static final String DEFAULT_FONT_NAME = "Helvetica";

	private final int indent;
	private final Color color;
	private final Font font;
	private final int leading;

	// Enum to define style levels
	public enum Level {
		TITLE,
		HEADER,
		SUBHEADER,
		BODY,
		FOOTER
	}

	static {
		// Initialize predefined styles
		PREDEFINED_STYLES.add(new Style(0, Color.red, 48, 20));    // Title
		PREDEFINED_STYLES.add(new Style(20, Color.blue, 40, 10));  // Header
		PREDEFINED_STYLES.add(new Style(50, Color.black, 36, 10)); // Subheader
		PREDEFINED_STYLES.add(new Style(70, Color.black, 30, 10)); // Body
		PREDEFINED_STYLES.add(new Style(90, Color.black, 24, 10)); // Footer
	}

	// Constructor
	private Style(int indent, Color color, int fontSize, int leading) {
		this.indent = indent;
		this.color = color;
		this.font = new Font(DEFAULT_FONT_NAME, Font.BOLD, fontSize);
		this.leading = leading;
	}

	/**
	 * Retrieves a predefined Style object based on the specified level.
	 * @param level the level of style to retrieve
	 * @return Style the style corresponding to the provided level
	 */
	public static Style getStyle(Level level) {
		return PREDEFINED_STYLES.get(level.ordinal());
	}

	// Overrides the standard toString method.
	@Override
	public String toString() {
		return String.format("[%d, %s; %d on %d]", indent, color, font.getSize(), leading);
	}

	/**
	 * Adjusts the font size based on a scaling factor.
	 * @param scale the scaling factor for the font size
	 * @return Font the adjusted font
	 */
	public Font getFont(float scale) {
		return font.deriveFont(font.getSize() * scale);
	}

	// Getters for the properties
	public int getIndent() {
		return indent;
	}

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}

	public int getLeading() {
		return leading;
	}
}
