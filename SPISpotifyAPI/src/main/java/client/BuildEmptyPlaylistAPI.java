package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyPlaylistID;
import models.SpotifyUser;
import org.assertj.core.util.VisibleForTesting;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

class BuildEmptyPlaylistAPI extends SpotifyClient {
    private final String token;

    BuildEmptyPlaylistAPI(String xmlFile, Playlist playlist, String token) {
        super(xmlFile, playlist);
        this.token = token;
    }

    SpotifyPlaylistID buildSpotifyEmptyPlaylistAndSpotifyPlaylistID() throws IOException, InterruptedException {
        String jsonPlaylist = serializePlaylistToJsonString();
        String playlistResponseBody = this.createNewPlaylist(jsonPlaylist);
        return getPlaylistURI(playlistResponseBody);
    }

    @VisibleForTesting
    SpotifyPlaylistID getPlaylistURI(String playlistResponseBody) throws IOException {
        return this.getObjectMapper().readValue(playlistResponseBody, SpotifyPlaylistID.class);
    }

    @VisibleForTesting
    String createNewPlaylist(String jsonPlaylist) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/users/" + getSpotifyUser().getId() + "/playlists"))
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

        if (response.statusCode() == 401) {
            throw new IllegalStateException("Access token invalid or expired. Please re-authenticate");
        }
        return response.body();
    }

    public SpotifyUser getSpotifyUser() throws IOException, InterruptedException {
        return new GetUserProfileAPI(getXmlFile(), getPlaylist(), getToken()).getCurrentUsersProfile();
    }

    @VisibleForTesting
    String serializePlaylistToJsonString() throws JsonProcessingException {
        return this.getObjectMapper().writeValueAsString(this.getPlaylist());
    }

    public String getToken() {
        return token;
    }
}
