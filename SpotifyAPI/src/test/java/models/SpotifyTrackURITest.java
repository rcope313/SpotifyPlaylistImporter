package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class SpotifyTrackURITest {
    File jsonSpotifySearchItem;

    void initData() {
        jsonSpotifySearchItem = new File("//Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/SpotifyAPI/src/main/resources/SpotifySearchItemJsonFile.json");
    }

    @Test
    public void itDeserializesJsonValueIntoPlayListIDBean() throws IOException {
        this.initData();

        SpotifyTrackURI spotifyTrackURI = new ObjectMapper().readValue(jsonSpotifySearchItem, SpotifyTrackURI.class);
        assertThat(spotifyTrackURI.getUriName()).isEqualTo("spotify:track:0COqiPhxzoWICwFCS4eZcp");
    }
}
