package controller;

import data.Accessor;
import data.XMLAccessor;
import model.Presentation;
import util.AboutBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MenuController extends JMenuBar {

	private JFrame parent; // Change to JFrame for Swing
	private Presentation presentation;

	public MenuController(JFrame frame, Presentation pres) {
		parent = frame;
		presentation = pres;

		JMenu fileMenu = new JMenu("File");
		fileMenu.add(createMenuItem("Open", KeyEvent.VK_O));
		fileMenu.add(createMenuItem("New", KeyEvent.VK_N));
		fileMenu.add(createMenuItem("Save", KeyEvent.VK_S));
		fileMenu.addSeparator();
		fileMenu.add(createMenuItem("Exit", KeyEvent.VK_E));
		add(fileMenu);

		JMenu viewMenu = new JMenu("View");
		viewMenu.add(createMenuItem("Next", KeyEvent.VK_N));
		viewMenu.add(createMenuItem("Prev", KeyEvent.VK_P));
		viewMenu.add(createMenuItem("Go to", KeyEvent.VK_G));
		add(viewMenu);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.add(createMenuItem("About", KeyEvent.VK_A));
		add(helpMenu); // Changed to add to JMenuBar directly for Swing compatibility.
	}

	// Helper method to create a JMenuItem with an action listener
	private JMenuItem createMenuItem(String name, int keyEvent) {
		JMenuItem menuItem = new JMenuItem(name);
		menuItem.setMnemonic(keyEvent);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performAction(name);
			}
		});
		return menuItem;
	}

	// Handling actions based on menu item name
	private void performAction(String action) {
		switch (action) {
			case "Open":
				openPresentation();
				break;
			case "New":
				presentation.clear();
				parent.repaint();
				break;
			case "Save":
				savePresentation();
				break;
			case "Exit":
				System.exit(0);
				break;
			case "Next":
				presentation.nextSlide();
				parent.repaint(); // Update view after changing slide
				break;
			case "Prev":
				presentation.previousSlide();
				parent.repaint(); // Update view after changing slide
				break;
			case "Go to":
				goToSlide();
				break;
			case "About":
				AboutBox.show(parent);
				break;
			default:
				break;
		}
	}

	private void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.loadFile(presentation, "testPresentation.xml");
			presentation.setCurrentSlideIndex(0); // Assuming starting at the first slide
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
					"Load Error", JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}

	private void savePresentation() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			xmlAccessor.saveFile(presentation, "savedPresentation.xml");
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
					"Save Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(parent, "Page number?");
		try {
			int pageNumber = Integer.parseInt(pageNumberStr);
			presentation.setCurrentSlideIndex(pageNumber - 1); // Assuming pages are 1-indexed for the user
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(parent, "Invalid number format",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint(); // Update view after changing slide
	}
}
