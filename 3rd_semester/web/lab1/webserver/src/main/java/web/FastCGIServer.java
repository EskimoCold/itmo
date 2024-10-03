package web;

import com.fastcgi.FCGIInterface;
import com.google.gson.Gson;
import web.network.Request;
import web.network.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FastCGIServer extends FCGIInterface {
    private static final Logger logger = Logger.getLogger(FastCGIServer.class.getName());

    public static void main(String[] args) {
        FastCGIServer server = new FastCGIServer();
        logger.info("FastCGI server started, waiting for requests...");

        while (true) {
            try {
                int acceptResult = server.FCGIaccept();

                if (acceptResult >= 0) {
                    if (request != null && request.socket != null) {
                        logger.info("Received a new request, processing...");
                        server.handleRequest();
                    } else {
                        logger.warning("Request object or socket is null after FCGIaccept.");
                    }
                } else {
                    logger.warning("Failed to accept a request. Waiting for the next request...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.log(Level.SEVERE, "Thread interrupted during sleep", e);
                    }
                }
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Got string", e);
            }
        }
    }

    private void handleRequest() {
        try {
            if (request == null || request.socket == null) {
                logger.warning("Request or socket is null, cannot handle the request.");
                return;
            }

            String jsonRequest;
            String httpResponse;

            try {
                jsonRequest = readRequestBody();
            } catch (IOException e) {
                httpResponse = Response.httpResponse(e.getMessage().getBytes(StandardCharsets.UTF_8).length + 2, e.getMessage());
                logger.log(Level.INFO, httpResponse);
                System.out.println(httpResponse);
                return;
            }

            Gson gson = new Gson();
            Request request = gson.fromJson(jsonRequest, Request.class);

            Response response;
            try {
                response = Response.handleRequest(request);
            } catch (IllegalArgumentException e) {
                httpResponse = Response.httpResponse("{\"response\": \"%s\"}".formatted(e.getMessage()).getBytes(StandardCharsets.UTF_8).length + 2,
                        "{\"response\": \"%s\"}".formatted(e.getMessage()));
                logger.log(Level.INFO, httpResponse);
                System.out.println(httpResponse);
                return;
            }

            String jsonResponse = gson.toJson(response, Response.class);

            httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: application/json
                    Content-Length: %d

                    %s
                    """.formatted(jsonResponse.getBytes(StandardCharsets.UTF_8).length + 2, jsonResponse);

            logger.log(Level.INFO, httpResponse);
            System.out.println(httpResponse);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private static String readRequestBody() throws IOException {
        FCGIInterface.request.inStream.fill();
        var contentLength = FCGIInterface.request.inStream.available();
        var buffer = ByteBuffer.allocate(contentLength);
        var readBytes =
                FCGIInterface.request.inStream.read(buffer.array(), 0,
                        contentLength);
        var requestBodyRaw = new byte[readBytes];
        buffer.get(requestBodyRaw);
        buffer.clear();
        return new String(requestBodyRaw, StandardCharsets.UTF_8);
    }
}