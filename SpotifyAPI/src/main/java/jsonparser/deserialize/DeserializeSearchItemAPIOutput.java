package jsonparser.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import models.TrackURI;

import java.io.IOException;

public class DeserializeSearchItemAPIOutput extends StdDeserializer<TrackURI> {

    //i don't know if i need either of these constructors
    public DeserializeSearchItemAPIOutput() {
        this(null);
    }

    public DeserializeSearchItemAPIOutput(Class<?> vc) {
        super(vc);
    }

    @Override
    public TrackURI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode rootNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode itemsNode = rootNode.get("tracks").get("items");
        return new TrackURI(itemsNode.get(0).get("uri").asText());

    }
}
