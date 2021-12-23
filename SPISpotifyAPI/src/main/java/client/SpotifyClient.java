package client;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Playlist;

public abstract class SpotifyClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String token = "BQBUjFGZXZCUDegYcRS33o-Ljv_D0HulgLGLp3akhxeqTHn3Hpf6Oco5aOOuYT3-b8b5OTO9s0wFwRChRSo_T2Sg_3SHxsGqBBiiYHgkfL57W9PW4AyPoZE7HhyURWNd1oXO_01jFvbKh7oLQIMtLByS3LjyRmf5pyr8xiyZ9t13x5_sng-_0rebr_BETdkVLTM6LxQJ0ilX6sSF3DgBo4M";
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
