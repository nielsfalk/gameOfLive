package de.niles.gameOfLife.game;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final boolean alive;

    private List<Cell> neighbors;

    private Generation generation;

    private int x;

    private int y;

    private Cell inNextGeneration, inLastGeneration;

    public Cell(boolean alive, Generation generation, int y, int x, Cell inLastGeneration) {
        this.alive = alive;
        this.generation = generation;
        this.y = y;
        this.x = x;
        this.inLastGeneration = inLastGeneration;
    }

    public List<Cell> getNeighbors() {
        if (neighbors == null) {
            neighbors = new ArrayList<Cell>();
            if (inLastGeneration != null) {
                for (Cell lastGenetationNeighbor : inLastGeneration.getNeighbors()) {
                    neighbors.add(lastGenetationNeighbor.inNextGeneration(generation));
                }
            } else {
                for (int[] neighborPos : new int[][]{ //
                        {-1, -1}, {-1, 0}, {-1, 1},//
                        {0, -1}, {0, 1},//
                        {1, -1}, {1, 0}, {1, 1}}) {
                    Cell neighbor = generation.getCellAtContinuing(x + neighborPos[1], y + neighborPos[0]);
                    if (neighbor != null) {
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }

    public int countLivingNeighbors() {
        int livingNeighbors = 0;
        for (Cell neighbor : getNeighbors()) {
            if (neighbor.alive) {
                livingNeighbors++;
            }
        }
        return livingNeighbors;
    }

    public Cell inNextGeneration(Generation next) {
        if (inNextGeneration == null) {
            inNextGeneration = new Cell(countLivingNeighbors() == 3 || (alive && countLivingNeighbors() == 2), next,
                    y, x, this);
        }
        return inNextGeneration;
    }

    public Cell inLastGeneration() {
        return inLastGeneration;
    }

    public Boolean isAlive() {
        return alive;
    }

    public LifeStatus getLifeStatus() {
        return LifeStatus.fromCell(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) o;
        return this == other || (
                y == other.y
                        && x == other.x
                        && alive == other.alive
                        && generation == other.generation);
    }

    public Generation getGeneration() {
        return generation;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y + " alife=" + alive + " generation=" + generation.getNumberOfGeneration();
    }
}
