package auth;

import models.PostRequest;
import models.PostResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SpotifyClientAuth {
    private final String code;
    private final PostRequest postRequest;

    SpotifyClientAuth(String code) {
        this.code = code;
        this.postRequest = new PostRequest(code);
    }

    public PostResponse getAccessAndRefreshTokens () throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://accounts.spotify.com/api/token"))
            .headers("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest
                    .BodyPublishers
                    .ofString(getPostRequest().buildJsonBodyParameter()))
            .build();

        HttpClient client = HttpClient.newBuilder()
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return PostResponse.deserializeJsonPostResponse(response.body());
    }

    public String getCode() {
        return code;
    }

    public PostRequest getPostRequest() {
        return postRequest;
    }
}
