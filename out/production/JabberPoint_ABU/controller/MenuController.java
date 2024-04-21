package controller;

import model.Presentation;

import javax.swing.*;

public class MenuController extends JMenuBar {

	private JFrame parent;
	private Presentation presentation;
	private PresentationManager presentationManager;
	private ActionHandler actionHandler;
	private MenuBarBuilder menuBarBuilder;

	public MenuController(JFrame frame, Presentation pres) {
		this.parent = frame;
		this.presentation = pres;
		this.presentationManager = new PresentationManager(parent, presentation);
		this.actionHandler = new ActionHandler(parent, presentation, presentationManager);
		this.menuBarBuilder = new MenuBarBuilder(parent, actionHandler);

		this.add(menuBarBuilder.createMenuBar()); // add the created menu bar to this MenuController
	}
}
