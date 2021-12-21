package xmlparser;

import models.Track;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ITunesXMLFileParser {

    private final static SAXParserFactory FACTORY = SAXParserFactory.newInstance();

    public static ArrayList<Track> parse(String fileName) {
        ITunesXMLFileDefaultHandler handler = new ITunesXMLFileDefaultHandler();

        try (InputStream is = getXMLFileAsStream(fileName)) {
            SAXParser saxParser = FACTORY.newSAXParser();
            saxParser.parse(is, handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return handler.getHandlerResultTrackList();
    }

    private static InputStream getXMLFileAsStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}
