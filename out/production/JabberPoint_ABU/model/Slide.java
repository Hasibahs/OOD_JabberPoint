package model;

import model.slideitems.SlideItem;
import model.slideitems.TextItem;
import util.Style;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Slide {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	private String title;
	private final List<SlideItem> items = new ArrayList<>();

	public Slide() {}

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

	public Optional<SlideItem> getSlideItem(int index) {
		return (index >= 0 && index < items.size()) ? Optional.of(items.get(index)) : Optional.empty();
	}

	public List<SlideItem> getSlideItems() {
		return new ArrayList<>(items); // To prevent modification of the internal list
	}

	public int getNumberOfSlideItems() {
		return items.size();
	}

	public void draw(Graphics g, Rectangle area, ImageObserver observer) {
		float scale = calculateScale(area);
		int y = drawTitle(g, area, observer, scale, area.y);
		drawSlideItems(g, area, observer, scale, y);
	}

	private int drawTitle(Graphics g, Rectangle area, ImageObserver observer, float scale, int y) {
		SlideItem titleItem = new TextItem(0, getTitle());
		Style titleStyle = Style.getStyle(titleItem.getLevel());
		titleItem.draw(area.x, y, scale, g, titleStyle, observer);
		return y + titleItem.getBoundingBox(g, observer, scale, titleStyle).height;
	}

	private void drawSlideItems(Graphics g, Rectangle area, ImageObserver observer, float scale, int y) {
		for (SlideItem item : items) {
			Style itemStyle = Style.getStyle(item.getLevel());
			item.draw(area.x, y, scale, g, itemStyle, observer);
			y += item.getBoundingBox(g, observer, scale, itemStyle).height;
		}
	}

	private float calculateScale(Rectangle area) {
		return Math.min((float) area.width / WIDTH, (float) area.height / HEIGHT);
	}
}
