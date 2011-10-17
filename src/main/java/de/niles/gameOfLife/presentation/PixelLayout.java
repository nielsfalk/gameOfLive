package de.niles.gameOfLife.presentation;

import de.niles.gameOfLife.game.Cell;
import de.niles.gameOfLife.game.Generation;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 14.10.11
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class PixelLayout extends GameOfLife {
    public PixelLayout() {
        generation = new Generation("" +//
                /*"" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							" OOOO                      \n" + //
							"O   O                      \n" + //
							"    O                      \n" + //
							"O  O                       \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n" + //
							"                           \n"*/

                " O      \n" + //
                "  O     \n" + //
                "OOO           \n" + //
                "        \n" + //
                "        \n" + //
                "        \n");
        generation.setContinuing(true);
        width = generation.getWidth() * ICON_WIDTH;
        height = generation.getHeight() * ICON_HEIGHT;
        super.init(width, height);
    }

    @Override
    protected void drawCells(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Cell cell : generation.getAllCells()) {

            g.setColor(cell.getLifeStatus().getColor());
            int x = 1 + (cell.getX() * ICON_WIDTH);
            int y = 1 + (cell.getY() * ICON_HEIGHT);
            g.fillOval(x, y, ICON_WIDTH - 2, ICON_HEIGHT - 2);
        }
    }

    @Override
    protected void drawState(Graphics2D g, long deltaMillis) {
        super.drawState(g, deltaMillis);
        drawHelpLines(g);
        drawCells(g);
    }

    public static void main(String argv[]) {
        Game g = new PixelLayout();
        g.gameLoop();
        System.out.println("strange");
    }
}
