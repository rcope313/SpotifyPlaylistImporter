package config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;

@Introspected
@ConfigurationProperties("stackoverflow.auth.server")
final public class HttpServerConfig {
    private String host;
    private int port;
    private String context;
    private String redirectUri;
    private int timeout;


    public String getRedirectUri() {
        return redirectUri;
    }

    public String getHost() { return host; }

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
