package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String xmlFile;
    private final Playlist playlist;

    public SpotifyClient(String xmlFile, Playlist playlist) {
        this.xmlFile = xmlFile;
        this.playlist = playlist;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
