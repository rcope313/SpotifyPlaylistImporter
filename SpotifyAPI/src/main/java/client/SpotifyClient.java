package client;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "abc";

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getToken() {
        return token;
    }
}
