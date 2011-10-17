package de.niles.gameOfLife.presentation.shape;

import de.niles.gameOfLife.game.Cell;
import de.niles.gameOfLife.presentation.GameOfLife;

import java.awt.*;
import java.awt.geom.GeneralPath;

import static de.niles.gameOfLife.presentation.shape.Corner.INITIAL_CORNER;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 15.10.11
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class ShapeGenerator {
    private GeneralPath shape;
    Cell cell;
    private int offsetX;
    private int offsetY;
    private Corner corner;
    private int cureveCount;

    public ShapeGenerator(Cell cell) {
        this.cell = cell;
        calcOffsets();
        corner = INITIAL_CORNER;
        shape = corner.startShapeCornerEnd(offsetX, offsetY);
    }

    ShapeGenerator generate() {
        cureveCount = 0;
        do {
            generationStep();
            cureveCount++;
            //System.out.println(cureveCount++);
        } while (!shapeDone());
        return this;
    }

    boolean shapeDone() {
        return corner.equals(INITIAL_CORNER);
    }


    void generationStep() {
        corner = corner.getNext();
        corner.lineToCornerBegin(offsetX, offsetY, shape);
        Boolean neighbor0Alive = corner.isNeighbor0Alive(cell);
        Boolean neighbor1Alive = corner.isNeighbor1Alive(cell);
        Boolean neighbor2Alive = corner.isNeighbor2Alive(cell);
        if (!neighbor0Alive && !neighbor1Alive && !neighbor2Alive){
            innerRoundCorner();
        }else{
            if (!neighbor0Alive && neighbor1Alive){
                Cell neighbor1 = corner.getNeighbor1(cell);
                int offsetX1 = getOffsetX(neighbor1);
                int offsetY1 = getOffsetY(neighbor1);
                corner.getOpposit().quadTo(offsetX1, offsetY1, shape);

            }
            corner.lineTo(offsetX, offsetY, shape);
        }
    }

    private void innerRoundCorner() {
        corner.quadTo(offsetX, offsetY, shape);
    }

    private void calcOffsets() {
        offsetX = getOffsetX(cell);
        offsetY = getOffsetY(cell);
    }

    private int getOffsetY(Cell cell) {
        return cell.getY() * GameOfLife.ICON_HEIGHT;
    }

    private int getOffsetX(Cell cell) {
        return cell.getX() * GameOfLife.ICON_WIDTH;
    }

    public Shape getShape() {
        return shape;
    }
}
