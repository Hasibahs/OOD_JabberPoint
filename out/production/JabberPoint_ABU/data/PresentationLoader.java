package data;

import model.Presentation;
import java.io.IOException;

/**
 * Interface for loading presentations from a file.
 */
public interface PresentationLoader {
    /**
     * Loads a presentation from the specified filename.
     *
     * @param presentation the Presentation object to load data into
     * @param filename the path to the file from which the presentation is loaded
     * @throws IOException if there is an issue reading from the file
     */
    void load(Presentation presentation, String filename) throws IOException;
}
