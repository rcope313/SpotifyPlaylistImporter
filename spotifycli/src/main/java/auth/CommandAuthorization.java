package auth;

import config.ClientConfiguration;
import picocli.CommandLine;
import java.io.IOException;


@CommandLine.Command(name = "auth")
public class CommandAuthorization implements Runnable {

    final ClientConfiguration clientConfiguration;
    final HttpServerAuthorization httpServerAuthorization;

    public CommandAuthorization() {
        this.clientConfiguration = new ClientConfiguration(
                "https://accounts.spotify.com/authorize",
                "58f5ea655fc64389ae8f53047aa14201",
                "code",
                "playlist-modify-private"
        );
        this.httpServerAuthorization = new HttpServerAuthorization();
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

        handleRedirectUri();

        System.exit(0);
    }

    private void handleRedirectUri() {
        httpServerAuthorization.start();

    }

    private void displayAuthUrl() throws IOException, InterruptedException {
        System.out.println("Click on the link to start authentication:\n");
        System.out.println(clientConfiguration.generateAuthUri(httpServerAuthorization.config.getRedirectUri()));
    }
}






