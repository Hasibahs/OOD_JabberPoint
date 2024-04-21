package application;

import data.PresentationAccessorFactory;
import data.PresentationLoader;
import data.XMLPresentationLoader;
import model.Presentation;
import view.SlideViewerFrame;

import javax.swing.JOptionPane;
import java.io.IOException;

public class JabberPoint {
	private static final String IO_ERROR_MESSAGE = "IO Error: ";
	private static final String JABBERPOINT_ERROR_MESSAGE = "Jabberpoint Error ";
	private static final String JABBERPOINT_VERSION = "Jabberpoint 1.6 - OU version";

	public static void main(String[] args) {
		Presentation presentation = new Presentation();
		SlideViewerFrame viewer = new SlideViewerFrame(presentation);
		loadPresentation(args, presentation);
		presentation.setCurrentSlideIndex(0);
	}

	private static void loadPresentation(String[] args, Presentation presentation) {
		try {
			if (args.length == 0) {
				loadDemoPresentation(presentation);
			} else {
				loadFromXMLFile(args[0], presentation);
			}
		} catch (IOException ex) {
			showErrorMessage(ex);
		}
	}

	private static void loadDemoPresentation(Presentation presentation) throws IOException {
		PresentationLoader demoLoader = PresentationAccessorFactory.getDemoAccessor();
		demoLoader.load(presentation, ""); // Empty string as path might be necessary here
	}

	private static void loadFromXMLFile(String filePath, Presentation presentation) throws IOException {
		PresentationLoader xmlLoader = new XMLPresentationLoader();
		xmlLoader.load(presentation, filePath);
	}

	private static void showErrorMessage(IOException ex) {
		JOptionPane.showMessageDialog(null, IO_ERROR_MESSAGE + ex, JABBERPOINT_ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE);
	}
}
