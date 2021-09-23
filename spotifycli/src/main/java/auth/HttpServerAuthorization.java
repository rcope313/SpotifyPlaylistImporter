package auth;

import com.sun.net.httpserver.HttpServer;
import config.ServerConfiguration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HttpServerAuthorization {

    public ServerConfiguration config;

    public HttpServerAuthorization() {
         config = new ServerConfiguration("localhost", 2000, "/authorize", 60);

    }

    void start() {
        try {
            var latch = new CountDownLatch(1);
            var server = HttpServer.create(new InetSocketAddress(config.getHost(), config.getPort()), 0);
            server.createContext(config.getContext(), exchange -> {
                var code = exchange.getRequestURI().toString().split("=")[1];
                System.out.println("Code:" + code);

                var response = "";

                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));

                latch.countDown();
            });
            server.start();
            System.out.println("Waiting for redirect URI...");
            latch.await(config.getTimeout(), TimeUnit.SECONDS);
            server.stop(0);


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
