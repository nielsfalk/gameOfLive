package de.niles.gameOfLife.presentation;

import de.niles.gameOfLife.presentation.shape.ShapeLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public abstract class Game extends Canvas {
    protected BufferStrategy strategy;
    private boolean gameRunning = true;

    protected void init(int width, int height) {

        JFrame container = new JFrame("Game Of Life");
        JPanel panel = (JPanel) container.getContentPane();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(null);
        setBounds(0, 0, width, height);
        panel.add(this);
        setIgnoreRepaint(true);
        container.pack();
        container.setResizable(false);
        container.setVisible(true);
        container.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                gameRunning = false;
                System.exit(0);

            }
        });
        requestFocus();

        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }

    public void gameLoop() {
        long lastLoopTime = System.currentTimeMillis();

        while (gameRunning) {
            long deltaMillis = System.currentTimeMillis() - lastLoopTime;
            lastLoopTime = System.currentTimeMillis();
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
            drawState(g, deltaMillis);
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    protected abstract void drawState(Graphics2D g, long deltaMillis);

    public static void main(String argv[]) {
        Game g = new ShapeLayout();
        g.gameLoop();
        System.out.println("strange");
    }
}
