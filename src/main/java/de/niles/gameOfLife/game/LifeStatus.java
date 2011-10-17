package de.niles.gameOfLife.game;

import java.awt.*;

import static java.awt.Color.green;

public enum LifeStatus {
    initialAlive(green),
    initialDead(Color.white),
    born(green),
    living(new Color(green.getRed() + 80, green.getGreen() - 30, green.getBlue() + 80)),
    dieing(new Color(230, 230, 230)),
    dead(Color.white);

    Color color;

    LifeStatus(Color color) {
        this.color = color;
    }

    public static LifeStatus fromCell(Cell cell) {
        if (cell.inLastGeneration() == null) {
            return cell.isAlive() ? initialAlive : initialDead;
        }
        Boolean wasAlive = cell.inLastGeneration().isAlive();
        if (cell.isAlive()) {
            return wasAlive ? living : born;
        }
        return wasAlive ? dieing : dead;
    }

    public Color getColor() {
        return color;
    }
}
