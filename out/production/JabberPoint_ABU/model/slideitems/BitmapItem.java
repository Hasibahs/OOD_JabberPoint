package model.slideitems;

import util.Style;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private Optional<String> imageName;

	private static final String FILE_NOT_FOUND_MESSAGE = "File not found";

	public BitmapItem(int level, String name) {
		super(level);
		imageName = Optional.ofNullable(name);
		loadImage();
	}

	public BitmapItem() {
		this(0, null);
	}

	private void loadImage() {
		imageName.ifPresent(name -> {
			try {
				bufferedImage = ImageIO.read(new File(name));
			} catch (IOException e) {
				System.err.println(FILE_NOT_FOUND_MESSAGE + ": " + name);
			}
		});
	}

	public Optional<String> getName() {
		return imageName;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		int imageWidth = (bufferedImage != null) ? bufferedImage.getWidth(observer) : 0;
		int imageHeight = (bufferedImage != null) ? bufferedImage.getHeight(observer) : 0;
		return new Rectangle(
				(int) (myStyle.getIndent() * scale), 0,
				(int) (imageWidth * scale),
				(int) (myStyle.getLeading() * scale) + (int) (imageHeight * scale)
		);
	}

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		if (bufferedImage != null) {
			int width = x + (int) (myStyle.getIndent() * scale);
			int height = y + (int) (myStyle.getLeading() * scale);
			g.drawImage(
					bufferedImage,
					width,
					height,
					(int) (bufferedImage.getWidth(observer) * scale),
					(int) (bufferedImage.getHeight(observer) * scale),
					observer
			);
		}
	}

	@Override
	public String toString() {
		return "BitmapItem[level=" + getLevel() + ", name=" + imageName.orElse("No image") + "]";
	}
}
