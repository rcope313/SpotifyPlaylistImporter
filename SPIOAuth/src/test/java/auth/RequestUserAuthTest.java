package auth;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class RequestUserAuthTest {

    RequestUserAuth request;

    void initData() {
        request = new RequestUserAuth();
    }

    @Test
    public void itGeneratesAuthUri() {
        this.initData();
        assertThat(request.generateAuthUri().toString().substring(0,215)).isEqualTo(
                "https://accounts.spotify.com/authorize?" +
                "client_id=58f5ea655fc64389ae8f53047aa14201" +
                "&response_type=code" +
                "&redirect_uri=http%3A%2F%2Flocalhost%3A2000%2Fauthorize" +
                "&scope=playlist-modify-public+playlist-modify-private" +
                "&state=");
    }
}
