package client;

import models.Playlist;
import models.SpotifyTrackURI;
import models.Track;
import org.junit.Test;
import xmlparser.ITunesXMLFileParser;
import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class SearchItemAPITest {

    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist;
    ArrayList<Track> oneSongTrackList, fullPlaylistTrackList;
    SearchItemAPI searchItemAPIOneSong, searchItemAPIFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;

    void initData() {
        iTunesXMLFileOneSong = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";

        oneSongTrackList = ITunesXMLFileParser.parse(iTunesXMLFileOneSong);
        fullPlaylistTrackList = ITunesXMLFileParser.parse(iTunesXMLFileFullPlaylist);

        oneSongPlaylist = new Playlist("One SongPlaylist", "A playlist of one song", true);
        fullPlaylist = new Playlist("Full Playlist", "a playlist of 20 songs", false);

        searchItemAPIOneSong = new SearchItemAPI(iTunesXMLFileOneSong, oneSongPlaylist);
        searchItemAPIFullPlaylist = new SearchItemAPI(iTunesXMLFileFullPlaylist, fullPlaylist);
    }

    @Test
    public void itThrowsIllegalStateExceptionWithInvalidOrExpiredAccessToken() {
        this.initData();
        assertThatThrownBy(()
                -> searchItemAPIOneSong.buildJsonStringOfListOfSpotifyTrackURI())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void itBuildAJsonString() throws IOException {
        this.initData();
        assertThat(searchItemAPIOneSong.buildJsonStringOfListOfSpotifyTrackURI().length()).isEqualTo(49);
        assertThat(searchItemAPIFullPlaylist.buildJsonStringOfListOfSpotifyTrackURI().length()).isEqualTo(790);
    }

    @Test
    public void itBuildsAJsonStringFromListOfSpotifyTrackURI() throws IOException {
        this.initData();
        ArrayList<String> jsonStringList1 = searchItemAPIOneSong.buildListOfJsonStringByItemSearchAPI(oneSongTrackList);
        ArrayList<SpotifyTrackURI> trackURIList1 = searchItemAPIOneSong.buildListOfSpotifyTrackURIFromListOfJsonString(jsonStringList1);
        assertThat(searchItemAPIOneSong.buildJsonStringOfListOfSpotifyTrackURI(trackURIList1).length()).isEqualTo(49);

        ArrayList<String> jsonStringList2 = searchItemAPIFullPlaylist.buildListOfJsonStringByItemSearchAPI(fullPlaylistTrackList);
        ArrayList<SpotifyTrackURI> trackURIList2 = searchItemAPIFullPlaylist.buildListOfSpotifyTrackURIFromListOfJsonString(jsonStringList2);
        assertThat(searchItemAPIFullPlaylist.buildJsonStringOfListOfSpotifyTrackURI(trackURIList2).length()).isEqualTo(790);
    }

    @Test
    public void itBuildsListOfSpotifyTrackURIFromListOfJsonString() throws IOException {
        this.initData();
        ArrayList<String> jsonStringList1 = searchItemAPIOneSong.buildListOfJsonStringByItemSearchAPI(oneSongTrackList);
        ArrayList<SpotifyTrackURI> trackURIList1 = searchItemAPIOneSong.buildListOfSpotifyTrackURIFromListOfJsonString(jsonStringList1);
        assertThat(trackURIList1)
                .extracting(SpotifyTrackURI::getUriName).
                contains("spotify:track:3PYdxIDuBIuJSDGwfptFx4");

        ArrayList<String> jsonStringList2 = searchItemAPIFullPlaylist.buildListOfJsonStringByItemSearchAPI(fullPlaylistTrackList);
        ArrayList<SpotifyTrackURI> trackURIList2 = searchItemAPIFullPlaylist.buildListOfSpotifyTrackURIFromListOfJsonString(jsonStringList2);
        assertThat(trackURIList2)
                .extracting(SpotifyTrackURI::getUriName)
                .contains(
                        "spotify:track:4RCWB3V8V0dignt99LZ8vH",
                        "spotify:track:3skn2lauGk7Dx6bVIt5DVj",
                        "spotify:track:4bPQs0PHn4xbipzdPfn6du",
                        "spotify:track:2Guz1b911CbpG8L92cnglI");
    }

    @Test
    public void itReceivesListOfNonEmptySpotifyTrackJsonStringWithHTTPGet() {
        this.initData();
        ArrayList<String> instantiatedJsonStringList1 = searchItemAPIOneSong.buildListOfJsonStringByItemSearchAPI(oneSongTrackList);
        assertThat(instantiatedJsonStringList1).size().isEqualTo(1);
        ArrayList<String> instantiatedJsonStringList2 = searchItemAPIFullPlaylist.buildListOfJsonStringByItemSearchAPI(fullPlaylistTrackList);
        assertThat(instantiatedJsonStringList2).size().isEqualTo(20);
    }

    @Test
    public void itReceivesNonEmptySpotifyTrackJsonStringWithHTTPGet() throws IOException, InterruptedException {
        this.initData();
        String instantiatedJsonString1 = searchItemAPIOneSong.receiveAJsonStringByItemSearchAPI(oneSongTrackList.get(0));
        assertThat(instantiatedJsonString1).isNotEmpty();
        String instantiatedJsonString2 = searchItemAPIFullPlaylist.receiveAJsonStringByItemSearchAPI(fullPlaylistTrackList.get(0));
        assertThat(instantiatedJsonString2).isNotEmpty();
    }
}
