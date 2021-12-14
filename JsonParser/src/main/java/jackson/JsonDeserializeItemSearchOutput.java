package jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import model.TrackURI;

import java.io.IOException;

public class JsonDeserializeItemSearchOutput extends StdDeserializer<TrackURI> {

    public JsonDeserializeItemSearchOutput() {
        this(null);
    }

    public JsonDeserializeItemSearchOutput(Class<?> vc) {
        super(vc);
    }

    @Override
    public TrackURI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemsNode = rootNode.get("tracks").get("items");
        return new TrackURI(itemsNode.get(0).get("uri").asText());

    }
}
