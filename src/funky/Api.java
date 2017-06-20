package funky;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class Api implements HttpHandler {

    private Display display = null;

    public Api(Display display) {
        this.display = display;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            String response = ""; //todo

            httpExchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private JSONObject getNofLeds(){
        JSONObject json = new JSONObject();
        json.put("leds", display.leds.size());
        return json;
    }


}
