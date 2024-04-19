package model.slideitems;

import util.Style;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class SlideItem {
	private int level = 0; //The level of the SlideItem

	public SlideItem(int lev) {
		level = lev;
	}

	public SlideItem() {
		this(0);
	}

//Returns the level
	public Style.Level getLevel() {
		return Style.Level.values()[level];
	}

//Returns the bounding box
	public abstract Rectangle getBoundingBox(Graphics g, 
			ImageObserver observer, float scale, Style style);

//Draws the item
	public abstract void draw(int x, int y, float scale, 
			Graphics g, Style style, ImageObserver observer);
}
