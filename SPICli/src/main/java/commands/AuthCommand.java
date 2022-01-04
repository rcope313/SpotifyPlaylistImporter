package commands;

import auth.HttpServerAuth;
import auth.RequestUserAuth;
import models.PostResponse;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "auth")
public class AuthCommand implements Runnable {
    final RequestUserAuth requestUserAuth;
    final HttpServerAuth httpServerAuth;

    public AuthCommand(RequestUserAuth requestUserAuth, HttpServerAuth httpServerAuth) {
        this.requestUserAuth = requestUserAuth;
        this.httpServerAuth = httpServerAuth;
    }

    public static void main (String[] args) {
        AuthCommand command = new AuthCommand(new RequestUserAuth(), new HttpServerAuth());
        command.run();
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


    private PostResponse handleRedirectUri() throws IOException {
         var response = getHttpServerAuth().start();
         return response;
    }
    private void displayAuthUrl() throws IOException, InterruptedException {
        System.out.println("Click on the link to start authentication:\n");
        System.out.println(getRequestUserAuth().generateAuthUri());
    }

    public RequestUserAuth getRequestUserAuth() {
        return requestUserAuth;
    }

    public HttpServerAuth getHttpServerAuth() {
        return httpServerAuth;
    }
}






