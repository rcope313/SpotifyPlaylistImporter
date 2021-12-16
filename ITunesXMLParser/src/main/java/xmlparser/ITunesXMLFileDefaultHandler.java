package xmlparser;

import models.Track;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class ITunesXMLFileDefaultHandler extends DefaultHandler {

    private final StringBuilder currentHandlerStringValue = new StringBuilder();
    private ArrayList<Track> handlerResultTrackList;

    @Override
    public void startDocument() {
        handlerResultTrackList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        String trackName = null;
        String artistName = null;
        String albumName = null;
        String genre = null;
        int year = 0;

        if (trackName != null && trackName.equals("Waiting For Input")) {
            trackName = currentHandlerStringValue.toString();
        }
        if (currentHandlerStringValue.toString().equals("Name")) {
            trackName = "Waiting For Input";
        }
        if (artistName != null && artistName.equals("Waiting For Input")) {
            artistName = currentHandlerStringValue.toString();
        }
        if (currentHandlerStringValue.toString().equals("Artist")) {
            artistName = "Waiting For Input";
        }
        if (albumName != null && albumName.equals("Waiting For Input")) {
            albumName = currentHandlerStringValue.toString();
        }
        if (currentHandlerStringValue.toString().equals("Album")) {
            albumName = "Waiting For Input";
        }
        if (genre != null && genre.equals("Waiting For Input")) {
            genre = currentHandlerStringValue.toString();
        }
        if (currentHandlerStringValue.toString().equals("Genre")) {
            genre = "Waiting For Input";
        }
        if (year == -1) {
            year = Integer.parseInt(currentHandlerStringValue.toString());
            handlerResultTrackList.add(new Track(trackName, artistName, albumName, genre, year));
        }
        if (currentHandlerStringValue.toString().equals("Year")) {
            year = -1;
        }
        currentHandlerStringValue.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length)  {
        currentHandlerStringValue.append(ch, start, length);
    }

    public ArrayList<Track> getHandlerResultTrackList() {
        return handlerResultTrackList;
    }
}
