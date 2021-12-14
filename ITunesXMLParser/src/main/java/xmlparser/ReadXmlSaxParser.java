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


public class ReadXmlSaxParser {

    public static ArrayList<Track> parse(String fileName) {

        ArrayList<Track> result = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try (InputStream is = getXMLFileAsStream(fileName)) {

            SAXParser saxParser = factory.newSAXParser();
            MapStaffObjectHandlerSax handler = new MapStaffObjectHandlerSax();
            saxParser.parse(is, handler);

            result = handler.getResult();


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    private static InputStream getXMLFileAsStream(String fileName) throws FileNotFoundException {

        InputStream targetStream = new FileInputStream(fileName);
        return targetStream;

    }

}
