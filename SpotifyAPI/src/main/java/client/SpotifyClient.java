package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQDOUr7LUDyLaONdo1lj8XwzNSCLfdKqIeAi1tovcOqpzGDRh7COvZgsWUyIBpjtmmsUedpQLEqojfocKd72B4-9XysOtjjBwziueotY9lbbK0Ff-v1Nwj0tSK8ksscT57cXQuve9OdI09dNuCihqn2YkLtqvnoZRjyCKXJs-xlOFt39UsskHaeOy7yQTxhOdIMQYrqzIHzUrGlEOJEVQEY";
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
