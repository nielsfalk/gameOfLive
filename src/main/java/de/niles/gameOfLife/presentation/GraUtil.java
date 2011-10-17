package de.niles.gameOfLife.presentation;

import java.awt.*;
import java.awt.image.*;

/**
 * Created by IntelliJ IDEA.
 * User: niles
 * Date: 16.10.11
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class GraUtil {
    public static BufferedImage horizontalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return dimg;
    }


    public static BufferedImage verticalflip(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(w, h, img.getColorModel().getTransparency());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return dimg;
    }

    public static Image makeTransparent(BufferedImage image, final int value) {
        ImageFilter filter = new RGBImageFilter() {
            public final int filterRGB(int x, int y, int rgb) {
                return rgb | (0x1000000 * value);
            }
        };
        ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
