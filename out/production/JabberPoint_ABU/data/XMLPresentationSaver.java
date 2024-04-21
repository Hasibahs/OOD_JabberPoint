package data;

import model.Presentation;
import model.Slide;
import model.slideitems.BitmapItem;
import model.slideitems.SlideItem;
import model.slideitems.TextItem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLPresentationSaver implements PresentationSaver {

    private static final String XML_HEADER = "<?xml version=\"1.0\"?>";
    private static final String DOCTYPE = "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">";

    @Override
    public void save(Presentation presentation, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println(XML_HEADER);
            out.println(DOCTYPE);
            writePresentation(presentation, out);
        }
    }

    private void writePresentation(Presentation presentation, PrintWriter out) {
        out.println("<presentation>");
        out.println("<showtitle>" + escapeXml(presentation.getTitle()) + "</showtitle>");
        presentation.getSlides().forEach(slide -> writeSlide(slide, out));
        out.println("</presentation>");
    }

    private void writeSlide(Slide slide, PrintWriter out) {
        out.println("<slide>");
        out.println("<title>" + escapeXml(slide.getTitle()) + "</title>");
        slide.getSlideItems().forEach(item -> writeSlideItem(item, out));
        out.println("</slide>");
    }

    private void writeSlideItem(SlideItem item, PrintWriter out) {
        String kind = item instanceof TextItem ? "text" : "image";
        out.println(String.format("<item kind=\"%s\" level=\"%d\">%s</item>",
                kind,
                item.getLevel(),
                escapeXml(getContent(item))));
    }

    private String getContent(SlideItem item) {
        if (item instanceof TextItem) {
            return ((TextItem)item).getText();
        } else if (item instanceof BitmapItem) {
            return ((BitmapItem)item).getName().orElse("DefaultName");
        }
        return "Unsupported item type";
    }


    private String escapeXml(String str) {
        return str.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
