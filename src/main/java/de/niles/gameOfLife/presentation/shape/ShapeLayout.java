package de.niles.gameOfLife.presentation.shape;

import de.niles.gameOfLife.game.Generation;
import de.niles.gameOfLife.presentation.Game;
import de.niles.gameOfLife.presentation.GameOfLife;
import de.niles.gameOfLife.presentation.GraUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 14.10.11
 * Time: 18:24
 * To change this template use File | Settings | File Templates.
 */
public class ShapeLayout extends GameOfLife {
    private BufferedImage backgroundImg;

    private ImageGenerator imageGenerator;
    private BufferedImage currentImg;
    private BufferedImage lastImg;
    private boolean preloadWithNextDrawing;
    private BufferedImage preloadedNext;


    public ShapeLayout() {
        String fail = "" +//
                "  OO                       \n" + //
                " OOOO                      \n" + //
                " OO OO                     \n" + //
                "  OOO                      \n" + //
                "                           \n" + //
                "                           \n" + //
                "                           \n" + //
                "                           \n" + //
                "                           \n" + //
                "                           \n";

        String glider = "" +//


                "\n\n" + //
                " O      \n" + //
                "  O     \n" + //
                "OOO          \n" + //
                "        \n" + //
                "        \n" + //
                "        \n";

        String explode = "" +//
                "\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n" +
                "                               000                           \n" +//
                "                               0 0\n" +//
                "                               0 0\n" +//
                "                                   \n" +//
                "                               0 0\n" +//
                "                               0 0\n" +//
                "                               000                           \n \n \n \n \n \n \n \n \n \n \n";

        String explode2 = "" +//
                "\n \n \n \n \n \n \n \n \n \n" +
                "                                                         \n" +//
                "                                O \n" +//
                "                               0O0\n" +//
                "                                 O  \n" +//
                "                               \n" +//
                "                               \n" +//
                "                                                          \n \n \n \n \n \n \n \n \n \n \n";
        generation = new Generation(64, 36, 31, false);
        //generation.setContinuing(true);
        width = generation.getWidth() * ICON_WIDTH;
        height = generation.getHeight() * ICON_HEIGHT;
        super.init(width, height);
    }


    @Override
    protected void drawCells(Graphics2D gCanvas) {

        int numberOfGeneration = generation.getNumberOfGeneration();
        if (preloadWithNextDrawing && preloadedNext != null) {
            preloadWithNextDrawing = false;
            preloadedNext = ImageGenerator.generate(generation, width, height);
        }

        if (currentImg == null) {
            currentImg = preloadedNext == null ? ImageGenerator.generate(generation, width, height) : preloadedNext;
            preloadedNext = null;
        }


        if (millisSinceLastChange < blendTime + blenDiff) {
            if (millisSinceLastChange > blenDiff) {
                float transparency = (millisSinceLastChange - blenDiff) / blendTime;
                gCanvas.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - transparency));
            } else {
                preloadWithNextDrawing = true;
                gCanvas.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            }
            gCanvas.drawImage(lastImg, 0, 0, null);
        }

        if (millisSinceLastChange < blendTime) {
            float transparency = millisSinceLastChange / blendTime;
            gCanvas.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        } else {
            gCanvas.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        gCanvas.drawImage(currentImg, 0, 0, null);

        gCanvas.dispose();
        strategy.show();

    }


    @Override
    protected void drawState(Graphics2D g, long deltaMillis) {
        millisSinceLastChange += deltaMillis;
        if (millisSinceLastChange > generationDuration) {
            generation = generation.next();
            millisSinceLastChange = 0;
            lastImg = currentImg;
            currentImg = null;

        }

        System.out.println(deltaMillis);
        drawBackground(g);
        //drawHelpLines(g);
        drawCells(g);
    }

    private void drawBackground(Graphics2D g) {

        g.drawImage(getBackgroundImg(), 0, 0, null);

    }

    private BufferedImage getBackgroundImg() {
        if (backgroundImg == null) {
            try {
                backgroundImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                BufferedImage part = ImageIO.read(ShapeLayout.class.getClassLoader().getResource("background.jpg"));
                Graphics2D g = (Graphics2D) backgroundImg.getGraphics();
                for (int i = 0; i * part.getWidth() < width; i++) {
                    for (int j = 0; j * part.getHeight() < height; j++) {
                        BufferedImage toAdd = part;
                        if (i % 2 == 1) {
                            toAdd = GraUtil.horizontalflip(toAdd);
                        }
                        if (j % 2 == 1) {
                            toAdd = GraUtil.verticalflip(toAdd);
                        }
                        g.drawImage(toAdd, i * part.getWidth(), j * part.getHeight(), null);
                    }
                }
            } catch (IOException e) {
                new RuntimeException(e);
            }
        }
        return backgroundImg;
    }


    public static void main(String argv[]) {
        Game g = new ShapeLayout();
        g.gameLoop();
        System.out.println("strange");
    }

}
