package client;

import models.Playlist;
import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

public class BuildSpotifyPlaylistAPITest {

    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist;
    BuildSpotifyPlaylistAPI buildOneSongPlaylist, buildFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;

    void initData() throws IOException, InterruptedException {
        iTunesXMLFileOneSong = "src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/ITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        buildOneSongPlaylist = new BuildSpotifyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist);
        buildFullPlaylist = new BuildSpotifyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist);
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
