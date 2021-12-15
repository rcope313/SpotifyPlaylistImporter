package models;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class TrackTest {

    Track myImmortal, soColdICouldSeeMyBreath;

    void initData() {
        myImmortal = new Track("My Immortal (Band Version)",
                        "Evanescence",
                        "Fallen",
                        "Hard Rock",
                        2003);
        soColdICouldSeeMyBreath = new Track("So Cold I Could See My Breath",
                        "Emery",
                        "The Question",
                        "Alternative",
                        2005);
    }

    @Test
    public void itEvaluatesSameTracks() {
        this.initData();
        assertThat(myImmortal).isEqualTo(myImmortal);
        assertThat(myImmortal).isNotEqualTo(soColdICouldSeeMyBreath);
    }
}
