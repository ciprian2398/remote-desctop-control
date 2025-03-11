package robot;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProvider {

    private Robot robot;
    private Rectangle screenSize;

    public ImageProvider() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        screenSize = calculateScreenSizeRectangle();
    }

    public BufferedImage getImg() {
        return robot.createScreenCapture(screenSize);
    }

    public Rectangle calculateScreenSizeRectangle() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        return new Rectangle(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
    }

    public Rectangle getScreenSizeRectangle() {
        return screenSize;
    }

    public Dimension getScreenSizeDimension() {
        return new Dimension(screenSize.width, screenSize.height);
    }
}
