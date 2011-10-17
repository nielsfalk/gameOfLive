package de.niles.gameOfLife.presentation;

import de.niles.gameOfLife.game.Generation;

import java.awt.*;

public abstract class GameOfLife extends Game {
    protected int width;
    protected int height;
    public static final int ICON_HEIGHT = 20;
    public static final int ICON_WIDTH = 20;
    protected long millisSinceLastChange = 0;
    protected Generation generation;
    protected int generationDuration=1000;

    protected float blendTime = 300f;
    protected int blenDiff=100;

    @Override
    protected void drawState(Graphics2D g, long deltaMillis) {
        g.setColor(Color.lightGray);

        g.fillRect(0, 0, width, height);
        millisSinceLastChange += deltaMillis;
        if (millisSinceLastChange > generationDuration) {
            generation = generation.next();
            millisSinceLastChange = 0;
        }

        System.out.println(deltaMillis);
    }

    protected abstract void drawCells(Graphics2D g);


    protected void drawHelpLines(Graphics2D g) {
        // horizontal HelpLines
        g.setColor(Color.black);
        for (int i = 0; i < height; i += ICON_HEIGHT) {
            g.drawLine(1, i, width, i);
        }

        //vertical HelpLines
        for (int i = 0; i < width; i += ICON_WIDTH) {
            g.drawLine(i, 1, i, height);
        }
    }
}
