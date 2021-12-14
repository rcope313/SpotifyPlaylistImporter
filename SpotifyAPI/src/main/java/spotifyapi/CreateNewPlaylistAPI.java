package spotifyapi;

import models.Playlist;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class CreateNewPlaylistAPI {

    public static String createNewPlaylist(Playlist playlist, String jsonPlaylist, String token) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/users/1287343652/playlists"))
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest
                        .BodyPublishers
                        .ofString(jsonPlaylist))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}

