package model.slideitems;

import model.Slide;
import util.Style;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to help with creating layouts for text in slides considering styles and scaling.
 */
public class TextLayoutHelper {

    /**
     * Generates a list of TextLayouts for the given text based on the specified style and scale.
     *
     * @param g     the Graphics context
     * @param style the styling rules
     * @param text  the text to layout
     * @param scale the scale factor to apply
     * @return a list of TextLayouts for the given text
     */
    public static List<TextLayout> getLayouts(Graphics g, Style style, String text, float scale) {
        if (text == null || text.isEmpty()) {
            text = " ";  // Use a single space to prevent empty attribute strings
        }

        AttributedString attrStr = createAttributedString(text, style, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

        float wrappingWidth = (Slide.WIDTH - style.getIndent()) * scale;
        List<TextLayout> layouts = new ArrayList<>();

        while (measurer.getPosition() < text.length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }

        return layouts;
    }

    /**
     * Creates an AttributedString based on the given text, applying the specified style and scale.
     *
     * @param text  the text to be styled
     * @param style the style to apply
     * @param scale the scale factor
     * @return an AttributedString that reflects the given style and scale
     */
    private static AttributedString createAttributedString(String text, Style style, float scale) {
        AttributedString attrStr = new AttributedString(text);
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
        return attrStr;
    }
}
