package util;

import javax.swing.*;
import java.awt.*;

/**
 * The About-box for JabberPoint.
 * Maintains a simple message dialog detailing the program and its versions.
 * Authors and their modifications are documented within the class description.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Gert Florijn
 * @author Sylvia Stuurman
 * @version 1.6, last modified 2014/05/16 by Sylvia Stuurman
 */
public class AboutBox {

	private static final String MESSAGE = """
            JabberPoint is a primitive slide-show program in Java(tm). It
            is freely copyable as long as you keep this notice and
            the splash screen intact.
            Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.
            Adapted by Gert Florijn (version 1.1) and Sylvia Stuurman (version 1.2 and higher) for the Open
            University of the Netherlands, 2002 -- now.
            Author's version available from http://www.darwinsys.com/""";

	private static final String TITLE = "About JabberPoint";

	/**
	 * Displays the about information in a dialog box.
	 *
	 * @param parent the parent frame to which the dialog is attached
	 */
	public static void show(Frame parent) {
		JOptionPane.showMessageDialog(parent, MESSAGE, TITLE, JOptionPane.INFORMATION_MESSAGE);
	}
}
