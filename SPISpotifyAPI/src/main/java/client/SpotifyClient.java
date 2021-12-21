package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQAnJeMdR04W9M_EdMu3swMh1be9fsoTE_2KpBlL8COQQqGCZTU0h7PtSNnFB2x-YV7KSYvMjRTbo5yU2_MpL9NJxF_pqCLWWFu4GOyoYA-QDG6e983hIWf615K4437Iskg3mkeu2ko2eaJjjAhuq7iPpffjyFKJsI8cMlcAW0H-VQh415o-wfN4NqEBy5ONmVloaLJ6lWXTuz7uci1X1gg";
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
