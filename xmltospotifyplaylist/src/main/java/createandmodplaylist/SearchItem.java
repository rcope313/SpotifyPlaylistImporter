package createandmodplaylist;

import models.Track;
import utility.Utils;
import xmlparser.ReadXmlSaxParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class SearchItem extends SpotifyAPIUsage {

    public SearchItem() {}

    public ArrayList<String> getSearchItemResponseList(String fileName) {

        ArrayList<String> searchItemResponseList = new ArrayList<>();
        ArrayList<Track> currentTracks = ReadXmlSaxParser.parse(fileName);

        currentTracks.forEach((track) -> {
            try {
                searchItemResponseList.add(getATrackResponseBody(track));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return searchItemResponseList;

    }

    public String getATrackResponseBody(Track track) throws IOException, InterruptedException {

        String trackNameURL = Utils.uRLify(track.trackName);
        String artistNameURL = Utils.uRLify(track.artistName);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.spotify.com/v1/search?" +
                        "q=artist:" + artistNameURL + "%20" +
                        "track:'" + trackNameURL +
                        "'&type=track" +
                        "&limit=1"))
                .header("Authorization", OAUTH_TOKEN)
                .GET()
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






