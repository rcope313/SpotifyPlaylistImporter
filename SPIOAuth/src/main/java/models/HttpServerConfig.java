package models;

final public class HttpServerConfig {
    private final String host;
    private final int port;
    private final String context;
    private final int timeout;

    public HttpServerConfig() {
        this.host = "localhost";
        this.port = 2000;
        this.context = "/authorize";
        this.timeout = 60;

    }
    public String buildRedirectUri() {
        return "http://" + getHost() + ":" + getPort() + getContext();
    }

    public String getHost() { return host; }

    public int getPort() {
        return port;
    }

    public String getContext() {
        return context;
    }

    public int getTimeout() {
        return timeout;
    }

}
