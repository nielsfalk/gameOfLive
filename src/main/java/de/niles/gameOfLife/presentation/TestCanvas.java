package de.niles.gameOfLife.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 15.10.11
 * Time: 00:16
 * To change this template use File | Settings | File Templates.
 */
public class TestCanvas extends Canvas {
    int cornerRadiusX = 40;
    int cornerRadiusY = 20;

    protected int width = 800;
    protected int height = 600;
    protected int iconHeight = 70;
    protected int iconWidth = 100;

    public TestCanvas() {
        JFrame container = new JFrame("Game Of Life");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
        setBounds(0, 0, width, height);
        panel.add(this);
        //setIgnoreRepaint(true);
        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });


    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawRect(400, 400, iconWidth, iconHeight);
        int offsetX = 15, offsetY = 15;
        GeneralPath shape = bottomLeft.startShapeCornerEnd(offsetX, offsetY);
        Corner next = bottomLeft.next;
        next.lineToCornerBegin(offsetX, offsetY, shape);
        next.quadTo(offsetX, offsetY, shape);
        next = next.next;
        next.lineToCornerBegin(offsetX, offsetY, shape);
        //next.quadTo(offsetX, offsetY, shape);
        //next = BOTTOM_LEFT.next;
        //next.lineToCornerBegin(offsetX, offsetY, shape);
        //next.quadTo(offsetX, offsetY, shape);
        g2.draw(shape);
    }

    Corner bottomLeft = new Corner("BOTTOM_LEFT", null, 0, iconHeight - 1).setCornerBegin(cornerRadiusX, 0).setCornerEnd(0, -cornerRadiusY);
    Corner bottomRight = new Corner("BOTTOM_RIGHT", bottomLeft, iconWidth - 1, iconHeight - 1).setCornerBegin(0, -cornerRadiusY).setCornerEnd(-cornerRadiusX, 0);
    Corner topRight = new Corner("TOP_RIGHT", bottomRight, iconWidth - 1, 0).setCornerBegin(-cornerRadiusX, 0).setCornerEnd(0, cornerRadiusY);
    Corner topLeft = new Corner("TOP_LEFT", topRight, 0, 0).setCornerBegin(0, cornerRadiusY).setCornerEnd(cornerRadiusX, 0);

    public static void main(String[] args) {
        new TestCanvas();
    }

    private class Corner {
        Corner next;
        int x;
        int y;
        int cornerBeginX;
        int cornerBeginY;
        int cornerEndX;
        int cornerEndY;
        String id;

        private Corner(String id, Corner next, int x, int y) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.next = next;
            if (id.equals("TOP_LEFT")) {
                bottomLeft.next = this;
            }
        }

        public Corner setCornerBegin(int xDiffToEdge, int yDiffToEdge) {
            cornerBeginX = x + xDiffToEdge;
            cornerBeginY = y + yDiffToEdge;
            return this;
        }

        public Corner setCornerEnd(int xDiffToEdge, int yDiffToEdge) {
            cornerEndX = x + xDiffToEdge;
            cornerEndY = y + yDiffToEdge;
            return this;
        }

        public GeneralPath startShape(int offsetX, int offsetY) {
            GeneralPath generalPath = new GeneralPath();
            generalPath.moveTo(offsetX + x, offsetY + y);
            return generalPath;
        }

        public GeneralPath startShapeCornerEnd(int offsetX, int offsetY) {
            GeneralPath generalPath = new GeneralPath();
            generalPath.moveTo(offsetX + cornerEndX, offsetY + cornerEndY);
            return generalPath;
        }

        public void lineToCornerBegin(int offsetX, int offsetY, GeneralPath shape) {

            System.out.println(id + " lineToCorner" + (offsetX + cornerBeginX) + " " + (offsetY + cornerBeginY));
            shape.lineTo(offsetX + cornerBeginX, offsetY + cornerBeginY);
        }

        public void lineTo(int offsetX, int offsetY, GeneralPath shape) {
            shape.lineTo(offsetX + x, offsetY + y);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Corner)) {
                return false;
            }
            return ((Corner) o).id.equals(id);
        }

        public void quadTo(int offsetX, int offsetY, GeneralPath shape) {
            shape.quadTo(x + offsetX, y + offsetY, cornerEndX + offsetX, cornerEndY + offsetY);
        }
    }
}
