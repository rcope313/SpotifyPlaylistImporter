package commands;

import client.BuildSpotifyPlaylistAPI;
import models.Playlist;
import org.junit.Test;
import picocli.CommandLine;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.assertj.core.api.Assertions.*;

public class ImportCommandTest {

    int exitCode1, exitCode2;
    ImportCommand app1, app2;
    CommandLine cmd1, cmd2;
    StringWriter sw1, sw2;
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;
    BuildSpotifyPlaylistAPI buildSpotifyPlaylistAPI1, buildSpotifyPlaylistAPI2;

    void initData() throws IOException, InterruptedException {
        iTunesXMLFileOneSong = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileOneSong.xml";
        oneSongPlaylist = new Playlist("One Song Playlist", null, false);
        buildSpotifyPlaylistAPI1 = new BuildSpotifyPlaylistAPI(iTunesXMLFileOneSong, oneSongPlaylist);

        iTunesXMLFileFullPlaylist = "/Users/rachelcope/Documents/SpotifyPlaylistImporter/SPIITunesXMLParser/src/main/resources/ITunesXMLFileFullPlaylist.xml";
        fullPlaylist = new Playlist("Full Playlist", "Emo mania", true);
        buildSpotifyPlaylistAPI2 = new BuildSpotifyPlaylistAPI(iTunesXMLFileFullPlaylist, fullPlaylist);

        app1 = new ImportCommand();
        cmd1 = new CommandLine(app1);
        sw1 = new StringWriter();
        cmd1.setOut(new PrintWriter(sw1));
        exitCode1 = cmd1.execute("--file", iTunesXMLFileOneSong, "--playlist", "One Song Playlist");

        app2 = new ImportCommand();
        cmd2 = new CommandLine(app2);
        sw2 = new StringWriter();
        cmd2.setOut(new PrintWriter(sw2));
        exitCode2 = cmd2.execute("--file", iTunesXMLFileFullPlaylist, "--playlist", "Full Playlist", "-d", "Emo mania", "-p");
    }

    @Test
    public void itCreatesAnInstantiatedPlaylist() throws IOException, InterruptedException {
        initData();
        assertThat(app1.createPlaylist().getDescription()).isEqualTo(null);
        assertThat(app1.createPlaylist().getName()).isEqualTo("One Song Playlist");
        assertThat(app1.createPlaylist().isPublic()).isFalse();

        assertThat(app2.createPlaylist().getDescription()).isEqualTo("Emo mania");
        assertThat(app2.createPlaylist().getName()).isEqualTo("Full Playlist");
        assertThat(app2.createPlaylist().isPublic()).isTrue();
    }

    @Test
    public void itCreatesAnInstantiatedBuildSpotifyPlaylistAPI() throws IOException, InterruptedException {
        initData();
        assertThat(app1.createBuildSpotifyPlaylistAPI().getXmlFile())
                .isEqualTo(iTunesXMLFileOneSong);
        assertThat(app2.createBuildSpotifyPlaylistAPI().getXmlFile())
                .isEqualTo(iTunesXMLFileFullPlaylist);
    }
}
