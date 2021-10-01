import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Playlist;
import models.PlaylistID;
import models.TrackURI;
import spotifyapi.AddItemToPlaylistAPI;
import spotifyapi.CreateNewPlaylistAPI;
import spotifyapi.SearchItemAPI;

import java.io.IOException;
import java.util.ArrayList;


public class XMLToSpotifyConverter {
    ObjectMapper objectMapper = new ObjectMapper();

    public static void main (String[] args) throws IOException, InterruptedException {

        XMLToSpotifyConverter xmlToSpotifyConverter = new XMLToSpotifyConverter();
        String FILE_NAME = "/Users/rachelcope/Documents/ITunesXMLtoSpotifyPlaylist/src/main/resources/ProjectExample.xml";
        Playlist PLAYLIST = new Playlist("myFirstPlaylist", "this is hardcoded", false);
        String TOKEN = "BQCnIAITp2NhEnj_x2GZu22zi-afj923Px67LZ3fuNteXZZ-N6ZJq1BsrOpM1c9B4vQ4mO2jw5nlL1k6XSd9OOrSeZVFxASRmD77EhlQ6XhzJqjeZCGm0MG_4j-mPS9DBIq5M1PLgJNcV3RB-YB773y6McBtz86Xa5nPHKVnvN2zx0Fkuw";

        ArrayList<String> searchResponse = new SearchItemAPI().getSearchItemResponseList(FILE_NAME, TOKEN);


        ArrayList<TrackURI> trackURIs =
                xmlToSpotifyConverter.getListOfTracksFromSearchResponseList(searchResponse);
        String jsonTrackURIs = xmlToSpotifyConverter.serializeTrackURIs(trackURIs);


        String jsonPlaylist = xmlToSpotifyConverter.serializePlaylist(PLAYLIST);
        String playlistResponseBody = new CreateNewPlaylistAPI().createNewPlaylist(PLAYLIST, jsonPlaylist, TOKEN);
        PlaylistID playlistID =
                xmlToSpotifyConverter.getPlaylistURI(playlistResponseBody);


        System.out.print(new AddItemToPlaylistAPI().addItemToPlaylist(jsonTrackURIs, playlistID.id, TOKEN));

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
