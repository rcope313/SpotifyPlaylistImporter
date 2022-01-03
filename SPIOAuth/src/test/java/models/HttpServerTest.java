package models;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class HttpServerTest {

    HttpServer server;

    void initData() {
        server = new HttpServer();
    }

    @Test
    public void itGeneratesAuthUri() {
        this.initData();
        assertThat(server.buildRedirectUri()).isEqualTo("http://localhost:2000/authorize");
    }
}
