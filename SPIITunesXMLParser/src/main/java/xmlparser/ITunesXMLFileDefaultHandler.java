package xmlparser;

import models.Track;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class ITunesXMLFileDefaultHandler extends DefaultHandler {

    private final StringBuilder stringBuilder = new StringBuilder();
    private final ArrayList<Track> handlerResultTrackList = new ArrayList<>();
    private Track currentTrack = new Track();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (currentTrack.getTrackName() != null && currentTrack.getTrackName().equals("Waiting For Input")) {
            currentTrack.setTrackName(stringBuilder.toString());
        }
        if (stringBuilder.toString().equals("Name")) {
            currentTrack.setTrackName("Waiting For Input");
        }
        if (currentTrack.getArtistName() != null && currentTrack.getArtistName().equals("Waiting For Input")) {
            currentTrack.setArtistName(stringBuilder.toString());
        }
        if (stringBuilder.toString().equals("Artist")) {
            currentTrack.setArtistName("Waiting For Input");
        }
        if (currentTrack.getAlbumName() != null && currentTrack.getAlbumName().equals("Waiting For Input")) {
            currentTrack.setAlbumName(stringBuilder.toString());
        }
        if (stringBuilder.toString().equals("Album")) {
            currentTrack.setAlbumName("Waiting For Input");
        }
        if (currentTrack.getGenre() != null && currentTrack.getGenre().equals("Waiting For Input")) {
            currentTrack.setGenre(stringBuilder.toString());
        }
        if (stringBuilder.toString().equals("Genre")) {
            currentTrack.setGenre("Waiting For Input");
        }
        if (currentTrack.getYear() == -1) {
            currentTrack.setYear(Integer.valueOf(stringBuilder.toString()));
            handlerResultTrackList.add(currentTrack);
            currentTrack = new Track();
        }
        if (stringBuilder.toString().equals("Year")) {
            currentTrack.setYear(-1);
        }
        stringBuilder.setLength(0);
    }

    @Override
    public void characters(char ch[], int start, int length) {
        stringBuilder.append(ch, start, length);
    }

    public ArrayList<Track> getHandlerResultTrackList() {
        return handlerResultTrackList;
    }
}
