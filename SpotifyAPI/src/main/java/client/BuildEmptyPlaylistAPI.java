package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyPlaylistID;
import org.assertj.core.util.VisibleForTesting;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class BuildEmptyPlaylistAPI extends SpotifyClient{

    BuildEmptyPlaylistAPI(String xmlFile, Playlist playlist) {
        super(xmlFile, playlist);
    }

    SpotifyPlaylistID buildSpotifyEmptyPlaylistWithNewSpotifyPlaylistID() throws IOException, InterruptedException {
        String jsonPlaylist = serializePlaylistToJsonString();
        String playlistResponseBody = this.createNewPlaylist(jsonPlaylist);
        return getPlaylistURI(playlistResponseBody);
    }

    @VisibleForTesting
    SpotifyPlaylistID getPlaylistURI (String playlistResponseBody) throws IOException {
        return this.getObjectMapper().readValue(playlistResponseBody, SpotifyPlaylistID.class);
    }

    // is this hard coded??
    @VisibleForTesting
    String createNewPlaylist(String jsonPlaylist) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/users/1287343652/playlists"))
                .header("Authorization", "Bearer " + this.getToken())
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

    @VisibleForTesting
    String serializePlaylistToJsonString() throws JsonProcessingException {
        return this.getObjectMapper().writeValueAsString(this.getPlaylist());
    }
}
