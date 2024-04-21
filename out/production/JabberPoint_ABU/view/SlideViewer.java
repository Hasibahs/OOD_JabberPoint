package view;

import model.Presentation;
import model.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideViewer extends JComponent {
	private static final long serialVersionUID = 227L;
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final Color TEXT_COLOR = Color.BLACK;
	private static final String LABEL_FONT_NAME = "Dialog";
	private static final int LABEL_FONT_STYLE = Font.BOLD;
	private static final int LABEL_FONT_HEIGHT = 10;
	private static final int LABEL_X_POSITION = 1100;
	private static final int LABEL_Y_POSITION = 20;

	private Slide currentSlide;
	private Presentation currentPresentation;
	private final JFrame mainFrame;
	private final Font labelFont;

	public SlideViewer(Presentation presentation, JFrame frame) {
		this.currentPresentation = presentation;
		this.mainFrame = frame;
		this.labelFont = new Font(LABEL_FONT_NAME, LABEL_FONT_STYLE, LABEL_FONT_HEIGHT);
		configureComponent();
	}

	private void configureComponent() {
		setBackground(BACKGROUND_COLOR);
		setOpaque(true);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide slide) {
		this.currentPresentation = presentation;
		this.currentSlide = slide;
		updateMainFrameTitle(presentation.getTitle());
		repaint();
	}

	private void updateMainFrameTitle(String title) {
		if (mainFrame != null) {
			mainFrame.setTitle(title);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		clearBackground(g);

		if (!isValidSlide()) {
			return;
		}

		drawSlideLabel(g);
		drawSlideContent(g);
	}

	private void clearBackground(Graphics g) {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private boolean isValidSlide() {
		return currentPresentation.getCurrentSlideIndex() >= 0 && currentSlide != null;
	}

	private void drawSlideLabel(Graphics g) {
		g.setFont(labelFont);
		g.setColor(TEXT_COLOR);
		String slideInfo = String.format("Slide %d of %d", 1 + currentPresentation.getCurrentSlideIndex(),
				currentPresentation.getNumberOfSlides());
		g.drawString(slideInfo, LABEL_X_POSITION, LABEL_Y_POSITION);
	}

	private void drawSlideContent(Graphics g) {
		Rectangle area = new Rectangle(0, LABEL_Y_POSITION, getWidth(), getHeight() - LABEL_Y_POSITION);
		currentSlide.draw(g, area, this);
	}
}
