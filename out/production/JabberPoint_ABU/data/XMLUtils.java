package data;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Utility class for common XML operations.
 */
public class XMLUtils {

    /**
     * Retrieves the text content of the first element with the specified tag name.
     *
     * @param element The parent element.
     * @param tagName The name of the tag to find.
     * @return The text content of the found element or an empty string if not found.
     */
    public static String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        // Consider throwing a custom exception if the element is required
        return "";
    }

    /**
     * Parses an integer attribute of an element.
     *
     * @param element       The element.
     * @param attributeName The name of the attribute.
     * @param defaultValue  The default value to return in case of an error.
     * @return The parsed integer or the default value if parsing fails.
     */
    public static int parseIntegerAttribute(Element element, String attributeName, int defaultValue) {
        if (!element.hasAttribute(attributeName)) {
            System.err.println("Attribute " + attributeName + " not found.");
            return defaultValue; // Or throw a custom exception
        }

        String value = element.getAttribute(attributeName);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Number Format Exception for attribute " + attributeName + ": " + value);
            return defaultValue; // Or throw a custom exception
        }
    }
}
