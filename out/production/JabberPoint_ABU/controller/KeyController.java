package controller;

import model.Presentation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
	private Presentation presentation;

	public KeyController(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_ENTER:
			case '+':
				navigateToNextSlide();
				break;
			case KeyEvent.VK_PAGE_UP:
			case KeyEvent.VK_UP:
			case '-':
				navigateToPreviousSlide();
				break;
			case 'q':
			case 'Q':
				exitApplication();
				break;
			default:
				break;
		}
	}

	private void navigateToNextSlide() {
		presentation.nextSlide();
	}

	private void navigateToPreviousSlide() {
		presentation.previousSlide();
	}

	private void exitApplication() {
		System.exit(0);
	}
}
