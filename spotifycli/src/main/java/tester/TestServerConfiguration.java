package tester;

import config.HttpServerConfig;

public class TestServerConfiguration {

    HttpServerConfig server1;

    void initData() {
        server1 = new HttpServerConfig("localhost", 0, "/authorize", 60);

    }





}
