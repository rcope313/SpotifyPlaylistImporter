package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

public class SpotifyPlaylistIDTest {
    String jsonPlaylistOutputExample;

    @Before
    public void initData() {
        jsonPlaylistOutputExample = "{\n" +
                "  \"collaborative\": false,\n" +
                "  \"description\": \"New playlist description\",\n" +
                "  \"external_urls\": {\n" +
                "    \"spotify\": \"https://open.spotify.com/playlist/5nVVb5mbCVRTbHLpPrUghy\"\n" +
                "  },\n" +
                "  \"followers\": {\n" +
                "    \"href\": null,\n" +
                "    \"total\": 0\n" +
                "  },\n" +
                "  \"href\": \"https://api.spotify.com/v1/playlists/5nVVb5mbCVRTbHLpPrUghy\",\n" +
                "  \"id\": \"5nVVb5mbCVRTbHLpPrUghy\",\n" +
                "  \"images\": [],\n" +
                "  \"name\": \"One Song Playlist\",\n" +
                "  \"owner\": {\n" +
                "    \"display_name\": \"Rachel Cope\",\n" +
                "    \"external_urls\": {\n" +
                "      \"spotify\": \"https://open.spotify.com/user/1287343652\"\n" +
                "    },\n" +
                "    \"href\": \"https://api.spotify.com/v1/users/1287343652\",\n" +
                "    \"id\": \"1287343652\",\n" +
                "    \"type\": \"user\",\n" +
                "    \"uri\": \"spotify:user:1287343652\"\n" +
                "  },\n" +
                "  \"primary_color\": null,\n" +
                "  \"public\": false,\n" +
                "  \"snapshot_id\": \"MSxlM2FhZGYzZTM4OWQxNWIwNTE2ODM5YzhjMmIxNDk4NmMxMjczYTY1\",\n" +
                "  \"tracks\": {\n" +
                "    \"href\": \"https://api.spotify.com/v1/playlists/5nVVb5mbCVRTbHLpPrUghy/tracks\",\n" +
                "    \"items\": [],\n" +
                "    \"limit\": 100,\n" +
                "    \"next\": null,\n" +
                "    \"offset\": 0,\n" +
                "    \"previous\": null,\n" +
                "    \"total\": 0\n" +
                "  },\n" +
                "  \"type\": \"playlist\",\n" +
                "  \"uri\": \"spotify:playlist:5nVVb5mbCVRTbHLpPrUghy\"\n" +
                "}";
    }

    @Test
    public void itDeserializesJsonValueIntoPlayListIDBean() throws IOException {
        SpotifyPlaylistID spotifyPlaylistID = new ObjectMapper().readValue(jsonPlaylistOutputExample, SpotifyPlaylistID.class);
        assertThat(spotifyPlaylistID.getValue()).isEqualTo("5nVVb5mbCVRTbHLpPrUghy");
    }
}
