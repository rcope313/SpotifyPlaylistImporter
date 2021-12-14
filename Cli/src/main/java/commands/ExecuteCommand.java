package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Playlist;
import models.PlaylistID;
import models.TokenManager;
import models.TrackURI;
import picocli.CommandLine;
import spotifyapi.AddItemToPlaylistAPI;
import spotifyapi.CreateNewPlaylistAPI;
import spotifyapi.SearchItemAPI;
import xmlparser.ReadXmlSaxParser;

import java.io.IOException;
import java.util.ArrayList;

@CommandLine.Command(name = "execute")
public class ExecuteCommand implements Runnable, Command {

    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    public String xmlFile;
    @CommandLine.Option(names = {"-pn", "--playlist"}, required = true)
    public String playlistName;
    @CommandLine.Option(names = {"-d", "--description"})
    public String playlistDescription;
    @CommandLine.Option(names = {"-p", "--public"})
    public boolean playlistIsPublic;
    public ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run() {

//        tokenManager.getTempFile();

//        String jsonTrackUris = null;
//        try {
//            jsonTrackUris = getListOfJsonTrackUris();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            createNewPlaylistWithTracks(jsonTrackUris);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(CommandLine.Help.Ansi.ON.string("@|bold,fg(green) Success! |@"
//                        + playlistName
//                        + "@|bold,fg(green)  has been created. Check your spotify, and start listening!|@"));



    }

    // add exceptions
    private String getListOfJsonTrackUris() throws IOException {
        ArrayList<String> searchResponse = SearchItemAPI.getJsonTrackListFromItemSearch(ReadXmlSaxParser.parse(xmlFile), new TokenManager().getToken());
        ArrayList<TrackURI> trackURIs = getListOfTracksFromSearchResponseList(searchResponse);
        return serializeTrackURIs(trackURIs);

    }

    private void createNewPlaylistWithTracks(String jsonTrackURIs) throws IOException, InterruptedException {

        Playlist playlist = createPlaylist();

        String jsonPlaylist = serializePlaylist(playlist);
        String playlistResponseBody = CreateNewPlaylistAPI.createNewPlaylist(playlist, jsonPlaylist, new TokenManager().getToken());
        PlaylistID playlistID = getPlaylistURI(playlistResponseBody);

        AddItemToPlaylistAPI.addItemToPlaylist(jsonTrackURIs, playlistID.id, new TokenManager().getToken());

    }

    private Playlist createPlaylist () {
        String description = "";
        boolean isPublic = false;

        if (playlistIsPublic) {
            isPublic = true;
        }
        if (playlistDescription != null) {
            description = playlistDescription;
        }
        return new Playlist(playlistName, description, isPublic);
    }

    private String serializePlaylist(Playlist playlist) throws JsonProcessingException {
        return objectMapper.writeValueAsString(playlist);
    }

    private ArrayList<TrackURI> getListOfTracksFromSearchResponseList (ArrayList<String> searchResponseList) throws IOException {

        ArrayList<TrackURI> trackURIs = new ArrayList<>();

        for (String aJsonString : searchResponseList ) {
            trackURIs.add(objectMapper.readValue(aJsonString, TrackURI.class));

        }

        return trackURIs;
    }

    private PlaylistID getPlaylistURI (String playlistResponseBody) throws IOException {
        return objectMapper.readValue(playlistResponseBody, PlaylistID.class);
    }

    private String serializeTrackURIs (ArrayList<TrackURI> trackURIs) throws JsonProcessingException {

        ObjectNode rootNode = objectMapper.createObjectNode();
        ArrayNode arrayNode = rootNode.putArray("uris");

        trackURIs.forEach((aTrackURI) -> arrayNode.add(aTrackURI.uriName));

        String jsonString = objectMapper.writeValueAsString(rootNode);

        return jsonString;

    }

}
