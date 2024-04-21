package model.slideitems;

import util.Style;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.List;

/**
 * Represents a text item in a slide with adjustable style and positioning.
 */
public class TextItem extends SlideItem {
	private static final String EMPTYTEXT = "No Text Given";
	private String text;

	/**
	 * Constructs a TextItem with specified level and text.
	 *
	 * @param level  the hierarchical level of the text item
	 * @param string the text content
	 */
	public TextItem(int level, String string) {
		super(level);
		this.text = string;
	}

	/**
	 * Constructs a TextItem with default values.
	 */
	public TextItem() {
		this(0, EMPTYTEXT);
	}

	/**
	 * Returns the text of the TextItem.
	 *
	 * @return non-null text, empty if text is null
	 */
	public String getText() {
		return text != null ? text : "";
	}

	/**
	 * Calculates the bounding box of the text based on the given graphics context and style.
	 *
	 * @param g        the Graphics context
	 * @param observer the ImageObserver
	 * @param scale    the scale factor
	 * @param style    the styling context
	 * @return the bounding rectangle of the text
	 */
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = TextLayoutHelper.getLayouts(g, style, getText(), scale);
		int width = 0;
		int height = (int) (style.getLeading() * scale);

		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			width = Math.max(width, (int) Math.ceil(bounds.getWidth()));
			height += layout.getAscent() + layout.getDescent() + layout.getLeading();
		}

		int x = (int) (style.getIndent() * scale);
		int y = 0;

		return new Rectangle(x, y, width, height);
	}

	/**
	 * Draws the text item at the specified location using the provided graphics context.
	 *
	 * @param x        the x-coordinate
	 * @param y        the y-coordinate
	 * @param scale    the scale factor
	 * @param g        the Graphics context
	 * @param style    the style context
	 * @param observer the ImageObserver
	 */
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		if (getText().isEmpty()) {
			return;
		}
		List<TextLayout> layouts = TextLayoutHelper.getLayouts(g, style, getText(), scale);
		TextRenderer.drawText(layouts, g, style, x, y, scale);
	}

	@Override
	public String toString() {
		return String.format("TextItem[level=%d, text=%s]", getLevel(), getText());
	}
}
