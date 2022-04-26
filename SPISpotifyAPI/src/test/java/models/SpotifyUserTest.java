package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class SpotifyUserTest {
    String jsonResponse1;
    SpotifyUser user1;

    @Before
    public void initData() {
        jsonResponse1 = "{\n" +
                "  \"country\": \"string\",\n" +
                "  \"display_name\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"explicit_content\": {\n" +
                "    \"filter_enabled\": true,\n" +
                "    \"filter_locked\": true\n" +
                "  },\n" +
                "  \"external_urls\": {\n" +
                "    \"spotify\": \"string\"\n" +
                "  },\n" +
                "  \"followers\": {\n" +
                "    \"href\": \"string\",\n" +
                "    \"total\": 0\n" +
                "  },\n" +
                "  \"href\": \"string\",\n" +
                "  \"id\": \"abc123\",\n" +
                "  \"images\": [\n" +
                "    {\n" +
                "      \"url\": \"https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228\\n\",\n" +
                "      \"height\": 300,\n" +
                "      \"width\": 300\n" +
                "    }\n" +
                "  ],\n" +
                "  \"product\": \"string\",\n" +
                "  \"type\": \"string\",\n" +
                "  \"uri\": \"string\"\n" +
                "}";
        user1 = new SpotifyUser("abc123");
    }

    @Test
    public void itDeserializesJsonValueIntoPlayListIDBean() throws IOException {
        SpotifyUser instantiatedUser = new ObjectMapper().readValue(jsonResponse1, SpotifyUser.class);
        assertThat(instantiatedUser.getId()).isEqualTo(user1.getId());
    }
}
