package controller;

import data.PresentationLoader;
import data.PresentationSaver;
import data.XMLPresentationLoader;
import data.XMLPresentationSaver;
import model.Presentation;

import javax.swing.*;
import java.io.IOException;

public class PresentationManager {
    private JFrame parent;
    private Presentation presentation;

    public PresentationManager(JFrame frame, Presentation pres) {
        this.parent = frame;
        this.presentation = pres;
    }

    public void openPresentation() {
        presentation.clear();
        PresentationLoader xmlLoader = new XMLPresentationLoader(); // use the XML loader
        try {
            xmlLoader.load(presentation, "testPresentation.xml");
            presentation.setCurrentSlideIndex(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
        parent.repaint();
    }

    public void savePresentation() {
        PresentationSaver xmlSaver = new XMLPresentationSaver();  // Use the XML saver
        try {
            xmlSaver.save(presentation, "savedPresentation.xml");
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goToSlide() {
        String pageNumberStr = JOptionPane.showInputDialog(parent, "Enter slide number:");
        try {
            int pageNumber = Integer.parseInt(pageNumberStr);
            if (pageNumber > 0 && pageNumber <= presentation.getNumberOfSlides()) {
                presentation.setCurrentSlideIndex(pageNumber - 1);  // Set current slide index
                parent.repaint();  // Refresh UI to display the selected slide
            } else {
                JOptionPane.showMessageDialog(parent, "Slide number is out of range", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid slide number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
