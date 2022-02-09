import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class StreamDemo {
    ImageProvider imageProvider = new ImageProvider();

    public void init() {
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/video", new FrameHandler());
        server.createContext("/", new MainHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    class FrameHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            BufferedImage bi = imageProvider.getImg();

            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", tmp);

            exchange.sendResponseHeaders(200, tmp.size());
            OutputStream os = exchange.getResponseBody();
            os.write(tmp.toByteArray());
            os.close();
        }
    }

    class MainHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            try (
                    InputStream in = getClass().getClassLoader().getResourceAsStream("image.html");
                    OutputStream out = exchange.getResponseBody();
            ) {
                int length = 0;
                byte[] bytes = new byte[1024];

                exchange.sendResponseHeaders(200, in.available());

                // copy data from input stream to output stream
                while ((length = in.read(bytes)) != -1) {
                    out.write(bytes, 0, length);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
