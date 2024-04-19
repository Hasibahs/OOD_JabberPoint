package data;

import model.Presentation;
import model.Slide;
import model.slideitems.BitmapItem;
import model.slideitems.SlideItem;
import model.slideitems.TextItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class XMLAccessor extends Accessor {
	protected static final String DEFAULT_API_TO_USE = "dom";
	protected static final String SHOWTITLE = "showtitle";
	protected static final String SLIDETITLE = "title";
	protected static final String SLIDE = "slide";
	protected static final String ITEM = "item";
	protected static final String LEVEL = "level";
	protected static final String KIND = "kind";
	protected static final String TEXT = "text";
	protected static final String IMAGE = "image";

	private String getTitle(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();
	}

	@Override
	public void loadFile(Presentation presentation, String filename) throws IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(new File(filename)); // Parse the XML file
			Element doc = document.getDocumentElement();
			presentation.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentation.appendSlide(slide);

				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}
		} catch (ParserConfigurationException | SAXException e) {
			throw new IOException("Error parsing XML file", e);
		}
	}

	protected void loadSlideItem(Slide slide, Element item) {
		NamedNodeMap attributes = item.getAttributes();
		String levelText = attributes.getNamedItem(LEVEL).getTextContent();
		int level = 1; // Default level
		try {
			level = Integer.parseInt(levelText);
		} catch (NumberFormatException e) {
			System.err.println("Number Format Exception for level: " + levelText);
		}

		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.addSlideItem(new TextItem(level, item.getTextContent()));
		} else if (IMAGE.equals(type)) {
			slide.addSlideItem(new BitmapItem(level, item.getTextContent()));
		} else {
			System.err.println("Unknown Element type: " + type);
		}
	}

	public void saveFile(Presentation presentation, String filename) throws IOException {
		try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
			out.println("<?xml version=\"1.0\"?>");
			out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
			out.println("<presentation>");
			out.print("<showtitle>");
			out.print(presentation.getTitle());
			out.println("</showtitle>");
			List<Slide> slides = presentation.getSlides();
			for (Slide slide : slides) {
				out.println("<slide>");
				out.println("<title>" + slide.getTitle() + "</title>");
				List<SlideItem> slideItems = slide.getSlideItems();
				for (SlideItem slideItem : slideItems) {
					out.print("<item kind=\"");
					if (slideItem instanceof TextItem) {
						out.print("text\" level=\"" + slideItem.getLevel() + "\">");
						out.print(((TextItem) slideItem).getText());
					} else if (slideItem instanceof BitmapItem) {
						out.print("image\" level=\"" + slideItem.getLevel() + "\">");
						out.print(((BitmapItem) slideItem).getName());
					}
					out.println("</item>");
				}
				out.println("</slide>");
			}
			out.println("</presentation>");
		}
	}
}
