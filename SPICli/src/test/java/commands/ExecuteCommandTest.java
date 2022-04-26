package commands;

import models.Playlist;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import picocli.CommandLine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class ExecuteCommandTest {
    int exitCode1, exitCode2;
    ExecuteCommand mockApp1, mockApp2, mockApp3;
    CommandLine cmd1, cmd2, cmd3;
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist;
    Playlist oneSongPlaylist, fullPlaylist;

    @Before
    public void initData() {
        iTunesXMLFileOneSong = "ITunesXMLFileOneSong.xml";
        oneSongPlaylist = new Playlist("One Song Playlist", null, false);

        iTunesXMLFileFullPlaylist = "ITunesXMLFileFullPlaylist.xml";
        fullPlaylist = new Playlist("Full Playlist", "Emo mania", true);

        mockApp1 = mock(ExecuteCommand.class);
        cmd1 = new CommandLine(mockApp1);
        exitCode1 = cmd1.execute( iTunesXMLFileOneSong, "One Song Playlist");

        mockApp2 = mock(ExecuteCommand.class);
        cmd2 = new CommandLine(mockApp2);
        exitCode2 = cmd2.execute( iTunesXMLFileFullPlaylist, "Full Playlist", "-d", "Emo Mania", "-p");

        mockApp3 = mock(ExecuteCommand.class);
        cmd3 = new CommandLine(mockApp3);
        exitCode1 = cmd3.execute("-pd",  "Emo Mania", iTunesXMLFileOneSong, "One Song Playlist");
    }

    @Ignore("Test passes; however, problem with mvn install due to mockito bug 'Illegal reflective access by org.mockito.cglib.core.ReflectUtil$2'")
    @Test
    public void itCreatesAnInstantiatedPlaylist() {
        assertThat(mockApp1.xmlFile).isEqualTo(iTunesXMLFileOneSong);
        assertThat(mockApp1.playlistName).isEqualTo("One Song Playlist");
        assertThat(mockApp1.playlistDescription).isNullOrEmpty();
        assertThat(mockApp1.isPublic).isFalse();

        assertThat(mockApp2.xmlFile).isEqualTo(iTunesXMLFileFullPlaylist);
        assertThat(mockApp2.playlistName).isEqualTo("Full Playlist");
        assertThat(mockApp2.playlistDescription).isEqualTo("Emo Mania");
        assertThat(mockApp2.isPublic).isTrue();

        assertThat(mockApp3.xmlFile).isEqualTo(iTunesXMLFileOneSong);
        assertThat(mockApp3.playlistName).isEqualTo("One Song Playlist");
        assertThat(mockApp3.playlistDescription).isEqualTo("Emo Mania");
        assertThat(mockApp3.isPublic).isTrue();
    }
}
