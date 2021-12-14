package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jsonparser.JsonDeserializeItemSearchOutput;
import java.io.File;
import java.io.IOException;

@JsonDeserialize(using = JsonDeserializeItemSearchOutput.class)
public class TrackURI {
    public String uriName;

    public TrackURI(String uriName) {
        this.uriName = uriName;
    }

    public static void main(String[] args) throws IOException {

        File jsonFile = new File ("/Users/rachelcope/Documents/ITunesXMLtoSpotifyPlaylist/src/main/resources/searchitemoutput.json");

        TrackURI trackURI = new ObjectMapper().readValue(jsonFile, TrackURI.class);
        System.out.print(trackURI);

    }
}
