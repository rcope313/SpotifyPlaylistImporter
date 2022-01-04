package models;

import org.junit.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.*;

public class PostResponseTest {

    PostResponse pr0, pr1;
    String jsonString1;

    void initData() {
        pr0 = new PostResponse("", "Bearer", 3600, "", "playlist-modify-private playlist-modify-public");
        pr1 = new PostResponse("abc123", "Bearer", 3600, "345def", "playlist-modify-private playlist-modify-public");

        jsonString1 = "{\"access_token\":\"abc123\"," +
                "\"token_type\":\"Bearer\"," +
                "\"expires_in\":3600," +
                "\"refresh_token\":\"345def\"," +
                "\"scope\":\"playlist-modify-private playlist-modify-public\"}";
    }

    @Test
    public void itDeserializesPostResponseJsonString() throws IOException {
        this.initData();
        assertThat(PostResponse.deserializeJsonPostResponse(jsonString1)).usingRecursiveComparison().isEqualTo(pr1);
    }

}
