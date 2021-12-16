package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.SpotifyTrackURI;
import models.Track;
import utility.Utils;
import xmlparser.ITunesXMLFileParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class SearchItemAPI extends SpotifyClient {

    public SearchItemAPI(String xmlFile) {
        super(xmlFile);
    }

    public String buildSpotifyTrackJsonStringList() throws IOException {
        ArrayList<String> spotifyTrackJsonStringList = buildSpotifyTrackJsonStringListByItemSearchAPI(ITunesXMLFileParser.parse(getXmlFile()), getToken());
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = buildListOfSpotifyTrackURIFromJsonString(spotifyTrackJsonStringList);
        return buildSpotifyTrackURIJsonString(listOfSpotifyTrackURI);
    }

    private static String receiveSpotifyTrackJsonStringByItemSearchAPI(Track track, String token) throws IOException, InterruptedException {

        String trackNameURL = uRLify(track.getTrackName());
        String artistNameURL = uRLify(track.getArtistName());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/search?" +
                        "q=artist:" + artistNameURL + "%20" +
                        "track:'" + trackNameURL +
                        "'&type=track" +
                        "&limit=1"))
                .header("Authorization", "Bearer " + token)
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
    
    private static ArrayList<String> buildSpotifyTrackJsonStringListByItemSearchAPI(ArrayList<Track> currentTracks, String token) {

        ArrayList<String> jsonTrackList = new ArrayList<>();
        currentTracks.forEach((track) -> {
            try {
                jsonTrackList.add(receiveSpotifyTrackJsonStringByItemSearchAPI(track, token));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return jsonTrackList;
    }

    private ArrayList<SpotifyTrackURI> buildListOfSpotifyTrackURIFromJsonString (ArrayList<String> spotifyTrackJsonStringList) throws IOException {
        ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI = new ArrayList<>();
        for (String aJsonString : spotifyTrackJsonStringList ) {
            listOfSpotifyTrackURI.add(this.getObjectMapper().readValue(aJsonString, SpotifyTrackURI.class));
        }
        return listOfSpotifyTrackURI;
    }

    private String buildSpotifyTrackURIJsonString (ArrayList<SpotifyTrackURI> listOfSpotifyTrackURI) throws JsonProcessingException {
        ObjectNode rootNode = this.getObjectMapper().createObjectNode();
        ArrayNode arrayNode = rootNode.putArray("uris");
        listOfSpotifyTrackURI.forEach((aSpotifyTrackURI) -> arrayNode.add(aSpotifyTrackURI.getUriName()));

        return this.getObjectMapper().writeValueAsString(rootNode);
    }

    static String uRLify(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");
        return s;
    }
}
