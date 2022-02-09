package panel;

import robot.ImageProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class SpecialJPanel extends JPanel {

    private static final int maxZoom = 300;
    private static final int minZoom = 5;
    private static final int scale = 10;

    private int zoomLevel = 100;

    private final Point oldPoint = new Point(0, 0);
    private final Point imagePosition = new Point(0, 0);
    private BufferedImage bufferedImage;

    private final Dimension imageDimension;
    private final ImageProvider imageProvider;

    public SpecialJPanel() {
        this.imageProvider = new ImageProvider();
        this.imageDimension = imageProvider.getScreenSizeDimension();
        this.bufferedImage = imageProvider.getImg();

        MouseMotionListener ms = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                moveImg(e.getPoint());
            }
        };

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                copyPoint(e.getPoint(), oldPoint);
            }
        };

        MouseWheelListener wl = new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                calcImageDimension(e.getWheelRotation());
            }
        };

        addMouseMotionListener(ms);
        addMouseWheelListener(wl);
        addMouseListener(ml);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bufferedImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            bufferedImage = getResizedBufferedImage(imageProvider.getImg());

            g2d.drawImage(bufferedImage, imagePosition.x, imagePosition.y, this);
            g2d.dispose();
            g.dispose();
            repaint();
        }
    }

    private BufferedImage getResizedBufferedImage(BufferedImage freshImage) {
        BufferedImage resizedImage = new BufferedImage(imageDimension.width, imageDimension.height, 1);
        Graphics2D res = resizedImage.createGraphics();
        res.drawImage(freshImage, 0, 0, imageDimension.width, imageDimension.height, this);
        return resizedImage;
    }

    private void calcImageDimension(int mouseTickRotations) {
        mouseTickRotations *= -1;
        if ((zoomLevel > minZoom + scale && mouseTickRotations < 0) || (zoomLevel + scale <= maxZoom && mouseTickRotations > 0)) {
            zoomLevel += (mouseTickRotations * scale);
            imageDimension.width = (imageProvider.getScreenSizeRectangle().width * zoomLevel) / 100;
            imageDimension.height = (imageProvider.getScreenSizeRectangle().height * zoomLevel) / 100;
        }
    }

    private void moveImg(Point point) {
        imagePosition.x -= oldPoint.x - point.x;
        imagePosition.y -= oldPoint.y - point.y;
        copyPoint(point, oldPoint);
    }
    private void copyPoint(Point source, Point destination) {
        destination.x = source.x;
        destination.y = source.y;
    }
}
