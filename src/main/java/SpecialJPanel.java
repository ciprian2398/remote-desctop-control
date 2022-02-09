import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpecialJPanel extends JPanel {

    private int maxZoom = 300;
    private int zoomLevel = 100;
    private int minZoom = 5;
    private int scale = 10;

    private Robot robot;
    private Rectangle rectangle;
    private BufferedImage bufferedImage;
    private Point imagePosition = new Point(0, 0);
    private Point oldPoint = new Point(0, 0);
    private Dimension imageDimension;

    ImageSource imageSource;


    public SpecialJPanel() {
        init();
    }

    private void init() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        rectangle = new ScreenSize().getRectangle();
        bufferedImage = robot.createScreenCapture(rectangle);
        imageDimension = new Dimension(rectangle.width, rectangle.height);
        imageSource = new ImageSource(2398, bufferedImage);

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
                oldPoint = e.getPoint();
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
            //bufferedImage = robot.createScreenCapture(rectangle);

            bufferedImage = getResizedBufferedImage();

            g2d.drawImage(bufferedImage, imagePosition.x, imagePosition.y, this);
            g2d.dispose();
            g.dispose();
            repaint();
        }
    }

    private BufferedImage getResizedBufferedImage() {
        BufferedImage resizedImage = new BufferedImage(imageDimension.width, imageDimension.height, 1);
        Graphics2D res = resizedImage.createGraphics();
        res.drawImage(bufferedImage, 0, 0, imageDimension.width, imageDimension.height, this);
        return resizedImage;
    }

    private void calcImageDimension(int d) {
        d *= -1;
        if ((zoomLevel > minZoom + scale && d < 0) || (zoomLevel + scale <= maxZoom && d > 0)) {
            zoomLevel += (d * scale);
            imageDimension.width = (rectangle.width * zoomLevel) / 100;
            imageDimension.height = (rectangle.height * zoomLevel) / 100;
        }
    }

    private void moveImg(Point point) {
        imagePosition.x -= oldPoint.x - point.x;
        imagePosition.y -= oldPoint.y - point.y;
        oldPoint = point;
    }

    public void getFrame(){
        try {
            URL url = new URL("http://127.0.0.1:8080/video");
            bufferedImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        getFrame();
//
//        BufferedImage resizedImage = new BufferedImage(imageDimension.width, imageDimension.height, 1);
//        Graphics2D res = resizedImage.createGraphics();
//        res.drawImage(bufferedImage, 0, 0, imageDimension.width, imageDimension.height, this);
//
//        bufferedImage = resizedImage;
//        g2d.drawImage(bufferedImage, imagePosition.x, imagePosition.y, this);
//        g2d.dispose();
//        g.dispose();
//        repaint();
//
//    }
}
