package data;

import model.Presentation;
import model.Slide;
import model.slideitems.BitmapItem;
import model.slideitems.SlideItem;
import model.slideitems.TextItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLPresentationLoader implements PresentationLoader {

    @Override
    public void load(Presentation presentation, String filename) throws IOException {
        Document document = parseXMLDocument(filename);
        Element docElement = document.getDocumentElement();
        loadPresentation(presentation, docElement);
    }

    private Document parseXMLDocument(String filename) throws IOException {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new File(filename));
        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("Error parsing XML file", e);
        }
    }

    private void loadPresentation(Presentation presentation, Element docElement) {
        presentation.setTitle(XMLUtils.getElementTextContent(docElement, "showtitle"));
        NodeList slides = docElement.getElementsByTagName("slide");
        for (int i = 0; i < slides.getLength(); i++) {
            Element slideElement = (Element) slides.item(i);
            presentation.appendSlide(createSlideFromElement(slideElement));
        }
    }

    private Slide createSlideFromElement(Element slideElement) {
        Slide slide = new Slide();
        slide.setTitle(XMLUtils.getElementTextContent(slideElement, "title"));
        loadSlideItems(slide, slideElement);
        return slide;
    }

    private void loadSlideItems(Slide slide, Element slideElement) {
        NodeList items = slideElement.getElementsByTagName("item");
        for (int j = 0; j < items.getLength(); j++) {
            slide.addSlideItem(createSlideItemFromElement((Element) items.item(j)));
        }
    }

    private SlideItem createSlideItemFromElement(Element itemElement) {
        int level = XMLUtils.parseIntegerAttribute(itemElement, "level", 1);
        String kind = itemElement.getAttribute("kind");
        String content = itemElement.getTextContent();

        if ("text".equals(kind)) {
            return new TextItem(level, content);
        } else if ("image".equals(kind)) {
            return new BitmapItem(level, content);
        } else {
            // Handle unknown item types or throw an exception if you prefer
            System.err.println("Unknown item type: " + kind);
            return null;
        }
    }
}
