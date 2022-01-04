package commands;

import auth.HttpServerAuth;
import auth.RequestUserAuth;
import client.BuildSpotifyPlaylistAPI;
import models.Playlist;
import models.PostResponse;
import picocli.CommandLine;

@CommandLine.Command(name = "execute")
public class ExecuteCommand implements Runnable {
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;
    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    public String xmlFile;
    @CommandLine.Option(names = {"-pn", "--playlist"}, required = true)
    public String playlistName = "name";
    @CommandLine.Option(names = {"-d", "--description"})
    public String playlistDescription;
    @CommandLine.Option(names = {"-p", "--public"})
    public boolean playlistIsPublic;
    private final RequestUserAuth requestUserAuth = new RequestUserAuth();
    private final HttpServerAuth httpServerAuth = new HttpServerAuth();

    public static void main (String[] args) {
        ExecuteCommand command = new ExecuteCommand();
        command.run();
    }

    @Override
    public void run() {
        // ** use to create tests
        // spec.commandLine().getOut().println(this);
        try {
            displayAuthUrl();
            createBuildSpotifyPlaylistAPI().addSpotifyTracksToPlaylist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BuildSpotifyPlaylistAPI createBuildSpotifyPlaylistAPI() {
        return new BuildSpotifyPlaylistAPI(getXmlFile(), this.createPlaylist(), handleRedirectUriAndReceiveAccessToken().getAccessToken());
    }

    Playlist createPlaylist() {
        return new Playlist (this.getPlaylistName(), this.getPlaylistDescription(), this.isPlaylistIsPublic());
    }

    private PostResponse handleRedirectUriAndReceiveAccessToken() {
        return getHttpServerAuth().start();
    }

    private void displayAuthUrl() {
        System.out.println("Click on the link to start authentication:");
        System.out.println(getRequestUserAuth().generateAuthUri());
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

    public RequestUserAuth getRequestUserAuth() {
        return requestUserAuth;
    }

    public HttpServerAuth getHttpServerAuth() {
        return httpServerAuth;
    }
}
