package view;

import model.Presentation;
import model.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideViewer extends JComponent {

	private Slide currentSlide;
	private final Font labelFont;
	private Presentation currentPresentation;
	private final JFrame mainFrame;

	private static final long serialVersionUID = 227L;

	private static final Color backgroundColor = Color.white;
	private static final Color textColor = Color.black;
	private static final String labelFontName = "Dialog";
	private static final int labelFontStyle = Font.BOLD;
	private static final int labelFontHeight = 10;
	private static final int labelXPosition = 1100;
	private static final int labelYPosition = 20;

	public SlideViewer(Presentation presentation, JFrame frame) {
		setBackground(backgroundColor);
		this.currentPresentation = presentation;
		this.labelFont = new Font(labelFontName, labelFontStyle, labelFontHeight);
		this.mainFrame = frame;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide slide) {
		this.currentPresentation = presentation;
		this.currentSlide = slide;
		repaint();
		if (mainFrame != null) {
			mainFrame.setTitle(presentation.getTitle());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // Swing best practice
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (currentPresentation.getCurrentSlideIndex() < 0 || currentSlide == null) {
			return;
		}

		g.setFont(labelFont);
		g.setColor(textColor);
		g.drawString("Slide " + (1 + currentPresentation.getCurrentSlideIndex()) + " of " +
				currentPresentation.getNumberOfSlides(), labelXPosition, labelYPosition);

		Rectangle area = new Rectangle(0, labelYPosition, getWidth(), (getHeight() - labelYPosition));
		currentSlide.draw(g, area, this);
	}
}
