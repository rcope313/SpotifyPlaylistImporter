package jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import models.PlaylistID;

import java.io.IOException;

public class JsonDeserializePlaylist extends StdDeserializer<PlaylistID> {

    public JsonDeserializePlaylist() {
        this(null);
    }

    public JsonDeserializePlaylist(Class<?> vc) {
        super(vc);
    }

    @Override
    public PlaylistID deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String playlistID = String.valueOf(node.get("id"));

        playlistID = playlistID.replaceAll("\"","");
        return new PlaylistID(playlistID);

    }
}
