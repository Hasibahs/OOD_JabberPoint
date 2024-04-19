package model;

import model.slideitems.SlideItem;
import model.slideitems.TextItem;
import util.Style;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Slide {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	private String title;
	private List<SlideItem> items;

	public Slide() {
		items = new ArrayList<>();
	}

	public void addSlideItem(SlideItem item) {
		items.add(item);
	}

	public void addTextItem(int level, String message) {
		addSlideItem(new TextItem(level, message));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		title = newTitle;
	}

	public SlideItem getSlideItem(int index) {
		if (index >= 0 && index < items.size()) {
			return items.get(index);
		}
		return null;
	}

	public List<SlideItem> getSlideItems() {
		return items;
	}

	public int getNumberOfSlideItems() {
		return items.size();
	}

	public void draw(Graphics g, Rectangle area, ImageObserver observer) {
		float scale = calculateScale(area);
		int y = area.y;
		// Draw title
		SlideItem titleItem = new TextItem(0, getTitle());
		Style titleStyle = Style.getStyle(titleItem.getLevel());
		titleItem.draw(area.x, y, scale, g, titleStyle, observer);
		y += titleItem.getBoundingBox(g, observer, scale, titleStyle).height;
		// Draw items
		for (SlideItem item : getSlideItems()) {
			Style itemStyle = Style.getStyle(item.getLevel());
			item.draw(area.x, y, scale, g, itemStyle, observer);
			y += item.getBoundingBox(g, observer, scale, itemStyle).height;
		}
	}

	private float calculateScale(Rectangle area) {
		return Math.min(((float) area.width) / WIDTH, ((float) area.height) / HEIGHT);
	}
}
