package client;

import models.Playlist;
import models.Track;
import org.junit.Test;
import xmlparser.ITunesXMLFileParser;
import java.io.IOException;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;


public class SearchItemAPITest {

    String itunesXMLFileOneSong;
    ArrayList<Track> itunesXMLFileOneSongTrackList;
    SearchItemAPI searchItemAPI;
    Playlist oneTrackPlaylist;

    void initData() {
        itunesXMLFileOneSong = "/Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/ITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml";
        itunesXMLFileOneSongTrackList = ITunesXMLFileParser.parse(itunesXMLFileOneSong);
        oneTrackPlaylist = new Playlist("One Track Playlist", "The Best playlist ever", true);
        searchItemAPI = new SearchItemAPI(itunesXMLFileOneSong, oneTrackPlaylist);
    }

    @Test
    public void itTestsReceivesSpotifyTrackJsonStringWithHTTPGet() throws IOException, InterruptedException {
        this.initData();
        ArrayList<Track> instantiatedTrackList1 = ITunesXMLFileParser.parse
                ("/Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/ITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml");
        String instantiatedSpotifyTrackURI = searchItemAPI.receiveSpotifyTrackJsonStringByItemSearchAPI(itunesXMLFileOneSongTrackList.get(0));
        assertThat(instantiatedSpotifyTrackURI).isEqualTo("spotify:track:0COqiPhxzoWICwFCS4eZcp");

    }
}
