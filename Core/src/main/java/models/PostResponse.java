package models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class PostResponse {

    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("expires_in")
    public Integer expiresIn;
    @JsonProperty("refresh_token")
    public String refreshToken;
    @JsonProperty("scope")
    public String scope;


    public static PostResponse deserializeJsonPostResponse (String jsonString) throws IOException {
        PostResponse config = new ObjectMapper().readValue(jsonString, PostResponse.class);
        return config;
    }



}
