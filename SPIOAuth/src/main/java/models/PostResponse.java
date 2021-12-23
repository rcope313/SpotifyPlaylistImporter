package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class PostResponse {
    @JsonProperty("access_token")
    private final String accessToken;
    @JsonProperty("token_type")
    private final String tokenType;
    @JsonProperty("expires_in")
    private final Integer expiresIn;
    @JsonProperty("refresh_token")
    private final String refreshToken;
    @JsonProperty("scope")
    private final String scope;

    public PostResponse(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public static PostResponse deserializeJsonPostResponse (String jsonString) throws IOException {
        PostResponse config = new ObjectMapper().readValue(jsonString, PostResponse.class);
        return config;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }
}
