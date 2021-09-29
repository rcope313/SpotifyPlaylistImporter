package auth;

import config.ClientConfig;
import config.GetAccessAndRefreshTokensResponseConfig;
import picocli.CommandLine;
import java.io.IOException;


@CommandLine.Command(name = "auth")
public class CommandAuth implements Runnable {

    final ClientConfig clientConfig;
    final HttpServerAuth httpServerAuth;

    public CommandAuth() {
        this.clientConfig = new ClientConfig(
                "https://accounts.spotify.com/authorize",
                "58f5ea655fc64389ae8f53047aa14201",
                "code",
                "playlist-modify-private"
        );
        this.httpServerAuth = new HttpServerAuth();
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
       httpServerAuth.start();



    }

    private void displayAuthUrl() throws IOException, InterruptedException {
        System.out.println("Click on the link to start authentication:\n");
        System.out.println(clientConfig.generateAuthUri(httpServerAuth.serverConfiguration.getRedirectUri()));
    }
}






