import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProvider {
    Robot robot;
    Rectangle screenSize;

    public ImageProvider() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        screenSize = new ScreenSize().getRectangle();
    }

    public BufferedImage getImg() {
        return robot.createScreenCapture(screenSize);
    }
}
