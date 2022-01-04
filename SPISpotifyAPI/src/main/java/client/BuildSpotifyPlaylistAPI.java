package client;

import models.Playlist;
import models.SpotifyPlaylistID;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BuildSpotifyPlaylistAPI extends SpotifyClient{
    private final String token;

    public BuildSpotifyPlaylistAPI(String xmlFile, Playlist playlist, String token) {
        super(xmlFile, playlist);
        this.token = token;
    }

    public String addSpotifyTracksToPlaylist() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/playlists/" + getSpotifyPlaylistID().getValue() + "/tracks"))
                .headers("Authorization", "Bearer " + getToken(),
                         "Content-Type", "application/json")
                .POST(HttpRequest
                        .BodyPublishers
                        .ofString(getSpotifyTrackJsonStringList()))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new IllegalStateException("Access token expired. Please re-authenticate");
        }
        System.out.print("Playlist imported. Enjoy listening!");
        return response.statusCode() + response.body();
    }

    public SpotifyPlaylistID getSpotifyPlaylistID() throws IOException, InterruptedException {
        return new BuildEmptyPlaylistAPI(getXmlFile(), getPlaylist(), getToken()).buildSpotifyEmptyPlaylistAndSpotifyPlaylistID();
    }

    public String getSpotifyTrackJsonStringList() throws IOException {
        return new SearchItemAPI(getXmlFile(), getPlaylist(), getToken()).buildJsonStringOfListOfSpotifyTrackURI();
    }

    public String getToken() {
        return token;
    }
}
