package commands;

import client.BuildSpotifyPlaylistAPI;
import models.Playlist;
import picocli.CommandLine;
import java.io.IOException;

@CommandLine.Command(name = "import")
public class ImportCommand implements Runnable  {

    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    public String xmlFile;
    @CommandLine.Option(names = {"-pn", "--playlist"}, required = true)
    public String playlistName;
    @CommandLine.Option(names = {"-d", "--description"})
    public String playlistDescription;
    @CommandLine.Option(names = {"-p", "--public"})
    public boolean playlistIsPublic;

    @Override
    public void run() {
        try {
            this.createBuildSpotifyPlaylistAPI().addSpotifyTracksToPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BuildSpotifyPlaylistAPI createBuildSpotifyPlaylistAPI() throws IOException, InterruptedException {
        return new BuildSpotifyPlaylistAPI(getXmlFile(), this.createPlaylist());
    }

    Playlist createPlaylist() {
        return new Playlist (this.getPlaylistName(), this.getPlaylistDescription(), this.isPlaylistIsPublic());
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    public boolean isPlaylistIsPublic() {
        return playlistIsPublic;
    }
}
