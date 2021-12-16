package models;

public class Track {

    private final String trackName;
    private final String artistName;
    private final String albumName;
    private final String genre;
    private final int year;

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
