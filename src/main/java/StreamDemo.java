import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class StreamDemo {
    ImageProvider imageProvider = new ImageProvider();

    public void init(){
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/video", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            BufferedImage bi = imageProvider.getImg();

            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", tmp);
            tmp.close();

            t.sendResponseHeaders(200, tmp.size());
            OutputStream os = t.getResponseBody();
            os.write(tmp.toByteArray());
            os.close();
            System.out.println("zapros");
        }
    }

}
