package models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

@JsonDeserialize(using = SpotifyTrackURI.DeserializeSpotifySearchItemResultToSpotifyTrackURI.class)
public class SpotifyTrackURI {
    private final String uriName;

    public SpotifyTrackURI(String uriName) {
        this.uriName = uriName;
    }

    public String getUriName() {
        return uriName;
    }

   static class DeserializeSpotifySearchItemResultToSpotifyTrackURI extends StdDeserializer<SpotifyTrackURI> {

        public DeserializeSpotifySearchItemResultToSpotifyTrackURI() {
            this(null);
        }

        public DeserializeSpotifySearchItemResultToSpotifyTrackURI(Class<?> vc) {
            super(vc);
        }

        @Override
        public SpotifyTrackURI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
            JsonNode itemsNode = rootNode.get("tracks").get("items");
            return new SpotifyTrackURI(itemsNode.get(0).get("uri").asText());

        }
    }
}
