package config;

final public class ServerConfiguration {
    private String host;
    private int port;
    private String context;
    private int timeout;

    public ServerConfiguration (String host, int port, String context, int timeout) {
        this.host = host;
        this.port = port;
        this.context = context;
        this.timeout = timeout;
    }

    public String getRedirectUri() {
        return "http://" + host + ":" + port + context;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
