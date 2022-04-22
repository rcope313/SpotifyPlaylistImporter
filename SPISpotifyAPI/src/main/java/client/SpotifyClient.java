package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;
import org.assertj.core.util.VisibleForTesting;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String xmlFile;
    @VisibleForTesting
    final Playlist playlist;

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
