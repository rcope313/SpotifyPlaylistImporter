package tester;

import auth.HttpServerAuthorization;
import config.ServerConfiguration;

import java.io.IOException;

public class TestServerConfiguration {

    ServerConfiguration server1;

    void initData() {
        server1 = new ServerConfiguration("localhost", 0, "/authorize", 60);

    }





}
