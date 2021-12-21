package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQB-L0q4SM-MIe9Yx8ylnmLsF6qPMh6n4ZsnnGvLUSg_Qp7jHSKgMgJNx-FpQsjKk5lxm1-mowDseXi7ZUTuyfSi-40BBR6P1sqEkRS4FJV7FCtNQQuTdROCsTuMqjFD8Uf3kG8dnH6LSF05c_f08oBpBxnBkEziMXE7hNKCv2xMPK_LslEjl3QpkikPUGl8qKsyO0j3T3nDzbD7JKL3zHc";
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
