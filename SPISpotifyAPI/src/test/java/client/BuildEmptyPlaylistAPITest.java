package client;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Playlist;
import models.SpotifyUser;
import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

public class BuildEmptyPlaylistAPITest {
    
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist, token;
    BuildEmptyPlaylistAPI buildEmptyOneSongPlaylist, buildEmptyFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;
    SpotifyUser user1;

    void initData() {
        token = "BQDHAWNMnnvLADiGh_6KMAib-O9qzEydUFpqUHXhizDLKWX8o-HOlGqeNChA1_wT_9YqzUONWTnyuBUhZtLlvrPfy2pey3K37gatXnO71MjnZc7fX-89GqBVaQqdV6a4HT240b3C10rGaVzfkv769coeu06UkdsZEmtKXgoaL7nBqhSZCX7twxcRAzN1d1KZBKplpxFn2JBQe5qsFRFWHMc";

        iTunesXMLFileOneSong = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        buildEmptyOneSongPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist, token);
        buildEmptyFullPlaylist = new BuildEmptyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist, token);

        user1 = new SpotifyUser("1287343652");
    }

    @Test
    //this won't pass if other tests pass
    public void itThrowsIllegalStateExceptionWithInvalidOrExpiredAccessToken() {
        this.initData();
        assertThatThrownBy(()
                -> buildEmptyFullPlaylist.buildSpotifyEmptyPlaylistAndSpotifyPlaylistID())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void itBuildsEmptyPlaylistAndSpotifyPlaylistID() throws IOException, InterruptedException {
        this.initData();
        assertThat(buildEmptyOneSongPlaylist
                .buildSpotifyEmptyPlaylistAndSpotifyPlaylistID()
                .getValue().length())
                .isEqualTo(22);

        assertThat(buildEmptyFullPlaylist
                .buildSpotifyEmptyPlaylistAndSpotifyPlaylistID()
                .getValue().length())
                .isEqualTo(22);
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
    public void itGetsCurrentUsersProfile() throws IOException, InterruptedException {
        this.initData();
        assertThat(buildEmptyOneSongPlaylist.getSpotifyUser()).usingRecursiveComparison().isEqualTo(user1);
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
