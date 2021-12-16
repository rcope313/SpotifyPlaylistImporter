package client;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "abc";
    private final String xmlFile;

    public SpotifyClient(String xmlFile) {
        this.xmlFile = xmlFile;
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
}
