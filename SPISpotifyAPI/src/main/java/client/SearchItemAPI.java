package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Playlist;
import models.SpotifyTrackURI;
import models.Track;
import org.assertj.core.util.VisibleForTesting;
import xmlparser.ITunesXMLFileParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

class SearchItemAPI extends SpotifyClient {
    private final String token;

    SearchItemAPI(String xmlFile, Playlist playlist, String token) {
        super(xmlFile, playlist);
        this.token = token;
    }

    String buildJsonSpotifyTrackURIList() throws IOException {
        ArrayList<String> spotifyTrackJsonStringList = buildJsonTrackListByItemSearchAPI();
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = buildSpotifyTrackURIListFromJsonTrackList(spotifyTrackJsonStringList);
        return buildJsonSpotifyTrackURIList(listOfSpotifyTrackURI);
    }

    @VisibleForTesting
    String buildJsonSpotifyTrackURIList(ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI) throws JsonProcessingException {
        ObjectNode rootNode = this.getObjectMapper().createObjectNode();
        ArrayNode arrayNode = rootNode.putArray("uris");
        listOfSpotifyTrackURI.forEach((aSpotifyTrackURI) -> arrayNode.add(aSpotifyTrackURI.getUriName()));

        return this.getObjectMapper().writeValueAsString(rootNode);
    }

    @VisibleForTesting
    ArrayList<SpotifyTrackURI> buildSpotifyTrackURIListFromJsonTrackList(ArrayList<String> spotifyTrackJsonStringList) throws IOException {
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = new ArrayList<>();
        for (String aJsonString : spotifyTrackJsonStringList ) {
            listOfSpotifyTrackURI.add(this.getObjectMapper().readValue(aJsonString, SpotifyTrackURI.class));
        }
        return listOfSpotifyTrackURI;
    }

    @VisibleForTesting
    ArrayList<String> buildJsonTrackListByItemSearchAPI() {
        ArrayList<Track> tracks = ITunesXMLFileParser.parse(getXmlFile());
        ArrayList<String> jsonTrackList = new ArrayList<>();
        tracks.forEach((track) -> {
            try {
                jsonTrackList.add(getJsonTrackByItemSearchAPI(track));
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        return jsonTrackList;
    }

    @VisibleForTesting
    String getJsonTrackByItemSearchAPI(Track track) throws IOException, InterruptedException {
        String trackNameURL = uRLify(track.getTrackName());
        String artistNameURL = uRLify(track.getArtistName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/search?" +
                        "q=artist:" + artistNameURL + "%20" +
                        "track:'" + trackNameURL +
                        "'&type=track" +
                        "&limit=1"))
                .header("Authorization", "Bearer " + getToken())
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.body() == null) {
            System.out.print("trackNameURL unable to be added");
        }
        if (response.statusCode() == 401) {
            throw new IllegalStateException("Access token invalid or expired. Please reauthenticate");
        }
        return response.body();
    }

    private static String uRLify(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");
        return s;
    }

    public String getToken() {
        return token;
    }
}
