package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class SpotifyPlaylistIDTest {

    File jsonPlaylistOutputExample;

    void initData() {
        jsonPlaylistOutputExample = new File("/Users/rachelcope/Documents/ITunesPlaylistToSpotifyPlaylist/SpotifyAPI/src/main/resources/NewSpotifyPlayJsonFile.json");
    }

    @Test
    public void itDeserializesJsonValueIntoPlayListIDBean() throws IOException {
        this.initData();

        SpotifyPlaylistID spotifyPlaylistID = new ObjectMapper().readValue(jsonPlaylistOutputExample, SpotifyPlaylistID.class);
        assertThat(spotifyPlaylistID.getValue()).isEqualTo("3ihH414S6RcPZWychEVQTB");
    }
}
