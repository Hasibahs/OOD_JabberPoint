package view;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;

import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame {

	private static final long serialVersionUID = 3227L;
	private static final String DEFAULT_TITLE = "Jabberpoint 1.6 - OU";
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;

	private SlideViewer slideViewer;

	public SlideViewerFrame(Presentation presentation) {
		super(DEFAULT_TITLE);	
		setupFrame(presentation);
	}

	private void setupFrame(Presentation presentation) {
		initializeComponents(presentation);
		configureWindowSettings();
	}

	private void initializeComponents(Presentation presentation) {
		slideViewer = new SlideViewer(presentation, this);
		presentation.setSlideViewer(slideViewer);
		getContentPane().add(slideViewer);
		addKeyListener(new KeyController(presentation));
		setJMenuBar(new MenuController(this, presentation));
	}

	private void configureWindowSettings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}
}
