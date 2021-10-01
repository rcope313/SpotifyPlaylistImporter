package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Playlist;
import models.PlaylistID;
import models.TrackURI;
import picocli.CommandLine;
import spotifyapi.AddItemToPlaylistAPI;
import spotifyapi.CreateNewPlaylistAPI;
import spotifyapi.SearchItemAPI;

import java.io.IOException;
import java.util.ArrayList;

@CommandLine.Command(name = "execute")
public class ExecuteCommand implements Runnable {

    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    public String xmlFile;
    @CommandLine.Option(names = {"-pn", "--playlist"}, required = true)
    public String playlistName;
    @CommandLine.Option(names = {"-d", "--description"})
    public String playlistDescription;
    @CommandLine.Option(names = {"-p", "--public"})
    public boolean playlistIsPublic;
    public ObjectMapper objectMapper = new ObjectMapper();
    public String token = "BQD_2ag_mmvcrmOu1t8XqGlNYeFrXMFFM43kIoysA7GhgxwSVJVCKrYP0vTAhxqIuibwEF_EagKeVsuIr3pUkyqHjWkmlTluWQTOmsFQEBGTMP5MZUJeKwBHNvLMtORSuXuPSkfpkh6HbZv5vzlropm9lYoQVPVOoUDXvhrq2Pa9Pn7g2A";


    @Override
    public void run() {

        String jsonTrackUris = null;
        try {
            jsonTrackUris = getListOfJsonTrackUris();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            createNewPlaylistWithTracks(jsonTrackUris);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    // add exceptions
    private String getListOfJsonTrackUris() throws IOException {
        ArrayList<String> searchResponse = SearchItemAPI.getSearchItemResponseList(xmlFile, token);
        ArrayList<TrackURI> trackURIs = getListOfTracksFromSearchResponseList(searchResponse);
        return serializeTrackURIs(trackURIs);

    }

    private void createNewPlaylistWithTracks(String jsonTrackURIs) throws IOException, InterruptedException {

        Playlist playlist = createPlaylist();

        String jsonPlaylist = serializePlaylist(playlist);
        String playlistResponseBody = CreateNewPlaylistAPI.createNewPlaylist(playlist, jsonPlaylist, token);
        PlaylistID playlistID = getPlaylistURI(playlistResponseBody);

        AddItemToPlaylistAPI.addItemToPlaylist(jsonTrackURIs, playlistID.id, token);

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
