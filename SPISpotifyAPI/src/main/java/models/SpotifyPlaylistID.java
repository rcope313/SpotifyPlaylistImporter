package models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

@JsonDeserialize(using = SpotifyPlaylistID.DeserializeSpotifyPlaylistToSpotifyPlaylistID.class)
public class SpotifyPlaylistID {
    private final String value;

    public SpotifyPlaylistID(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static class DeserializeSpotifyPlaylistToSpotifyPlaylistID extends StdDeserializer<SpotifyPlaylistID> {

        public DeserializeSpotifyPlaylistToSpotifyPlaylistID() {
            this(null);
        }

        public DeserializeSpotifyPlaylistToSpotifyPlaylistID(Class<?> vc) {
            super(vc);
        }

        @Override
        public SpotifyPlaylistID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String idValue = String.valueOf(node.get("id"));
            idValue = idValue.replaceAll("\"","");
            return new SpotifyPlaylistID(idValue);
        }
    }
}
