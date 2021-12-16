package spotifyapi;

import models.Track;
import utility.Utils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class SearchItemAPI {

    public static ArrayList<String> getJsonTrackListFromItemSearch (ArrayList<Track> currentTracks, String token) {

        ArrayList<String> jsonTrackList = new ArrayList<>();
        currentTracks.forEach((track) -> {
            try {
                jsonTrackList.add(getAJsonTrackFromItemSearch(track, token));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return jsonTrackList;
    }

    public static String getAJsonTrackFromItemSearch(Track track, String token) throws IOException, InterruptedException {

        String trackNameURL = Utils.uRLify(track.getTrackName());
        String artistNameURL = Utils.uRLify(track.getArtistName());

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
}







