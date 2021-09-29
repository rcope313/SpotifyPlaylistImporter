package config;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GetAccessAndRefreshTokensResponseConfig {

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


    public static GetAccessAndRefreshTokensResponseConfig deserializeJsonPostResponse (String jsonString) throws IOException {
        GetAccessAndRefreshTokensResponseConfig config = new ObjectMapper().readValue(jsonString, GetAccessAndRefreshTokensResponseConfig.class);

        return config;
    }


}
