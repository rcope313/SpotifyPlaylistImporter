package auth;

import com.sun.net.httpserver.HttpServer;
import config.HttpServerConfig;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import picocli.CommandLine;

public class HttpServerAuth {

    final public HttpServerConfig httpServerConfig;

    public HttpServerAuth(HttpServerConfig httpServerConfig) {
        this.httpServerConfig = httpServerConfig;

    }

    public void start() {

        try {
            var latch = new CountDownLatch(1);
            var server = HttpServer.create(new InetSocketAddress(httpServerConfig.getHost(), httpServerConfig.getPort()), 0);
            server.createContext(httpServerConfig.getContext(), exchange -> {

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
            latch.await(httpServerConfig.getTimeout(), TimeUnit.SECONDS);
            server.stop(0);

            System.out.println(CommandLine.Help.Ansi.ON.string("@|bold,fg(green) Success! You are now authenticated! " +
                    "You are now able to create your playlist with the 'execute' command.|@"));


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
    }


}