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
    private final String spotifyTrackJsonStringList =
            new SearchItemAPI(getXmlFile(), getPlaylist()).buildSpotifyTrackJsonStringList();
    private final SpotifyPlaylistID spotifyPlaylistID =
            new BuildEmptyPlaylistAPI(getXmlFile(), getPlaylist()).buildSpotifyEmptyPlaylist();

    public BuildSpotifyPlaylistAPI(String xmlFile, Playlist playlist) throws IOException, InterruptedException {
        super(xmlFile, playlist);
    }

    public String addSpotifyTracksToEmptyPlaylist() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/playlists/" + getSpotifyPlaylistID() + "/tracks"))
                .headers("Authorization", "Bearer " + getObjectMapper(),
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
        return response.statusCode() + response.body();
    }

    public SpotifyPlaylistID getSpotifyPlaylistID() {
        return spotifyPlaylistID;
    }

    public String getSpotifyTrackJsonStringList() {
        return spotifyTrackJsonStringList;
    }
}