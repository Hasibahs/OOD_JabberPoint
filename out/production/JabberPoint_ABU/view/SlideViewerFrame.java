package view;

import controller.KeyController;
import controller.MenuController;
import model.Presentation;

import javax.swing.*;

public class SlideViewerFrame extends JFrame {

	private static final long serialVersionUID = 3227L;
	private static final String DEFAULT_TITLE = "Jabberpoint 1.6 - OU";
	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 800;

	private SlideViewer slideViewerComponent;

	public SlideViewerFrame(String jabversion, Presentation presentation) {
		super(DEFAULT_TITLE);
		initialize(presentation);
	}

	private void initialize(Presentation presentation) {
		slideViewerComponent = new SlideViewer(presentation, this); // Updated constructor call
		presentation.setSlideViewerComponent(slideViewerComponent); // Consider renaming this method
		setupWindow(presentation);
	}

	private void setupWindow(Presentation presentation) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(presentation));
		setJMenuBar(new MenuController(this, presentation)); // Directly passing the MenuController
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
	}
}
