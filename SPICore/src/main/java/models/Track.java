package models;

public class Track {

    private String trackName;
    private String artistName;
    private String albumName;
    private String genre;
    private int year;

    public Track() {}

    public Track (String trackName, String artistName, String albumName, String genre, int year) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        Track aTrack = (Track)obj;
        return  aTrack.getTrackName().equals(this.getTrackName()) &&
                aTrack.getArtistName().equals(this.getArtistName()) &&
                aTrack.getAlbumName().equals(this.getAlbumName()) &&
                aTrack.getGenre().equals(this.getGenre()) &&
                aTrack.getYear() == this.getYear();
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
