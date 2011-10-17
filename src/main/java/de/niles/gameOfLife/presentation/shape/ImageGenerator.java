package de.niles.gameOfLife.presentation.shape;


import de.niles.gameOfLife.game.Cell;
import de.niles.gameOfLife.game.Generation;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageGenerator {
    public static BufferedImage generate(Generation generation, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape combinedShapes = getCombinedShapes(generation);
        if (combinedShapes != null) {
            g.setColor(Color.green);
            g.setStroke(new BasicStroke(2));
            g.fill(combinedShapes);
            g.setColor(Color.yellow);
            g.draw(combinedShapes);
        }
        g.dispose();
        return image;
    }

    private static Shape getCombinedShapes(Generation generation) {
        Iterator<Shape> shapes = getShapes(generation).iterator();
        if (!shapes.hasNext()) {
            return null;
        }
        Area ret = new Area(shapes.next());
        while (shapes.hasNext()) {
            ret.add(new Area(shapes.next()));
        }
        return ret;
    }

    private static java.util.List<Shape> getShapes(Generation generation) {
        java.util.List<Shape> shapes = new ArrayList<Shape>();
        for (Cell initialCell : generation.getAllCells()) {
            if (initialCell.isAlive()) {
                Cell cell = initialCell;
                ShapeGenerator shapeGenerator = new ShapeGenerator(initialCell).generate();
                shapes.add(shapeGenerator.getShape());
            }
        }
        return shapes;
    }
}
