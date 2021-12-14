package models;

public class Track {

    public final String trackName;
    public final String artistName;
    public final String albumName;
    public final String genre;
    public final int year;

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

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }
}
