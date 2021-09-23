package auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class SpotifyClientAuthorization {

    String getAccessAndRefreshTokens (String code) throws IOException, InterruptedException {

        String jsonstring = "";

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://accounts.spotify.com/api/token"))
            .headers("Authorization", "oauth-token",
                    "Content-Type", "application/json")
            .POST(HttpRequest
                    .BodyPublishers
                    .ofString(jsonstring))
            .build();

    HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(20))
            .build();


    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() + response.body();

    }

}

