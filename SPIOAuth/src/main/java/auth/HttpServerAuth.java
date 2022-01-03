package auth;

import models.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import picocli.CommandLine;

public class HttpServerAuth {

    final public HttpServer httpServer;

    public HttpServerAuth(HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    public void start() {
        try {
            var latch = new CountDownLatch(1);
            var server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(httpServer.getHost(), httpServer.getPort()), 0);
            server.createContext(httpServer.getContext(), exchange -> {
                var code = exchange.getRequestURI().toString().split("=")[1].split("&")[0];
                SpotifyClientAuth clientAuthorization = new SpotifyClientAuth(code);
                try {
                    clientAuthorization.getAccessAndRefreshTokens();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                var response = "Success! Authentication completed. You can close web browser and return to the terminal window.";
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
                latch.countDown();
            });
            server.start();
            latch.await(httpServer.getTimeout(), TimeUnit.SECONDS);
            server.stop(0);
            System.out.println(CommandLine.Help.Ansi.ON.string("@|bold,fg(green) Success! You are now authenticated! " +
                    "You are now able to create your playlist with the 'execute' command.|@"));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
