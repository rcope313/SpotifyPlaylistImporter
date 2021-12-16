package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQATBXqgph4rSSRf1D_M7juH5HhKs3IZcdz---GNnPIr-jxzXc0HEHd3e76t1yhZcrXlnaaPBoxYVhlccpzQXyq-qy0XYiK4EWGgnbOQ0z7KDC7fq7lT5e6qR__-H9KdyDU0MHd4xsXEmLpMXUMT44HuP7ZasUcUyh4wCFGfFUzLRGYsPfmFkOu01MDx-6WmSQrMtkuEgNTC0nH1R1Nqx-8";
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
