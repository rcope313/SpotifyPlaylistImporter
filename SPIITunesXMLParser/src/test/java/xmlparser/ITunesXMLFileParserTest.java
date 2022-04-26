package xmlparser;

import models.Track;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

public class ITunesXMLFileParserTest {

    Track
            myImmortal, soColdICouldSeeMyBreath, iWriteSinsNotTragedies, darkBlue, helena,
            superman, timeWontLetMeGo, heyThereDelilah, chasingCars, bestOfYou, someoneToLove,
            theKill, theGreatEscape, starlight, hereInYourArms, alreadyOver, breatheIntoMe,
            famousLastWords, yourGuardianAngel, onlyOne, oceanAvenue;

    ArrayList<Track>
            p1, p2;
    String
            errorMessage, pathToIncorrectFileType,
            pathToIncorrectXMLFormat, pathToFullPlaylistXml,
            pathToOneSongPlaylistXml;

    @Before
    public void initData() {
        errorMessage = "Unable to parse tracks from given file. Please check path of file and try again.";
        pathToIncorrectFileType = "src/test/resources/IncorrectFileType.json";
        pathToIncorrectXMLFormat = "src/test/resources/IncorrectXMLFormat.xml";
        pathToFullPlaylistXml = "src/test/resources/ITunesXMLFileFullPlaylist.xml";
        pathToOneSongPlaylistXml = "src/test/resources/ITunesXMLFileOneSong.xml";

        myImmortal =
                new Track("My Immortal (Band Version)",
                        "Evanescence",
                        "Fallen",
                        "Hard Rock",
                        2003);
        soColdICouldSeeMyBreath =
                new Track("So Cold I Could See My Breath",
                        "Emery",
                        "The Question",
                        "Alternative",
                        2005);
        iWriteSinsNotTragedies =
                new Track("I Write Sins Not Tragedies",
                        "Panic! At the Disco",
                        "A Fever You Can't Sweat Out",
                        "Alternative",
                        2005);
        darkBlue =
                new Track("Dark Blue",
                        "Jack's Mannequin",
                        "Everything In Transit (Bonus Track Version)",
                        "Alternative",
                        2005);
        helena =
                new Track("Helena",
                        "My Chemical Romance",
                        "Three Cheers for Sweet Revenge",
                        "Alternative",
                        2004);
        superman =
                new Track("Superman",
                        "Five for Fighting",
                        "America Town",
                        "Alternative",
                        2001);
        timeWontLetMeGo =
                new Track("Time Won't Let Me Go",
                        "The Bravery",
                        "Time Won't Let Me Go - Single",
                        "Rock",
                        2007);
        heyThereDelilah =
                new Track("Hey There Delilah",
                        "Plain White T's",
                        "All That We Needed",
                        "Alternative",
                        2005);
        chasingCars =
                new Track("Chasing Cars",
                        "Snow Patrol",
                        "Eyes Open (Bonus Track Version)",
                        "Alternative",
                        2006);
        bestOfYou =
                new Track("Best of You",
                        "Foo Fighters",
                        "In Your Honor",
                        "Alternative",
                        2005);
        someoneToLove =
                new Track("Someone to Love",
                        "Fountains Of Wayne",
                        "Traffic and Weather",
                        "Alternative",
                        2007);
        theKill =
                new Track("The Kill",
                        "Thirty Seconds to Mars",
                        "A Beautiful Lie",
                        "Alternative",
                        2005);
        theGreatEscape =
                new Track("The Great Escape",
                        "Boys Like Girls",
                        "Boys Like Girls",
                        "Alternative",
                        2006);
        starlight =
                new Track("Starlight",
                        "Muse",
                        "Black Holes and Revelations",
                        "Alternative",
                        2006);
        hereInYourArms =
                new Track("Here (In Your Arms)",
                        "Hellogoodbye",
                        "Zombies! Aliens! Vampires! Dinosaurs!",
                        "Alternative",
                        2006);
        alreadyOver =
                new Track("Already Over",
                        "Red",
                        "End of Silence",
                        "Alternative",
                        2006);
        breatheIntoMe =
                new Track("Breathe Into Me",
                        "Red",
                        "End of Silence",
                        "Alternative",
                        2006);
        famousLastWords =
                new Track("Famous Last Words",
                        "My Chemical Romance",
                        "The Black Parade",
                        "Alternative",
                        2006);
        yourGuardianAngel =
                new Track("Your Guardian Angel",
                        "The Red Jumpsuit Apparatus",
                        "Don't You Fake It",
                        "Rock",
                        2006);
        onlyOne =
                new Track("Only One",
                        "Yellowcard",
                        "Ocean Avenue",
                        "Rock",
                        2003);
        oceanAvenue =
                new Track("Ocean Avenue",
                        "Yellowcard",
                        "Ocean Avenue",
                        "Rock",
                        2003);

        p1 = new ArrayList<>(Arrays.asList(myImmortal));
        p2 = new ArrayList<>(Arrays.asList(soColdICouldSeeMyBreath, iWriteSinsNotTragedies, darkBlue, helena,
                superman, timeWontLetMeGo, heyThereDelilah, chasingCars, bestOfYou, someoneToLove,
                theKill, theGreatEscape, starlight, hereInYourArms, alreadyOver, breatheIntoMe,
                famousLastWords, yourGuardianAngel, onlyOne, oceanAvenue));
    }

    @Test
    public void itThrowsExceptionWhenIllegalXMLTypeInserted() {
        assertThatThrownBy(() -> ITunesXMLFileParser.parse(pathToIncorrectFileType))
                .isInstanceOf(IllegalStateException.class).hasMessageContaining(errorMessage);
        assertThatThrownBy(() -> ITunesXMLFileParser.parse(pathToIncorrectXMLFormat))
                .isInstanceOf(IllegalStateException.class).hasMessageContaining(errorMessage);
    }

    @Test
    public void itReadsITunesXMLFileAndBuildsArrayListOfTracks() {
        ArrayList<Track> instantiatedTrackList1 = ITunesXMLFileParser.parse
                (pathToOneSongPlaylistXml);
        for (int idx = 0; idx < p1.size(); idx++) {
            assertThat(instantiatedTrackList1.get(idx)).isEqualTo(p1.get(idx));
        }

        ArrayList<Track> instantiatedTrackList2 = ITunesXMLFileParser.parse
                (pathToFullPlaylistXml);
        for (int idx = 0; idx < p2.size(); idx++) {
            assertThat(instantiatedTrackList2.get(idx))
                    .withFailMessage("Idx %s is incorrect", idx)
                    .isEqualTo(p2.get(idx));
        }
    }
}
