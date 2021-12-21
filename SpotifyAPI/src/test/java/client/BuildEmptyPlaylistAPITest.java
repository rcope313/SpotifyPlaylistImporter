package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyPlaylistID;
import models.Track;
import org.junit.Test;
import xmlparser.ITunesXMLFileParser;

import java.io.IOException;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

public class BuildEmptyPlaylistAPITest {
    
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist;
    ArrayList<Track> oneSongTrackList, fullPlaylistTrackList;
    BuildEmptyPlaylistAPI buildEmptyOneSongPlaylist, buildEmptyFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;

    void initData() {
        iTunesXMLFileOneSong = "src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/ITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";

        oneSongTrackList = ITunesXMLFileParser.parse(iTunesXMLFileOneSong);
        fullPlaylistTrackList = ITunesXMLFileParser.parse(iTunesXMLFileFullPlaylist);

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        buildEmptyOneSongPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist);
        buildEmptyFullPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist);
    }

    @Test
    public void itRequestsCreatePlaylistAndReceivesHTTPPostResponseBody() throws IOException, InterruptedException {
        this.initData();
        String jsonPlaylist1 = buildEmptyOneSongPlaylist.serializePlaylistToJsonString();
        String jsonNewPlaylist1 = buildEmptyOneSongPlaylist.createNewPlaylist(jsonPlaylist1);
        assertThat(buildEmptyOneSongPlaylist
                .getPlaylistURI(jsonNewPlaylist1)
                .getValue().length())
                .isEqualTo(22);

        String jsonPlaylist2 = buildEmptyFullPlaylist.serializePlaylistToJsonString();
        String jsonNewPlaylist2 = buildEmptyFullPlaylist.createNewPlaylist(jsonPlaylist2);
        assertThat(buildEmptyFullPlaylist
                .getPlaylistURI(jsonNewPlaylist2)
                .getValue().length())
                .isEqualTo(22);
    }

    @Test
    public void itSerializesJsonStringToPlaylist() throws JsonProcessingException {
        this.initData();
        String jsonString1 = buildEmptyOneSongPlaylist.serializePlaylistToJsonString();
        assertThat(jsonString1).isEqualTo(
                "{\"name\":\"One Song Playlist\"," +
                "\"description\":\"A playlist of one song\"," +
                "\"public\":true}");

        String jsonString2 = buildEmptyFullPlaylist.serializePlaylistToJsonString();
        assertThat(jsonString2).isEqualTo(
                "{\"name\":\"Full Playlist\"," +
                "\"description\":\"a playlist of 20 songs\"," +
                "\"public\":false}");
    }
}
