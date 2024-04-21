package controller;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBarBuilder {
    private JFrame parent;
    private ActionHandler actionHandler;

    public MenuBarBuilder(JFrame frame, ActionHandler handler) {
        this.parent = frame;
        this.actionHandler = handler;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        menuBar.add(createHelpMenu());

        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(createMenuItem("Open", KeyEvent.VK_O));
        fileMenu.add(createMenuItem("New", KeyEvent.VK_N));
        fileMenu.add(createMenuItem("Save", KeyEvent.VK_S));
        fileMenu.addSeparator();
        fileMenu.add(createMenuItem("Exit", KeyEvent.VK_E));
        return fileMenu;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");
        viewMenu.add(createMenuItem("Next", KeyEvent.VK_N));
        viewMenu.add(createMenuItem("Prev", KeyEvent.VK_P));
        viewMenu.add(createMenuItem("Go to", KeyEvent.VK_G));
        return viewMenu;
    }

    private JMenu createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(createMenuItem("About", KeyEvent.VK_A));
        return helpMenu;
    }

    private JMenuItem createMenuItem(String name, int keyEvent) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setMnemonic(keyEvent);
        menuItem.addActionListener(e -> actionHandler.performAction(name));
        return menuItem;
    }
}
