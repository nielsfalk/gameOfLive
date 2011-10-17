package de.niles.gameOfLife.game;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CellTest {

    @Test
    public void rule1() {
        Cell cell = new CellMock(false, 3);
        assertThat(cell, is(Dead.dead()));
        assertThat(cell.inNextGeneration(null), Alive.alive());
    }

    @Test
    public void rule2() {
        for (Cell cell : new Cell[]{new CellMock(true, 1), new CellMock(true, 0)}) {
            assertThat(cell, is(Alive.alive()));
            assertThat(cell.inNextGeneration(null), is(Dead.dead()));
        }
    }

    @Test
    public void rule3() {
        for (Cell cell : new Cell[]{new CellMock(true, 2), new CellMock(true, 3)}) {
            assertThat(cell, is(Alive.alive()));
            assertThat(cell.inNextGeneration(null), is(Alive.alive()));
        }
    }

    @Test
    public void rule4() {
        for (Cell cell : new Cell[]{new CellMock(true, 4), new CellMock(true, 5)}) {
            assertThat(cell, is(Alive.alive()));
            assertThat(cell.inNextGeneration(null), is(Dead.dead()));
        }
    }

    private static class CellMock extends Cell {
        int countLivingNeighbors;

        public CellMock(boolean alive, int countLivingNeighbors) {
            super(alive, new Generation(""), 0, 0, null);
            this.countLivingNeighbors = countLivingNeighbors;
        }

        @Override
        public List<Cell> getNeighbors() {
            List<Cell> neighbors = new ArrayList<Cell>();
            for (int i = 0; i < countLivingNeighbors; i++) {
                neighbors.add(new Cell(true, null, 0, 0, null));
            }
            return neighbors;
        }
    }

    public static class Dead extends TypeSafeMatcher<Cell> {
        @Override
        public boolean matchesSafely(Cell cell) {
            return !cell.isAlive();
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("not dead");
        }

        @Factory
        public static <T> Matcher<Cell> dead() {
            return new Dead();
        }
    }

    public static class Alive extends TypeSafeMatcher<Cell> {
        @Override
        public boolean matchesSafely(Cell cell) {
            return cell.isAlive();
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("not alive");
        }

        @Factory
        public static <T> Matcher<Cell> alive() {
            return new Alive();
        }
    }
}
