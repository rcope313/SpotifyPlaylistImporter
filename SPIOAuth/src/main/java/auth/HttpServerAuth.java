package auth;

import com.sun.net.httpserver.HttpServer;
import models.HttpServerConfig;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import models.PostResponse;

public class HttpServerAuth {

    final public HttpServerConfig httpServerConfig;
    public String code;

    public HttpServerAuth() {
        this.httpServerConfig = new HttpServerConfig();
    }

    public PostResponse start() {
        try {
            var latch = new CountDownLatch(1);
            var server = HttpServer.create(new InetSocketAddress(httpServerConfig.getHost(), httpServerConfig.getPort()), 0);
            server.createContext(httpServerConfig.getContext(), exchange -> {
                this.code = exchange.getRequestURI().toString().split("=")[1];
                var response = "Success! Authentication completed. You can close web browser and return to the terminal window.";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
                latch.countDown();
            });
            server.start();
            System.out.println("Waiting for redirect URI...");
            latch.await(httpServerConfig.getTimeout(), TimeUnit.SECONDS);
            server.stop(0);
            return this.getPostResponse();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public PostResponse getPostResponse() throws IOException, InterruptedException {
        if (code == null) {
            throw new IllegalStateException("Code has not been retrieved for authentication. Please authenticate again.");
        } else {
            System.out.print("Success! You are now authenticated!\n");
            return new SpotifyClientAuth(this.code).getAccessAndRefreshTokens();
        }
    }
}