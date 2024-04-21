package data;

/**
 * Factory class for creating accessors for presentations.
 */
public class PresentationAccessorFactory {

	public static final String DEMO_NAME = "Demo presentation";
	public static final String DEFAULT_EXTENSION = ".xml";

	/**
	 * Factory method for getting a PresentationLoader for the demo presentation.
	 *
	 * @return A PresentationLoader capable of loading the demo presentation.
	 */
	public static PresentationLoader getDemoAccessor() {
		return new DemoPresentation();
	}

	// If there are other types of accessors, provide factory methods for them here.
}
