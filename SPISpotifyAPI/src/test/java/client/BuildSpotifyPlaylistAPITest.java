package client;

import models.Playlist;
import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

public class BuildSpotifyPlaylistAPITest {

    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist, token;
    BuildSpotifyPlaylistAPI buildOneSongPlaylist, buildFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;

    void initData() throws IOException, InterruptedException {
        token = "insert token here";

        iTunesXMLFileOneSong = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        buildOneSongPlaylist = new BuildSpotifyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist, token);
        buildFullPlaylist = new BuildSpotifyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist, token);
    }

    @Test
    public void itAddsTracksToPlaylist() throws IOException, InterruptedException {
        this.initData();
        var response1 = buildOneSongPlaylist.addSpotifyTracksToPlaylist();
        var response2 = buildFullPlaylist.addSpotifyTracksToPlaylist();
        assertThat(response1.substring(0,3)).isEqualTo("201");
        assertThat(response2.substring(0,3)).isEqualTo("201");
    }
    
    @Test
    public void testClassFields() throws IOException, InterruptedException {
        this.initData();
        assertThat(buildOneSongPlaylist.getSpotifyPlaylistID().getValue().length()).isEqualTo(22);
        assertThat(buildOneSongPlaylist.getSpotifyTrackJsonStringList().length()).isEqualTo(49);

        assertThat(buildFullPlaylist.getSpotifyPlaylistID().getValue().length()).isEqualTo(22);
        assertThat(buildFullPlaylist.getSpotifyTrackJsonStringList().length()).isEqualTo(790);
    }
}
