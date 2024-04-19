package model.slideitems;

import model.Slide;
import util.Style;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextItem extends SlideItem {
	private String text;
	
	private static final String EMPTYTEXT = "No Text Given";

//A textitem of int level with text string
	public TextItem(int level, String string) {
		super(level);
		text = string;
	}

//An empty textitem
	public TextItem() {
		this(0, EMPTYTEXT);
	}

//Returns the text
	public String getText() {
		return text == null ? "" : text;
	}

//Returns the AttributedString for the Item
public AttributedString getAttributedString(Style style, float scale) {
	String str = getText();
	AttributedString attrStr = new AttributedString(str.isEmpty() ? " " : str);
	if (!str.isEmpty()) {
		attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, str.length());
	}
	return attrStr;
}


	//Returns the bounding box of an Item
public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
	List<TextLayout> layouts = getLayouts(g, style, scale);
	int xsize = 0, ysize = (int) (style.getLeading() * scale); // Use getter here
	Iterator<TextLayout> iterator = layouts.iterator();
	while (iterator.hasNext()) {
		TextLayout layout = iterator.next();
		Rectangle2D bounds = layout.getBounds();
		if (bounds.getWidth() > xsize) {
			xsize = (int) bounds.getWidth();
		}
		if (bounds.getHeight() > 0) {
			ysize += bounds.getHeight();
		}
		ysize += layout.getLeading() + layout.getDescent();
	}
	return new Rectangle((int) (style.getIndent()*scale), 0, xsize, ysize ); // Use getter here
}


//Draws the item
public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver o) {
	if (text == null || text.length() == 0) {
		return;
	}
	List<TextLayout> layouts = getLayouts(g, style, scale);
	Point pen = new Point(x + (int)(style.getIndent() * scale), // Use getter here
			y + (int) (style.getLeading() * scale)); // Use getter here
	Graphics2D g2d = (Graphics2D)g;
	g2d.setColor(style.getColor());
	Iterator<TextLayout> it = layouts.iterator();
	while (it.hasNext()) {
		TextLayout layout = it.next();
		pen.y += layout.getAscent();
		layout.draw(g2d, pen.x, pen.y);
		pen.y += layout.getDescent();
	}
}


	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
		List<TextLayout> layouts = new ArrayList<TextLayout>();
		AttributedString attrStr = getAttributedString(s, scale);
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
		// Use the getter method to access the indent property
		Float wrappingWidth = (Slide.WIDTH - s.getIndent()) * scale;
		while (measurer.getPosition() < getText().length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
		}
		return layouts;
	}


	public String toString() {
		return "TextItem[" + getLevel()+","+getText()+"]";
	}
}
