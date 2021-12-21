package commands;

import auth.HttpServerAuth;
import config.ClientConfig;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.io.IOException;


@CommandLine.Command(name = "auth")
public class AuthCommand implements Runnable {

    final ClientConfig clientConfig;
    final HttpServerAuth httpServerAuth;

    @Inject
    public AuthCommand(ClientConfig clientConfig, HttpServerAuth httpServerAuth) {
        this.clientConfig = clientConfig;
        this.httpServerAuth = httpServerAuth;
    }

    @Override
    public void run() {
        try {
            displayAuthUrl();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            handleRedirectUri();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.exit(0);
    }


    private void handleRedirectUri() throws IOException {
         httpServerAuth.start();

    }

    private void displayAuthUrl() throws IOException, InterruptedException {
        System.out.println("Click on the link to start authentication:\n");
        System.out.println(clientConfig.generateAuthUri(httpServerAuth.httpServerConfig.getRedirectUri()));
    }
}






