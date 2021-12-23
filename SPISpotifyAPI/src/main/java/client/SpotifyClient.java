package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQCPg8gQI-YNHHS1f8tHJ9DdD9Xnn5vzgFiHXvm4g23gw2jDq3BEfhz_J2xKfrDwn-woQ150BjvtMFwutN5ZgeGaCOBslSiNH2CzTb5GT6hUSVGh6mbAua-tM-28b_DtgnxN3JTNvXTrgT8Y5NefOivrt8aUMOWe6sNcw8G9J4f_W_zqj_kKZ0W8ZZyMCic1YuKEksOIOHPliD-h8hioOSo";
    private final String xmlFile;
    private final Playlist playlist;

    public SpotifyClient(String xmlFile, Playlist playlist) {
        this.xmlFile = xmlFile;
        this.playlist = playlist;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public String getToken() {
        return token;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
