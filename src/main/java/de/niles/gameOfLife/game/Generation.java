package de.niles.gameOfLife.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Generation {
    private List<List<Cell>> data = new ArrayList<List<Cell>>();

    private int width;

    private Generation next, last;

    private final int numberOfGeneration;

    private boolean continuing = false;

    private Generation(Generation last) {
        numberOfGeneration = last.numberOfGeneration + 1;
        this.last = last;
        this.width = last.width;
        for (List<Cell> lastRows : last.data) {
            ArrayList<Cell> row = new ArrayList<Cell>();
            data.add(row);
            for (Cell cell : lastRows) {
                row.add(cell.inNextGeneration(this));
            }
        }
    }

    public Generation(String initial) {
        numberOfGeneration = 0;
        Initialiser.init(this, initial);
    }

    public Generation(int width, int height, int percent, boolean continuing) {
        numberOfGeneration = 0;
        this.continuing = continuing;
        this.width = width;
        int livingCellCount = width * height * percent / 100;
        HashSet<Cell> livingCells = new HashSet<Cell>();
        for (int i = 0; livingCells.size() < livingCellCount; i++) {
            livingCells.add(new Cell(true, this, new Random().nextInt(height), new Random().nextInt(width), null));
        }
        for (int rowCount = 0; rowCount < height; rowCount++) {
            ArrayList<Cell> row = new ArrayList<Cell>();
            data.add(row);
            for (int pos = 0; pos < width; pos++) {
               row.add(new Cell(false, this, rowCount, pos, null));
            }
        }
        for (Cell livingCell : livingCells) {
            data.get(livingCell.getY()).set(livingCell.getX(), livingCell);
        }


    }

    public Cell getCellAtContinuing(int x, int y) {
        if (continuing) {
            if (y < 0) {
                y += data.size();
            }
            if (x < 0) {
                x += width;
            }
            y %= data.size();
            x %= width;
        } else {
            if ((y < 0 || y >= data.size() || x < 0 || x >= width)) {
                return null;
            }
        }
        return data.get(y).get(x);
    }

    public Cell getCellAt(int x, int y) {
        if ((y < 0 || y >= data.size() || x < 0 || x >= width)) {
            return null;
        }

        return data.get(y).get(x);
    }

    private static class Initialiser {
        public static void init(Generation g, String initial) {
            String[] rows = initial.split("\n");
            initWidth(g, rows);
            initData(g, rows);
        }

        private static void initData(Generation g, String[] rows) {
            for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
                String row = rows[rowIndex];
                List<Cell> rowData = new ArrayList<Cell>();
                g.data.add(rowData);
                for (int pos = 0; pos < g.width; pos++) {
                    boolean alive = row.length() <= pos ? false : row.charAt(pos) != ' ';
                    rowData.add(new Cell(alive, g, rowIndex, pos, null));
                }
            }
        }

        private static void initWidth(Generation g, String[] rows) {
            g.width = 0;
            for (String row : rows) {
                int rowLen = row.length();
                g.width = rowLen > g.width ? rowLen : g.width;
            }
        }
    }


    public Generation next() {
        if (next == null) {
            Generation potentialEqual = last;
            while (potentialEqual != null) {
                if (equals(potentialEqual)) {
                    next = potentialEqual.next();
                    return next;
                }
                potentialEqual = potentialEqual.last;// step back in time
            }
            next = new Generation(this);
        }
        return next;
    }

    public String asString() {
        String ret = null;
        for (List<Cell> row : data) {
            ret = ret == null ? "" : ret + "\n";
            for (Cell cell : row) {
                ret += cell.isAlive() ? 'O' : ' ';
            }
        }
        return ret + "\n";
    }

    public String asJavaString() {
        String ret = null;
        for (List<Cell> row : data) {
            ret = ret == null ? "//\n\n\"\" + //\n\"" : ret + "\\n\"+//\n\"";
            for (Cell cell : row) {
                ret += cell.isAlive() ? 'O' : ' ';
            }

        }
        return ret + "\\n\"//\n\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Generation)) {
            return false;
        }
        Generation other = (Generation) obj;
        if (other.width != width || other.data.size() != data.size()) {
            return false;
        }
        for (int row = 0; row < data.size(); row++) {
            List<Cell> rowData = data.get(row);
            List<Cell> otherRowData = other.data.get(row);
            for (int pos = 0; pos < width; pos++) {
                if (rowData.get(pos).isAlive() != otherRowData.get(pos).isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return data.size();
    }

    public List<Cell> getAllCells() {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (List<Cell> row : data) {
            for (Cell cell : row) {
                cells.add(cell);
            }
        }
        return cells;
    }


    public void setContinuing(boolean continuing) {
        this.continuing = continuing;
    }
}
