package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class BuildEmptyPlaylistAPITest {
    
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist, token, createOneSongPlaylistGetResponse;
    BuildEmptyPlaylistAPI buildEmptyOneSongPlaylist, buildEmptyFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;
    SpotifyUser user1;

    @Before
    public void initData() {
        token = "BQDHAWNMnnvLADiGh_6KMAib-O9qzEydUFpqUHXhizDLKWX8o-HOlGqeNChA1_wT_9YqzUONWTnyuBUhZtLlvrPfy2pey3K37gatXnO71MjnZc7fX-89GqBVaQqdV6a4HT240b3C10rGaVzfkv769coeu06UkdsZEmtKXgoaL7nBqhSZCX7twxcRAzN1d1KZBKplpxFn2JBQe5qsFRFWHMc";

        iTunesXMLFileOneSong = "src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "src/main/resources/ITunesXMLFileFullPlaylist.xml";

        createOneSongPlaylistGetResponse = "{\n" +
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

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        buildEmptyOneSongPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist, token);
        buildEmptyFullPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist, token);

        user1 = new SpotifyUser("1287343652");
    }

    @Test
    public void itBuildsEmptyPlaylistAndSpotifyPlaylistID() throws IOException, InterruptedException {
        BuildEmptyPlaylistAPI api = spy(buildEmptyOneSongPlaylist);
        when(api.serializePlaylistToJsonString()).thenReturn("");

        Mockito.doReturn("jsonString").when(api).serializePlaylistToJsonString();
        Mockito.doReturn(createOneSongPlaylistGetResponse).when(api).createNewPlaylist("jsonString");

        assertThat(api
                .buildSpotifyEmptyPlaylistAndSpotifyPlaylistID().getValue())
                .isEqualTo("5nVVb5mbCVRTbHLpPrUghy");
    }

    @Test
    public void itSerializesJsonStringToPlaylist() throws JsonProcessingException {
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
