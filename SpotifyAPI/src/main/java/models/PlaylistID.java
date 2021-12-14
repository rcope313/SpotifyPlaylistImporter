package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jsonparser.JsonDeserializePlaylist;
import java.io.File;
import java.io.IOException;

@JsonDeserialize(using = JsonDeserializePlaylist.class)
public class PlaylistID {
    public String  id;

    public PlaylistID(String id) {
        this.id = id;
    }

    public static void main(String[] args) throws IOException {

        File jsonFile = new File("/Users/rachelcope/Documents/ITunesXMLtoSpotifyPlaylist/src/main/resources/playlistOutput.json");

        PlaylistID playlistID = new ObjectMapper().readValue(jsonFile, PlaylistID.class);
        System.out.print(playlistID);
    }
}
