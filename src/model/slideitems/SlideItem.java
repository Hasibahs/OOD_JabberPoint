package model.slideitems;

import util.Style;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * Abstract class representing a SlideItem with a certain level.
 */
public abstract class SlideItem {
	private int level; // The level of the SlideItem, default is 0

	/**
	 * Constructs a SlideItem with the specified level.
	 *
	 * @param lev The level of this SlideItem
	 */
	public SlideItem(int lev) {
		level = lev;
	}

	/**
	 * Constructs a SlideItem with the default level.
	 */
	public SlideItem() {
		this(0);
	}

	/**
	 * Returns the level of this SlideItem.
	 *
	 * @return The level of this SlideItem as a Style.Level enum
	 */
	public Style.Level getLevel() {
		return Style.Level.values()[level];
	}

	/**
	 * Returns the bounding box of this SlideItem.
	 *
	 * @param g        The Graphics context in which to draw
	 * @param observer The ImageObserver to be notified
	 * @param scale    The scale factor for drawing
	 * @param style    The Style to be applied
	 * @return A Rectangle representing the bounding box of this SlideItem
	 */
	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);

	/**
	 * Draws this SlideItem.
	 *
	 * @param x        The x-coordinate for drawing
	 * @param y        The y-coordinate for drawing
	 * @param scale    The scale factor for drawing
	 * @param g        The Graphics context in which to draw
	 * @param style    The Style to be applied
	 * @param observer The ImageObserver to be notified
	 */
	public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
}
