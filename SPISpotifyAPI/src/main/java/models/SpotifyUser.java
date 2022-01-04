package models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

@JsonDeserialize(using = SpotifyUser.DeserializeUserProfileResultToSpotifyUser.class)
public class SpotifyUser {
    private final String id;

    public SpotifyUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    static class DeserializeUserProfileResultToSpotifyUser extends StdDeserializer<SpotifyUser> {

        public DeserializeUserProfileResultToSpotifyUser() {
            this(null);
        }

        public DeserializeUserProfileResultToSpotifyUser(Class<?> vc) {
            super(vc);
        }

        @Override
        public SpotifyUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String idValue = String.valueOf(node.get("id"));
            idValue = idValue.replaceAll("\"", "");
            return new SpotifyUser(idValue);
        }

    }
}
