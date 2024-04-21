package controller;

import model.Presentation;
import util.AboutBox;

import javax.swing.*;

public class ActionHandler {
    private JFrame parent;
    private Presentation presentation;
    private PresentationManager presentationManager;

    public ActionHandler(JFrame frame, Presentation pres, PresentationManager presManager) {
        this.parent = frame;
        this.presentation = pres;
        this.presentationManager = presManager;
    }

    public void performAction(String action) {
        try {
            switch (action) {
                case "Open": this.open(); break;
                case "New": this.newPresentation(); break;
                case "Save": this.save(); break;
                case "Exit": this.exit(); break;
                case "Next": this.next(); break;
                case "Prev": this.prev(); break;
                case "Go to": this.goTo(); break;
                case "About": this.about(); break;
                default: this.unknownAction(); break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "Error performing action: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void open() {
        presentationManager.openPresentation();
    }

    private void newPresentation() {
        presentation.clear();
        parent.repaint();
    }

    private void save() {
        presentationManager.savePresentation();
    }

    private void exit() {
        System.exit(0);
    }

    private void next() {
        presentation.nextSlide();
        parent.repaint();
    }

    private void prev() {
        presentation.previousSlide();
        parent.repaint();
    }

    private void goTo() {
        presentationManager.goToSlide();
    }

    private void about() {
        AboutBox.show(parent);
    }

    private void unknownAction() {
        JOptionPane.showMessageDialog(parent, "Unrecognized action",
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
