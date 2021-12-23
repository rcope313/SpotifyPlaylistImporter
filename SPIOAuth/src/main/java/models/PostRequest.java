package models;

public class PostRequest {
    private final String grantType;
    private final String code;
    private final String redirectUri;
    private final String clientId;
    private final String clientSecret;

    public PostRequest(String code) {
        this.grantType = "authorization_code";
        this.code = code;
        this.redirectUri = "http%3A%2F%2Flocalhost%3A2000%2Fauthorize";
        this.clientId = "58f5ea655fc64389ae8f53047aa14201";
        this.clientSecret = "fe103f0d879048da899baea87e5402a9";
    }

    public String jsonBodyParameter () {
        return "grant_type=" + grantType
                + "&redirect_uri=" + redirectUri
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&code=" + code;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getCode() {
        return code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
