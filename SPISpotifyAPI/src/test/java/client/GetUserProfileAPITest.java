package client;

import models.Playlist;
import models.SpotifyUser;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class GetUserProfileAPITest {
    String mockXMLFileString, token;
    GetUserProfileAPI searchItemAPIOneSong, searchItemAPIFullPlaylist;
    Playlist mockPlaylist;
    SpotifyUser user1;

    void initData() {
        token = "BQCGkq3sRzafvJbZ-f34jgM-2sjsd4hP2PgpHOMPKdmHDFufYXHqEzIjj40Xvj75SACqR1hMQiPA3ovJ_kfGqmjs53gqqkTfL85pMijq4pTPsqIO3tLr-aq3x0HzNL5hELfepftladYnkoplh8hY3vh8_yE77FlHrAg-sb3j8RAztcnQhVn8Z_GYSg-iTSMIWs2kymUAb7-dn-xZiHVrykY";
        mockXMLFileString = "file.xml";
        mockPlaylist = new Playlist("name", "description", false);

        searchItemAPIOneSong = new GetUserProfileAPI(mockXMLFileString, mockPlaylist, token);
        searchItemAPIFullPlaylist = new GetUserProfileAPI(mockXMLFileString, mockPlaylist, token);

        user1 = new SpotifyUser("1287343652");
    }

    @Test
    public void itGetsCurrentUsersProfile() throws IOException, InterruptedException {
        this.initData();
        assertThat(searchItemAPIFullPlaylist.getCurrentUsersProfile()).usingRecursiveComparison().isEqualTo(user1);
    }
}
