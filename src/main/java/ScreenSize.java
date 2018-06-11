import java.awt.*;

public class ScreenSize {
    private int width;
    private int height;

    public ScreenSize() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.width = gd.getDisplayMode().getWidth();
        this.height = gd.getDisplayMode().getHeight();
    }

    public Rectangle getRectangle() {
        return new Rectangle(width, height);
    }
}
