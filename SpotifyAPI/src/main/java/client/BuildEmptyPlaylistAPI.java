package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyPlaylistID;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BuildEmptyPlaylistAPI extends SpotifyClient{
    private final Playlist playlist;

    public BuildEmptyPlaylistAPI(String xmlFile, Playlist playlist) {
        super(xmlFile);
        this.playlist = playlist;
    }

    private SpotifyPlaylistID buildSpotifyEmptyPlaylist() throws IOException, InterruptedException {
        String jsonPlaylist = serializePlaylistToJsonString(this.getPlaylist());
        String playlistResponseBody = BuildEmptyPlaylistAPI.createNewPlaylist(jsonPlaylist, this.getToken());
        return getPlaylistURI(playlistResponseBody);
    }

    private String serializePlaylistToJsonString(Playlist playlist) throws JsonProcessingException {
        return this.getObjectMapper().writeValueAsString(playlist);
    }

    // is this hard coded??
    public static String createNewPlaylist(String jsonPlaylist, String token) throws IOException, InterruptedException {
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

    private SpotifyPlaylistID getPlaylistURI (String playlistResponseBody) throws IOException {
        return this.getObjectMapper().readValue(playlistResponseBody, SpotifyPlaylistID.class);
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
