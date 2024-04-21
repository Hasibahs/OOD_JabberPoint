package model.slideitems;

import util.Style;

import java.awt.*;
import java.awt.font.TextLayout;
import java.util.List;

/**
 * A utility class for rendering text layouts.
 */
public class TextRenderer {

    /**
     * Draws a list of text layouts on the given graphics context starting at specified position.
     *
     * @param layouts the list of TextLayout to draw
     * @param g       the Graphics context to draw on
     * @param style   the styling rules to apply
     * @param x       the x-coordinate of the starting point
     * @param y       the y-coordinate of the starting point
     * @param scale   the scale factor to apply to the style metrics
     */
    public static void drawText(List<TextLayout> layouts, Graphics g, Style style, int x, int y, float scale) {
        // Adjust the starting point based on the indent and leading of the style
        Point pen = new Point(
                x + (int) (style.getIndent() * scale),
                y + (int) (style.getLeading() * scale)
        );

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(style.getColor());

        // Draw each layout at the computed position
        for (TextLayout layout : layouts) {
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent() + layout.getLeading();
        }
    }
}
