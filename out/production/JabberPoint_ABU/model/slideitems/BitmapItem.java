package model.slideitems;

import util.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BitmapItem extends SlideItem {
  private BufferedImage bufferedImage;
  private String imageName;
  
  protected static final String FILE = "File ";
  protected static final String NOTFOUND = " not found";


  	//level indicates the item-level; name indicates the name of the file with the image
	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		}
		catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND) ;
		}
	}

	//An empty bitmap item
	public BitmapItem() {
		this(0, null);
	}

	//Returns the filename of the image
	public String getName() {
		return imageName;
	}

	//Returns the bounding box of the image
	// Returns the bounding box of the image
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
		// Use the getter methods instead of direct field access
		return new Rectangle(
				(int) (myStyle.getIndent() * scale), 0,
				(int) (bufferedImage.getWidth(observer) * scale),
				(int) (myStyle.getLeading() * scale) +
						(int) (bufferedImage.getHeight(observer) * scale)
		);
	}


	//Draws the image
	// Draws the image
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
		// Use the getter methods instead of direct field access
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


	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
