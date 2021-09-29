package auth;

import config.GetAccessAndRefreshTokensRequestConfig;
import config.GetAccessAndRefreshTokensResponseConfig;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SpotifyClientAuth {
    String code;
    public GetAccessAndRefreshTokensRequestConfig postRequestConfig;

    SpotifyClientAuth(String code) {
        this.code = code;
        this.postRequestConfig = new GetAccessAndRefreshTokensRequestConfig(code);
    }


    public GetAccessAndRefreshTokensResponseConfig getAccessAndRefreshTokens () throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://accounts.spotify.com/api/token"))
            .headers("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest
                    .BodyPublishers
                    .ofString(postRequestConfig.jsonBodyParameter()))
            .build();

        HttpClient client = HttpClient.newBuilder()
            .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return GetAccessAndRefreshTokensResponseConfig.deserializeJsonPostResponse(response.body());

    }

}

