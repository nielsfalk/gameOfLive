package de.niles.gameOfLife.presentation.shape;

import de.niles.gameOfLife.game.Cell;
import de.niles.gameOfLife.game.Generation;
import org.junit.Ignore;
import org.junit.Test;

import static de.niles.gameOfLife.presentation.shape.Corner.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CornerTest {
    @Test
    public void fromMiddle() {
        Generation generation = new Generation("OOO\n" +//
                "OOO\nOOO\n");
        Cell cell = generation.getCellAt(1, 1);
        Corner corner = INITIAL_CORNER.getNext();

        assertNeigbors(cell, corner, TOP_LEFT, generation.getCellAt(0, 1), generation.getCellAt(0, 0), generation.getCellAt(1, 0));
        corner = corner.getNext();
        assertNeigbors(cell, corner, TOP_RIGHT, generation.getCellAt(1, 0), generation.getCellAt(2, 0), generation.getCellAt(2, 1));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_RIGHT, generation.getCellAt(2, 1), generation.getCellAt(2, 2), generation.getCellAt(1, 2));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_LEFT, generation.getCellAt(1, 2), generation.getCellAt(0, 2), generation.getCellAt(0, 1));


    }

    @Test
    public void fromTopLeft() {
        Generation generation = new Generation("OOO\n" +//
                "OOO\nOOO\n");
        Cell cell = generation.getCellAt(0, 0);
        Corner corner = INITIAL_CORNER.getNext();

        assertNeigbors(cell, corner, TOP_LEFT, null, null, null);
        corner = corner.getNext();
        assertNeigbors(cell, corner, TOP_RIGHT, null, null, generation.getCellAt(1, 0));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_RIGHT, generation.getCellAt(1, 0), generation.getCellAt(1, 1), generation.getCellAt(0, 1));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_LEFT, generation.getCellAt(0, 1), null, null);
    }

    @Test
    public void fromTopMiddel() {
        Generation generation = new Generation("OOO\n" +//
                "OOO\nOOO\n");
        Cell cell = generation.getCellAt(1, 0);
        Corner corner = INITIAL_CORNER.getNext();

        //assertNeigbors(cell, corner, TOP_LEFT, null, null);
        corner = corner.getNext();
        assertNeigbors(cell, corner, TOP_RIGHT, null, null, generation.getCellAt(2, 0));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_RIGHT, generation.getCellAt(2, 0), generation.getCellAt(2, 1), generation.getCellAt(1, 1));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_LEFT, generation.getCellAt(1, 1), generation.getCellAt(0, 1), generation.getCellAt(0, 0));
    }

    @Ignore
    @Test
    public void roundForOne() {
        Generation generation = new Generation("OOO\n" +//
                "OOO\nOOO\n");
        Cell cell = generation.getCellAt(0, 0);
        Corner corner = INITIAL_CORNER.getNext();

        assertNeigbors(cell, corner, TOP_LEFT, null, null, null);
        corner = corner.getNext();
        assertNeigbors(cell, corner, TOP_RIGHT, null, null, generation.getCellAt(1, 0));
        cell = generation.getCellAt(1, 0);
        assertNeigbors(cell, corner, TOP_RIGHT, null, null, generation.getCellAt(2, 0));
        cell = generation.getCellAt(2, 0);
        corner = corner.getNext();

        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_RIGHT, null, generation.getCellAt(2, 2), generation.getCellAt(1, 2));
        corner = corner.getNext();
        assertNeigbors(cell, corner, BOTTOM_LEFT, null, generation.getCellAt(0, 2), generation.getCellAt(0, 1));


    }

    private void assertNeigbors(Cell cell, Corner corner, Corner expectedCorner, Cell expectedNeighbor0, Cell expectedNeighbor1, Cell expectedNeighbor2) {
        assertThat(corner, is(expectedCorner));
        Cell neighbor0 = corner.getNeighbor0(cell);
        assertThat("neighbor0", neighbor0, is(expectedNeighbor0));
        Cell neighbor1 = corner.getNeighbor1(cell);
        assertThat("neighbor1", neighbor1, is(expectedNeighbor1));
        Cell neighbor2 = corner.getNeighbor2(cell);
        assertThat("neighbor2", neighbor2, is(expectedNeighbor2));
    }
}
