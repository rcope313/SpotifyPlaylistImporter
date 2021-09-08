import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Playlist;
import models.PlaylistID;
import models.TrackURI;
import createandmodplaylist.AddItemToPlaylist;
import createandmodplaylist.CreateNewPlaylist;
import createandmodplaylist.SearchItem;

import java.io.IOException;
import java.util.ArrayList;


public class XMLToSpotifyConverter {
    ObjectMapper objectMapper = new ObjectMapper();

    public static void main (String[] args) throws IOException, InterruptedException {

        XMLToSpotifyConverter xmlToSpotifyConverter = new XMLToSpotifyConverter();
        String FILE_NAME = "/Users/rachelcope/Documents/ITunesXMLtoSpotifyPlaylist/src/main/resources/ProjectExample.xml";
        Playlist PLAYLIST = new Playlist("myFirstPlaylist", "this is hardcoded", false);


        ArrayList<String> searchResponse = new SearchItem().getSearchItemResponseList(FILE_NAME);


        ArrayList<TrackURI> trackURIs =
                xmlToSpotifyConverter.getListOfTracksFromSearchResponseList(searchResponse);
        String jsonTrackURIs = xmlToSpotifyConverter.serializeTrackURIs(trackURIs);


        String jsonPlaylist = xmlToSpotifyConverter.serializePlaylist(PLAYLIST);
        String playlistResponseBody = new CreateNewPlaylist().createNewPlaylist(PLAYLIST, jsonPlaylist);
        PlaylistID playlistID =
                xmlToSpotifyConverter.getPlaylistURI(playlistResponseBody);


        System.out.print(new AddItemToPlaylist().addItemToPlaylist(jsonTrackURIs, playlistID.id));

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
