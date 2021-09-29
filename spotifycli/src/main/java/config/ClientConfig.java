package config;

import io.micronaut.http.uri.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Random;

final public class ClientConfig {
    private String url;
    private String clientId;
    private String responseType;
    private String scope;

    public ClientConfig(String url, String clientId, String responseType, String scope) {
        this.url = url;
        this.clientId = clientId;
        this.responseType = responseType;
        this.scope = scope;

    }

    public URI generateAuthUri(String redirectUri) throws IOException, InterruptedException {

        URI authURI = UriBuilder.of(url)
                .queryParam("client_id", clientId)
                .queryParam("response_type", responseType)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("scope", scope)
                .queryParam("state", generateRandomAlphaNumericString())
                .build();

        return authURI;
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
