package panel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketImageDownloader extends Thread {

    private Socket server;
    private ServerSocket serverSocket;
    private BufferedImage bufferedImage;

    public SocketImageDownloader(int port, BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    public void run() {

        while (true) {
            try {
                server = serverSocket.accept();
                System.out.println("accepted");
                server.setSoTimeout(1000);
                while (true) {
                    BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
                    if (img != null) {
                        System.out.println("received");
                        img.createGraphics().drawImage(bufferedImage, 0, 0, null);
                    } else System.out.println("null");
                }
            } catch (SocketTimeoutException st) {
                System.out.println("Socket timed out!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}