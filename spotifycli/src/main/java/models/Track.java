package models;

public class Track {

    public String trackName;
    public String artistName;
    public String albumName;
    public String genre;
    public int year;

    public Track (String trackName, String artistName, String albumName, String genre, int year) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.genre = genre;
        this.year = year;
    }

    public Track () {
        trackName = "";
        artistName = "";
        albumName = "";
        genre = "";
        year = 0;

    }
}
