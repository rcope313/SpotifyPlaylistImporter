package auth;

import io.micronaut.http.uri.UriBuilder;
import java.net.URI;
import java.util.Random;

public class RequestUserAuth {
    private final String url;
    private final String clientId;
    private final String responseType;
    private final String scope;
    private final String redirectUri;

    public RequestUserAuth() {
        this.url = "https://accounts.spotify.com/authorize";
        this.clientId = "58f5ea655fc64389ae8f53047aa14201";
        this.responseType = "code";
        this.scope = "playlist-modify-public playlist-modify-private";
        this.redirectUri = "http://localhost:2000/authorize";
    }

    public URI generateAuthUri() {
        return UriBuilder.of(url)
                .queryParam("client_id", clientId)
                .queryParam("response_type", responseType)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .queryParam("state", generateRandomAlphaNumericString())
                .build();
    }

    private static String generateRandomAlphaNumericString () {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 11;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
