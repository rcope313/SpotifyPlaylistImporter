package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;
import models.SpotifyUser;
import org.assertj.core.util.VisibleForTesting;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GetUserProfileAPI extends SpotifyClient{
    private final String token;

    GetUserProfileAPI(String xmlFile, Playlist playlist, String token) {
        super(xmlFile, playlist);
        this.token = token;
    }

    @VisibleForTesting
    SpotifyUser getCurrentUsersProfile() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/me"))
                .header("Authorization", "Bearer " + getToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new IllegalStateException("Access token invalid or expired. Please reauthenticate");
        }
        return getObjectMapper().readValue(response.body(), SpotifyUser.class);
    }

    public String getToken() {
        return token;
    }
}
