package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQAIrJFrePtQGsm9kxv8p8wBs11pZN7fAoIPD8Rl4KKi1l4uiQlSmKPZpIbCvkH_LYs-yEnRaxWBmKgM5XX_yCSihSlcFMzQRx_O3D-w-Ioge0NUixUlN0cOgQXJ6YGPm80Y3TkmpUcKFGQE8SjIXn8Z";
    private final String xmlFile;
    private final Playlist playlist;

    public SpotifyClient(String xmlFile, Playlist playlist) {
        this.xmlFile = xmlFile;
        this.playlist = playlist;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getToken() {
        return token;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
