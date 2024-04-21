package data;

import model.Presentation;
import java.io.IOException;

/**
 * Interface for saving presentations to a file.
 */
public interface PresentationSaver {

    /**
     * Saves a presentation to the specified filename.
     *
     * @param presentation the Presentation object to save
     * @param filename the path to the file where the presentation will be saved
     * @throws IOException if there is an issue writing to the file
     */
    void save(Presentation presentation, String filename) throws IOException;
}
