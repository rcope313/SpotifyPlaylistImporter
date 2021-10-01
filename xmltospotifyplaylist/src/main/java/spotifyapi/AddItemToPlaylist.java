package spotifyapi;

import manager.TokenManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class AddItemToPlaylist {

    public String addItemToPlaylist(String jsonTrackURIs, String playlistID) throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/playlists/" + playlistID + "/tracks"))
                .headers("Authorization", TokenManager.getToken(),
                         "Content-Type", "application/json")
                .POST(HttpRequest
                        .BodyPublishers
                        .ofString(jsonTrackURIs))
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