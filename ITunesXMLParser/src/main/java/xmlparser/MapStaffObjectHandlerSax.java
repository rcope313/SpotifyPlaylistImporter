package xmlparser;

import models.Track;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MapStaffObjectHandlerSax extends DefaultHandler {

    private StringBuilder currentValue = new StringBuilder();
    ArrayList<Track> result;
    Track currentTrack = new Track();

    public ArrayList<Track> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        result = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // trackName

        if (currentTrack.trackName.equals("Waiting For Input")) {
            currentTrack.trackName = currentValue.toString();
        }

        if (currentValue.toString().equals("Name")) {
            currentTrack.trackName = "Waiting For Input";

        }

        // artistName

        if (currentTrack.artistName.equals("Waiting For Input")) {
            currentTrack.artistName = currentValue.toString();
        }

        if (currentValue.toString().equals("Artist")) {
            currentTrack.artistName = "Waiting For Input";

        }

        // albumName

        if (currentTrack.albumName.equals("Waiting For Input")) {
            currentTrack.albumName = currentValue.toString();
        }

        if (currentValue.toString().equals("Album")) {
            currentTrack.albumName = "Waiting For Input";

        }

        // genre

        if (currentTrack.genre.equals("Waiting For Input")) {
            currentTrack.genre = currentValue.toString();
        }

        if (currentValue.toString().equals("Genre")) {
            currentTrack.genre = "Waiting For Input";

        }

        // year

        if (currentTrack.year == -1) {
            currentTrack.year = Integer.valueOf(currentValue.toString());
            result.add(currentTrack);
            currentTrack = new Track();
        }

        if (currentValue.toString().equals("Year")) {
            currentTrack.year = -1;

        }


        currentValue.setLength(0);


    }



    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }

}
