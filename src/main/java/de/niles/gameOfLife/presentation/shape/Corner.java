package de.niles.gameOfLife.presentation.shape;

import de.niles.gameOfLife.game.Cell;
import de.niles.gameOfLife.presentation.GameOfLife;

import java.awt.geom.GeneralPath;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 15.10.11
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
public class Corner {
    private Corner next, last, opposit;
    int x;
    int y;
    int cornerBeginX;
    int cornerBeginY;
    int cornerEndX;
    int cornerEndY;
    String id;

    public static final int cornerRadiusX = 10;
    public static final int cornerRadiusY = 10;

    public static final Corner BOTTOM_LEFT = new Corner("bl", null, 0, GameOfLife.ICON_HEIGHT)
            .setCornerBegin(cornerRadiusX, 0)
            .setCornerEnd(0, -cornerRadiusY)
            .setNeighborsDiff(0, 1, -1, 1, -1, 0);
    public static final Corner BOTTOM_RIGHT = new Corner("br", BOTTOM_LEFT, GameOfLife.ICON_WIDTH, GameOfLife.ICON_HEIGHT)
            .setCornerBegin(0, -cornerRadiusY)
            .setCornerEnd(-cornerRadiusX, 0)
            .setNeighborsDiff(1, 0, 1, 1, 0, 1);
    public static final Corner TOP_RIGHT = new Corner("tr", BOTTOM_RIGHT, GameOfLife.ICON_WIDTH, 0)
            .setCornerBegin(-cornerRadiusX, 0)
            .setCornerEnd(0, cornerRadiusY)
            .setNeighborsDiff(0, -1, 1, -1, 1, 0)
            .setCornerTop();
    public static final Corner TOP_LEFT = new Corner("tl", TOP_RIGHT, 0, 0)
            .setCornerBegin(0, cornerRadiusY)
            .setCornerEnd(cornerRadiusX, 0)
            .setNeighborsDiff(-1, 0, -1, -1, 0, -1)
            .setCornerTop();
    public static final Corner INITIAL_CORNER = BOTTOM_LEFT;
    private int neighborDiffX0;
    private int neighborDiffY0;
    private int neighborDiffX1;
    private int neighborDiffY1;
    private int neighborDiffX2;
    private int neighborDiffY2;
    protected boolean cornerTop = false;

    private Corner(String id, Corner next, int x, int y) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.next = next;
        if (id.equals("tl")) {
            BOTTOM_LEFT.next = this;
        }
    }

    private Corner setCornerTop() {
        this.cornerTop = true;
        return this;
    }

    private Corner setCornerBegin(int xDiffToEdge, int yDiffToEdge) {
        cornerBeginX = x + xDiffToEdge;
        cornerBeginY = y + yDiffToEdge;
        return this;

    }

    private Corner setCornerEnd(int xDiffToEdge, int yDiffToEdge) {
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

    public Corner getNext() {
        return next;
    }

    public Corner getLast() {
        if (last == null) {
            last = next.next.next;
        }
        return last;
    }

    public Corner getOpposit() {
        if (opposit == null) {
            opposit = next.next;
        }
        return opposit;
    }

    private Corner setNeighborsDiff(int x0, int y0, int x1
            , int y1, int x2, int y2) {
        neighborDiffX0 = x0;
        neighborDiffY0 = y0;
        neighborDiffX1 = x1;
        neighborDiffY1 = y1;
        neighborDiffX2 = x2;
        neighborDiffY2 = y2;
        return this;
    }

    public Cell getNeighbor0(Cell cell) {
        int x = cell.getX() + neighborDiffX0;
        int y = cell.getY() + neighborDiffY0;
        return cell.getGeneration().getCellAt(x, y);
    }


    public Cell getNeighbor1(Cell cell) {
        int x = cell.getX() + neighborDiffX1;
        int y = cell.getY() + neighborDiffY1;
        return cell.getGeneration().getCellAt(x, y);
    }

    public Cell getNeighbor2(Cell cell) {
        int x = cell.getX() + neighborDiffX2;
        int y = cell.getY() + neighborDiffY2;
        return cell.getGeneration().getCellAt(x, y);
    }

    @Override
    public String toString() {
        return id;
    }

    Boolean isNeighbor0Alive(Cell cell) {
        Cell neighbor0 = getNeighbor0(cell);
        return neighbor0 != null && neighbor0.isAlive();
    }

    Boolean isNeighbor1Alive(Cell cell) {
        Cell neighbor1 = getNeighbor1(cell);
        return neighbor1 != null && neighbor1.isAlive();
    }

    Boolean isNeighbor2Alive(Cell cell) {
        Cell neighbor2 = getNeighbor2(cell);
        return neighbor2 != null && neighbor2.isAlive();
    }



}
