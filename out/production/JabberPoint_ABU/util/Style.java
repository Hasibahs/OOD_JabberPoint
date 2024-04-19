package util;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

public class Style {
	private static final List<Style> STYLES = new ArrayList<>();
	private static final String FONT_NAME = "Helvetica";

	private final int indent;
	private final Color color;
	private final Font font;
	private final int leading;

	// Enum for predefined style levels, assuming these are fixed
	public enum Level {
		TITLE,
		HEADER,
		SUBHEADER,
		BODY,
		FOOTER
	}

	static {
		// Initialize the styles statically if they are constants
		STYLES.add(new Style(0, Color.red, 48, 20));   // style for level TITLE
		STYLES.add(new Style(20, Color.blue, 40, 10)); // style for level HEADER
		STYLES.add(new Style(50, Color.black, 36, 10)); // style for level SUBHEADER
		STYLES.add(new Style(70, Color.black, 30, 10)); // style for level BODY
		STYLES.add(new Style(90, Color.black, 24, 10)); // style for level FOOTER
	}

	public static Style getStyle(Level level) {
		return STYLES.get(level.ordinal());
	}

	private Style(int indent, Color color, int fontSize, int leading) {
		this.indent = indent;
		this.color = color;
		this.font = new Font(FONT_NAME, Font.BOLD, fontSize);
		this.leading = leading;
	}

	@Override
	public String toString() {
		return String.format("[%d, %s; %d on %d]", indent, color, font.getSize(), leading);
	}

	public Font getFont(float scale) {
		return font.deriveFont(font.getSize() * scale);
	}

	// Getters for encapsulated properties, if needed
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
