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

    SearchItemAPI(String xmlFile, Playlist playlist) {
        super(xmlFile, playlist);
    }

    String buildJsonStringOfListOfSpotifyTrackURI() throws IOException {
        ArrayList<String> spotifyTrackJsonStringList = buildListOfJsonStringByItemSearchAPI(ITunesXMLFileParser.parse(getXmlFile()));
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = buildListOfSpotifyTrackURIFromListOfJsonString(spotifyTrackJsonStringList);
        return buildJsonStringOfListOfSpotifyTrackURI(listOfSpotifyTrackURI);
    }

    @VisibleForTesting
    String buildJsonStringOfListOfSpotifyTrackURI(ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI) throws JsonProcessingException {
        ObjectNode rootNode = this.getObjectMapper().createObjectNode();
        ArrayNode arrayNode = rootNode.putArray("uris");
        listOfSpotifyTrackURI.forEach((aSpotifyTrackURI) -> arrayNode.add(aSpotifyTrackURI.getUriName()));

        return this.getObjectMapper().writeValueAsString(rootNode);
    }

    @VisibleForTesting
    ArrayList<SpotifyTrackURI> buildListOfSpotifyTrackURIFromListOfJsonString(ArrayList<String> spotifyTrackJsonStringList) throws IOException {
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = new ArrayList<>();
        for (String aJsonString : spotifyTrackJsonStringList ) {
            listOfSpotifyTrackURI.add(this.getObjectMapper().readValue(aJsonString, SpotifyTrackURI.class));
        }
        return listOfSpotifyTrackURI;
    }

    @VisibleForTesting
    ArrayList<String> buildListOfJsonStringByItemSearchAPI(ArrayList<Track> currentTracks) {
        ArrayList<String> jsonTrackList = new ArrayList<>();
        currentTracks.forEach((track) -> {
            try {
                jsonTrackList.add(receiveAJsonStringByItemSearchAPI(track));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return jsonTrackList;
    }

    @VisibleForTesting
    String receiveAJsonStringByItemSearchAPI(Track track) throws IOException, InterruptedException {

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
        return response.body();
    }

    private static String uRLify(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");
        return s;
    }
}
