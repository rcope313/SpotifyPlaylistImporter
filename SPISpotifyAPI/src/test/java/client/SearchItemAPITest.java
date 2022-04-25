package client;

import models.Playlist;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.spy;

public class SearchItemAPITest {
    String iTunesXMLFileOneSong, iTunesXMLFileFullPlaylist, jsonMyImmortal, jsonBringMeToLife, jsonNoResults;
    SearchItemAPI searchItemAPIOneSong, searchItemAPITwoSong, searchItemAPINoSongs;
    Playlist oneSongPlaylist, twoSongPlaylist, noSongPlaylist;

    @Before
    public void initData() {
        iTunesXMLFileOneSong = "src/test/resources/ITunesXMLFileOneSong.xml";
        iTunesXMLFileFullPlaylist = "src/test/resources/ITunesXMLFileFullPlaylist.xml";
        jsonNoResults = "{\n" +
                "  \"tracks\": {\n" +
                "    \"href\": \"https://api.spotify.com/v1/search?query=blabhablahadl&type=track&locale=en-US%2Cen%3Bq%3D0.9&offset=0&limit=1\",\n" +
                "    \"items\": [],\n" +
                "    \"limit\": 1,\n" +
                "    \"next\": null,\n" +
                "    \"offset\": 0,\n" +
                "    \"previous\": null,\n" +
                "    \"total\": 0\n" +
                "  }\n" +
                "}";
        jsonMyImmortal = "{\n" +
                "  \"tracks\": {\n" +
                "    \"href\": \"https://api.spotify.com/v1/search?query=track%3Amy+immortal&type=track&locale=en-US%2Cen%3Bq%3D0.9&offset=0&limit=1\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"album\": {\n" +
                "          \"album_type\": \"album\",\n" +
                "          \"artists\": [\n" +
                "            {\n" +
                "              \"external_urls\": {\n" +
                "                \"spotify\": \"https://open.spotify.com/artist/5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "              },\n" +
                "              \"href\": \"https://api.spotify.com/v1/artists/5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "              \"id\": \"5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "              \"name\": \"Evanescence\",\n" +
                "              \"type\": \"artist\",\n" +
                "              \"uri\": \"spotify:artist:5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"available_markets\": [\n" +
                "            \"AD\",\n" +
                "            \"AE\",\n" +
                "            \"AG\",\n" +
                "            \"AL\",\n" +
                "            \"AM\",\n" +
                "            \"AO\",\n" +
                "            \"AR\",\n" +
                "            \"AT\",\n" +
                "            \"AU\",\n" +
                "            \"AZ\",\n" +
                "            \"BA\",\n" +
                "            \"BB\",\n" +
                "            \"BD\",\n" +
                "            \"BE\",\n" +
                "            \"BF\",\n" +
                "            \"BG\",\n" +
                "            \"BH\",\n" +
                "            \"BI\",\n" +
                "            \"BJ\",\n" +
                "            \"BN\",\n" +
                "            \"BO\",\n" +
                "            \"BR\",\n" +
                "            \"BS\",\n" +
                "            \"BT\",\n" +
                "            \"BW\",\n" +
                "            \"BY\",\n" +
                "            \"BZ\",\n" +
                "            \"CA\",\n" +
                "            \"CD\",\n" +
                "            \"CG\",\n" +
                "            \"CH\",\n" +
                "            \"CI\",\n" +
                "            \"CL\",\n" +
                "            \"CM\",\n" +
                "            \"CO\",\n" +
                "            \"CR\",\n" +
                "            \"CV\",\n" +
                "            \"CY\",\n" +
                "            \"CZ\",\n" +
                "            \"DE\",\n" +
                "            \"DJ\",\n" +
                "            \"DK\",\n" +
                "            \"DM\",\n" +
                "            \"DO\",\n" +
                "            \"DZ\",\n" +
                "            \"EC\",\n" +
                "            \"EE\",\n" +
                "            \"EG\",\n" +
                "            \"ES\",\n" +
                "            \"FI\",\n" +
                "            \"FJ\",\n" +
                "            \"FM\",\n" +
                "            \"FR\",\n" +
                "            \"GA\",\n" +
                "            \"GB\",\n" +
                "            \"GD\",\n" +
                "            \"GH\",\n" +
                "            \"GM\",\n" +
                "            \"GN\",\n" +
                "            \"GQ\",\n" +
                "            \"GR\",\n" +
                "            \"GT\",\n" +
                "            \"GW\",\n" +
                "            \"GY\",\n" +
                "            \"HK\",\n" +
                "            \"HN\",\n" +
                "            \"HR\",\n" +
                "            \"HT\",\n" +
                "            \"HU\",\n" +
                "            \"ID\",\n" +
                "            \"IE\",\n" +
                "            \"IL\",\n" +
                "            \"IN\",\n" +
                "            \"IQ\",\n" +
                "            \"IS\",\n" +
                "            \"IT\",\n" +
                "            \"JM\",\n" +
                "            \"JO\",\n" +
                "            \"JP\",\n" +
                "            \"KE\",\n" +
                "            \"KG\",\n" +
                "            \"KH\",\n" +
                "            \"KI\",\n" +
                "            \"KM\",\n" +
                "            \"KN\",\n" +
                "            \"KR\",\n" +
                "            \"KW\",\n" +
                "            \"KZ\",\n" +
                "            \"LA\",\n" +
                "            \"LB\",\n" +
                "            \"LC\",\n" +
                "            \"LI\",\n" +
                "            \"LK\",\n" +
                "            \"LR\",\n" +
                "            \"LS\",\n" +
                "            \"LT\",\n" +
                "            \"LU\",\n" +
                "            \"LV\",\n" +
                "            \"LY\",\n" +
                "            \"MA\",\n" +
                "            \"MC\",\n" +
                "            \"MD\",\n" +
                "            \"ME\",\n" +
                "            \"MG\",\n" +
                "            \"MH\",\n" +
                "            \"MK\",\n" +
                "            \"ML\",\n" +
                "            \"MN\",\n" +
                "            \"MO\",\n" +
                "            \"MR\",\n" +
                "            \"MT\",\n" +
                "            \"MU\",\n" +
                "            \"MV\",\n" +
                "            \"MW\",\n" +
                "            \"MX\",\n" +
                "            \"MY\",\n" +
                "            \"MZ\",\n" +
                "            \"NA\",\n" +
                "            \"NE\",\n" +
                "            \"NG\",\n" +
                "            \"NI\",\n" +
                "            \"NL\",\n" +
                "            \"NO\",\n" +
                "            \"NP\",\n" +
                "            \"NR\",\n" +
                "            \"NZ\",\n" +
                "            \"OM\",\n" +
                "            \"PA\",\n" +
                "            \"PE\",\n" +
                "            \"PG\",\n" +
                "            \"PH\",\n" +
                "            \"PK\",\n" +
                "            \"PL\",\n" +
                "            \"PS\",\n" +
                "            \"PT\",\n" +
                "            \"PW\",\n" +
                "            \"PY\",\n" +
                "            \"QA\",\n" +
                "            \"RO\",\n" +
                "            \"RS\",\n" +
                "            \"RW\",\n" +
                "            \"SA\",\n" +
                "            \"SB\",\n" +
                "            \"SC\",\n" +
                "            \"SE\",\n" +
                "            \"SG\",\n" +
                "            \"SI\",\n" +
                "            \"SK\",\n" +
                "            \"SL\",\n" +
                "            \"SN\",\n" +
                "            \"SR\",\n" +
                "            \"ST\",\n" +
                "            \"SV\",\n" +
                "            \"SZ\",\n" +
                "            \"TD\",\n" +
                "            \"TG\",\n" +
                "            \"TH\",\n" +
                "            \"TJ\",\n" +
                "            \"TL\",\n" +
                "            \"TN\",\n" +
                "            \"TO\",\n" +
                "            \"TR\",\n" +
                "            \"TT\",\n" +
                "            \"TV\",\n" +
                "            \"TW\",\n" +
                "            \"TZ\",\n" +
                "            \"UA\",\n" +
                "            \"UG\",\n" +
                "            \"US\",\n" +
                "            \"UY\",\n" +
                "            \"UZ\",\n" +
                "            \"VC\",\n" +
                "            \"VE\",\n" +
                "            \"VN\",\n" +
                "            \"WS\",\n" +
                "            \"XK\",\n" +
                "            \"ZA\",\n" +
                "            \"ZM\",\n" +
                "            \"ZW\"\n" +
                "          ],\n" +
                "          \"external_urls\": {\n" +
                "            \"spotify\": \"https://open.spotify.com/album/02w1xMzzdF2OJxTeh1basm\"\n" +
                "          },\n" +
                "          \"href\": \"https://api.spotify.com/v1/albums/02w1xMzzdF2OJxTeh1basm\",\n" +
                "          \"id\": \"02w1xMzzdF2OJxTeh1basm\",\n" +
                "          \"images\": [\n" +
                "            {\n" +
                "              \"height\": 640,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d0000b27325f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 640\n" +
                "            },\n" +
                "            {\n" +
                "              \"height\": 300,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d00001e0225f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 300\n" +
                "            },\n" +
                "            {\n" +
                "              \"height\": 64,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d0000485125f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 64\n" +
                "            }\n" +
                "          ],\n" +
                "          \"name\": \"Fallen\",\n" +
                "          \"release_date\": \"2003-03-04\",\n" +
                "          \"release_date_precision\": \"day\",\n" +
                "          \"total_tracks\": 12,\n" +
                "          \"type\": \"album\",\n" +
                "          \"uri\": \"spotify:album:02w1xMzzdF2OJxTeh1basm\"\n" +
                "        },\n" +
                "        \"artists\": [\n" +
                "          {\n" +
                "            \"external_urls\": {\n" +
                "              \"spotify\": \"https://open.spotify.com/artist/5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "            },\n" +
                "            \"href\": \"https://api.spotify.com/v1/artists/5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "            \"id\": \"5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "            \"name\": \"Evanescence\",\n" +
                "            \"type\": \"artist\",\n" +
                "            \"uri\": \"spotify:artist:5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"available_markets\": [\n" +
                "          \"AD\",\n" +
                "          \"AE\",\n" +
                "          \"AG\",\n" +
                "          \"AL\",\n" +
                "          \"AM\",\n" +
                "          \"AO\",\n" +
                "          \"AR\",\n" +
                "          \"AT\",\n" +
                "          \"AU\",\n" +
                "          \"AZ\",\n" +
                "          \"BA\",\n" +
                "          \"BB\",\n" +
                "          \"BD\",\n" +
                "          \"BE\",\n" +
                "          \"BF\",\n" +
                "          \"BG\",\n" +
                "          \"BH\",\n" +
                "          \"BI\",\n" +
                "          \"BJ\",\n" +
                "          \"BN\",\n" +
                "          \"BO\",\n" +
                "          \"BR\",\n" +
                "          \"BS\",\n" +
                "          \"BT\",\n" +
                "          \"BW\",\n" +
                "          \"BY\",\n" +
                "          \"BZ\",\n" +
                "          \"CA\",\n" +
                "          \"CD\",\n" +
                "          \"CG\",\n" +
                "          \"CH\",\n" +
                "          \"CI\",\n" +
                "          \"CL\",\n" +
                "          \"CM\",\n" +
                "          \"CO\",\n" +
                "          \"CR\",\n" +
                "          \"CV\",\n" +
                "          \"CY\",\n" +
                "          \"CZ\",\n" +
                "          \"DE\",\n" +
                "          \"DJ\",\n" +
                "          \"DK\",\n" +
                "          \"DM\",\n" +
                "          \"DO\",\n" +
                "          \"DZ\",\n" +
                "          \"EC\",\n" +
                "          \"EE\",\n" +
                "          \"EG\",\n" +
                "          \"ES\",\n" +
                "          \"FI\",\n" +
                "          \"FJ\",\n" +
                "          \"FM\",\n" +
                "          \"FR\",\n" +
                "          \"GA\",\n" +
                "          \"GB\",\n" +
                "          \"GD\",\n" +
                "          \"GH\",\n" +
                "          \"GM\",\n" +
                "          \"GN\",\n" +
                "          \"GQ\",\n" +
                "          \"GR\",\n" +
                "          \"GT\",\n" +
                "          \"GW\",\n" +
                "          \"GY\",\n" +
                "          \"HK\",\n" +
                "          \"HN\",\n" +
                "          \"HR\",\n" +
                "          \"HT\",\n" +
                "          \"HU\",\n" +
                "          \"ID\",\n" +
                "          \"IE\",\n" +
                "          \"IL\",\n" +
                "          \"IN\",\n" +
                "          \"IQ\",\n" +
                "          \"IS\",\n" +
                "          \"IT\",\n" +
                "          \"JM\",\n" +
                "          \"JO\",\n" +
                "          \"JP\",\n" +
                "          \"KE\",\n" +
                "          \"KG\",\n" +
                "          \"KH\",\n" +
                "          \"KI\",\n" +
                "          \"KM\",\n" +
                "          \"KN\",\n" +
                "          \"KR\",\n" +
                "          \"KW\",\n" +
                "          \"KZ\",\n" +
                "          \"LA\",\n" +
                "          \"LB\",\n" +
                "          \"LC\",\n" +
                "          \"LI\",\n" +
                "          \"LK\",\n" +
                "          \"LR\",\n" +
                "          \"LS\",\n" +
                "          \"LT\",\n" +
                "          \"LU\",\n" +
                "          \"LV\",\n" +
                "          \"LY\",\n" +
                "          \"MA\",\n" +
                "          \"MC\",\n" +
                "          \"MD\",\n" +
                "          \"ME\",\n" +
                "          \"MG\",\n" +
                "          \"MH\",\n" +
                "          \"MK\",\n" +
                "          \"ML\",\n" +
                "          \"MN\",\n" +
                "          \"MO\",\n" +
                "          \"MR\",\n" +
                "          \"MT\",\n" +
                "          \"MU\",\n" +
                "          \"MV\",\n" +
                "          \"MW\",\n" +
                "          \"MX\",\n" +
                "          \"MY\",\n" +
                "          \"MZ\",\n" +
                "          \"NA\",\n" +
                "          \"NE\",\n" +
                "          \"NG\",\n" +
                "          \"NI\",\n" +
                "          \"NL\",\n" +
                "          \"NO\",\n" +
                "          \"NP\",\n" +
                "          \"NR\",\n" +
                "          \"NZ\",\n" +
                "          \"OM\",\n" +
                "          \"PA\",\n" +
                "          \"PE\",\n" +
                "          \"PG\",\n" +
                "          \"PH\",\n" +
                "          \"PK\",\n" +
                "          \"PL\",\n" +
                "          \"PS\",\n" +
                "          \"PT\",\n" +
                "          \"PW\",\n" +
                "          \"PY\",\n" +
                "          \"QA\",\n" +
                "          \"RO\",\n" +
                "          \"RS\",\n" +
                "          \"RW\",\n" +
                "          \"SA\",\n" +
                "          \"SB\",\n" +
                "          \"SC\",\n" +
                "          \"SE\",\n" +
                "          \"SG\",\n" +
                "          \"SI\",\n" +
                "          \"SK\",\n" +
                "          \"SL\",\n" +
                "          \"SN\",\n" +
                "          \"SR\",\n" +
                "          \"ST\",\n" +
                "          \"SV\",\n" +
                "          \"SZ\",\n" +
                "          \"TD\",\n" +
                "          \"TG\",\n" +
                "          \"TH\",\n" +
                "          \"TJ\",\n" +
                "          \"TL\",\n" +
                "          \"TN\",\n" +
                "          \"TO\",\n" +
                "          \"TR\",\n" +
                "          \"TT\",\n" +
                "          \"TV\",\n" +
                "          \"TW\",\n" +
                "          \"TZ\",\n" +
                "          \"UA\",\n" +
                "          \"UG\",\n" +
                "          \"US\",\n" +
                "          \"UY\",\n" +
                "          \"UZ\",\n" +
                "          \"VC\",\n" +
                "          \"VE\",\n" +
                "          \"VN\",\n" +
                "          \"WS\",\n" +
                "          \"XK\",\n" +
                "          \"ZA\",\n" +
                "          \"ZM\",\n" +
                "          \"ZW\"\n" +
                "        ],\n" +
                "        \"disc_number\": 1,\n" +
                "        \"duration_ms\": 262533,\n" +
                "        \"explicit\": false,\n" +
                "        \"external_ids\": {\n" +
                "          \"isrc\": \"USWU30200104\"\n" +
                "        },\n" +
                "        \"external_urls\": {\n" +
                "          \"spotify\": \"https://open.spotify.com/track/4UzVcXufOhGUwF56HT7b8M\"\n" +
                "        },\n" +
                "        \"href\": \"https://api.spotify.com/v1/tracks/4UzVcXufOhGUwF56HT7b8M\",\n" +
                "        \"id\": \"4UzVcXufOhGUwF56HT7b8M\",\n" +
                "        \"is_local\": false,\n" +
                "        \"name\": \"My Immortal\",\n" +
                "        \"popularity\": 73,\n" +
                "        \"preview_url\": null,\n" +
                "        \"track_number\": 4,\n" +
                "        \"type\": \"track\",\n" +
                "        \"uri\": \"spotify:track:4UzVcXufOhGUwF56HT7b8M\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"limit\": 1,\n" +
                "    \"next\": \"https://api.spotify.com/v1/search?query=track%3Amy+immortal&type=track&locale=en-US%2Cen%3Bq%3D0.9&offset=1&limit=1\",\n" +
                "    \"offset\": 0,\n" +
                "    \"previous\": null,\n" +
                "    \"total\": 1246\n" +
                "  }\n" +
                "}";
        jsonBringMeToLife = "{\n" +
                "  \"tracks\": {\n" +
                "    \"href\": \"https://api.spotify.com/v1/search?query=track%3Abring+me+to+life&type=track&locale=en-US%2Cen%3Bq%3D0.9&offset=0&limit=1\",\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"album\": {\n" +
                "          \"album_type\": \"album\",\n" +
                "          \"artists\": [\n" +
                "            {\n" +
                "              \"external_urls\": {\n" +
                "                \"spotify\": \"https://open.spotify.com/artist/5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "              },\n" +
                "              \"href\": \"https://api.spotify.com/v1/artists/5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "              \"id\": \"5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "              \"name\": \"Evanescence\",\n" +
                "              \"type\": \"artist\",\n" +
                "              \"uri\": \"spotify:artist:5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"available_markets\": [\n" +
                "            \"AD\",\n" +
                "            \"AE\",\n" +
                "            \"AG\",\n" +
                "            \"AL\",\n" +
                "            \"AM\",\n" +
                "            \"AO\",\n" +
                "            \"AR\",\n" +
                "            \"AT\",\n" +
                "            \"AU\",\n" +
                "            \"AZ\",\n" +
                "            \"BA\",\n" +
                "            \"BB\",\n" +
                "            \"BD\",\n" +
                "            \"BE\",\n" +
                "            \"BF\",\n" +
                "            \"BG\",\n" +
                "            \"BH\",\n" +
                "            \"BI\",\n" +
                "            \"BJ\",\n" +
                "            \"BN\",\n" +
                "            \"BO\",\n" +
                "            \"BR\",\n" +
                "            \"BS\",\n" +
                "            \"BT\",\n" +
                "            \"BW\",\n" +
                "            \"BY\",\n" +
                "            \"BZ\",\n" +
                "            \"CA\",\n" +
                "            \"CD\",\n" +
                "            \"CG\",\n" +
                "            \"CH\",\n" +
                "            \"CI\",\n" +
                "            \"CL\",\n" +
                "            \"CM\",\n" +
                "            \"CO\",\n" +
                "            \"CR\",\n" +
                "            \"CV\",\n" +
                "            \"CY\",\n" +
                "            \"CZ\",\n" +
                "            \"DE\",\n" +
                "            \"DJ\",\n" +
                "            \"DK\",\n" +
                "            \"DM\",\n" +
                "            \"DO\",\n" +
                "            \"DZ\",\n" +
                "            \"EC\",\n" +
                "            \"EE\",\n" +
                "            \"EG\",\n" +
                "            \"ES\",\n" +
                "            \"FI\",\n" +
                "            \"FJ\",\n" +
                "            \"FM\",\n" +
                "            \"FR\",\n" +
                "            \"GA\",\n" +
                "            \"GB\",\n" +
                "            \"GD\",\n" +
                "            \"GH\",\n" +
                "            \"GM\",\n" +
                "            \"GN\",\n" +
                "            \"GQ\",\n" +
                "            \"GR\",\n" +
                "            \"GT\",\n" +
                "            \"GW\",\n" +
                "            \"GY\",\n" +
                "            \"HK\",\n" +
                "            \"HN\",\n" +
                "            \"HR\",\n" +
                "            \"HT\",\n" +
                "            \"HU\",\n" +
                "            \"ID\",\n" +
                "            \"IE\",\n" +
                "            \"IL\",\n" +
                "            \"IN\",\n" +
                "            \"IQ\",\n" +
                "            \"IS\",\n" +
                "            \"IT\",\n" +
                "            \"JM\",\n" +
                "            \"JO\",\n" +
                "            \"JP\",\n" +
                "            \"KE\",\n" +
                "            \"KG\",\n" +
                "            \"KH\",\n" +
                "            \"KI\",\n" +
                "            \"KM\",\n" +
                "            \"KN\",\n" +
                "            \"KR\",\n" +
                "            \"KW\",\n" +
                "            \"KZ\",\n" +
                "            \"LA\",\n" +
                "            \"LB\",\n" +
                "            \"LC\",\n" +
                "            \"LI\",\n" +
                "            \"LK\",\n" +
                "            \"LR\",\n" +
                "            \"LS\",\n" +
                "            \"LT\",\n" +
                "            \"LU\",\n" +
                "            \"LV\",\n" +
                "            \"LY\",\n" +
                "            \"MA\",\n" +
                "            \"MC\",\n" +
                "            \"MD\",\n" +
                "            \"ME\",\n" +
                "            \"MG\",\n" +
                "            \"MH\",\n" +
                "            \"MK\",\n" +
                "            \"ML\",\n" +
                "            \"MN\",\n" +
                "            \"MO\",\n" +
                "            \"MR\",\n" +
                "            \"MT\",\n" +
                "            \"MU\",\n" +
                "            \"MV\",\n" +
                "            \"MW\",\n" +
                "            \"MX\",\n" +
                "            \"MY\",\n" +
                "            \"MZ\",\n" +
                "            \"NA\",\n" +
                "            \"NE\",\n" +
                "            \"NG\",\n" +
                "            \"NI\",\n" +
                "            \"NL\",\n" +
                "            \"NO\",\n" +
                "            \"NP\",\n" +
                "            \"NR\",\n" +
                "            \"NZ\",\n" +
                "            \"OM\",\n" +
                "            \"PA\",\n" +
                "            \"PE\",\n" +
                "            \"PG\",\n" +
                "            \"PH\",\n" +
                "            \"PK\",\n" +
                "            \"PL\",\n" +
                "            \"PS\",\n" +
                "            \"PT\",\n" +
                "            \"PW\",\n" +
                "            \"PY\",\n" +
                "            \"QA\",\n" +
                "            \"RO\",\n" +
                "            \"RS\",\n" +
                "            \"RW\",\n" +
                "            \"SA\",\n" +
                "            \"SB\",\n" +
                "            \"SC\",\n" +
                "            \"SE\",\n" +
                "            \"SG\",\n" +
                "            \"SI\",\n" +
                "            \"SK\",\n" +
                "            \"SL\",\n" +
                "            \"SN\",\n" +
                "            \"SR\",\n" +
                "            \"ST\",\n" +
                "            \"SV\",\n" +
                "            \"SZ\",\n" +
                "            \"TD\",\n" +
                "            \"TG\",\n" +
                "            \"TH\",\n" +
                "            \"TJ\",\n" +
                "            \"TL\",\n" +
                "            \"TN\",\n" +
                "            \"TO\",\n" +
                "            \"TR\",\n" +
                "            \"TT\",\n" +
                "            \"TV\",\n" +
                "            \"TW\",\n" +
                "            \"TZ\",\n" +
                "            \"UA\",\n" +
                "            \"UG\",\n" +
                "            \"US\",\n" +
                "            \"ZW\"\n" +
                "          ],\n" +
                "          \"external_urls\": {\n" +
                "            \"spotify\": \"https://open.spotify.com/album/02w1xMzzdF2OJxTeh1basm\"\n" +
                "          },\n" +
                "          \"href\": \"https://api.spotify.com/v1/albums/02w1xMzzdF2OJxTeh1basm\",\n" +
                "          \"id\": \"02w1xMzzdF2OJxTeh1basm\",\n" +
                "          \"images\": [\n" +
                "            {\n" +
                "              \"height\": 640,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d0000b27325f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 640\n" +
                "            },\n" +
                "            {\n" +
                "              \"height\": 300,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d00001e0225f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 300\n" +
                "            },\n" +
                "            {\n" +
                "              \"height\": 64,\n" +
                "              \"url\": \"https://i.scdn.co/image/ab67616d0000485125f49ab23f0ec6332efef432\",\n" +
                "              \"width\": 64\n" +
                "            }\n" +
                "          ],\n" +
                "          \"name\": \"Fallen\",\n" +
                "          \"release_date\": \"2003-03-04\",\n" +
                "          \"release_date_precision\": \"day\",\n" +
                "          \"total_tracks\": 12,\n" +
                "          \"type\": \"album\",\n" +
                "          \"uri\": \"spotify:album:02w1xMzzdF2OJxTeh1basm\"\n" +
                "        },\n" +
                "        \"artists\": [\n" +
                "          {\n" +
                "            \"external_urls\": {\n" +
                "              \"spotify\": \"https://open.spotify.com/artist/5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "            },\n" +
                "            \"href\": \"https://api.spotify.com/v1/artists/5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "            \"id\": \"5nGIFgo0shDenQYSE0Sn7c\",\n" +
                "            \"name\": \"Evanescence\",\n" +
                "            \"type\": \"artist\",\n" +
                "            \"uri\": \"spotify:artist:5nGIFgo0shDenQYSE0Sn7c\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"available_markets\": [\n" +
                "          \"AD\",\n" +
                "          \"AE\",\n" +
                "          \"AG\",\n" +
                "          \"AL\",\n" +
                "          \"AM\",\n" +
                "          \"AO\",\n" +
                "          \"AR\",\n" +
                "          \"AT\",\n" +
                "          \"AU\",\n" +
                "          \"AZ\",\n" +
                "          \"BA\",\n" +
                "          \"BB\",\n" +
                "          \"BD\",\n" +
                "          \"BE\",\n" +
                "          \"BF\",\n" +
                "          \"BG\",\n" +
                "          \"BH\",\n" +
                "          \"BI\",\n" +
                "          \"BJ\",\n" +
                "          \"BN\",\n" +
                "          \"BO\",\n" +
                "          \"BR\",\n" +
                "          \"BS\",\n" +
                "          \"BT\",\n" +
                "          \"BW\",\n" +
                "          \"BY\",\n" +
                "          \"BZ\",\n" +
                "          \"CA\",\n" +
                "          \"CD\",\n" +
                "          \"CG\",\n" +
                "          \"CH\",\n" +
                "          \"CI\",\n" +
                "          \"CL\",\n" +
                "          \"CM\",\n" +
                "          \"CO\",\n" +
                "          \"CR\",\n" +
                "          \"CV\",\n" +
                "          \"CY\",\n" +
                "          \"CZ\",\n" +
                "          \"DE\",\n" +
                "          \"DJ\",\n" +
                "          \"DK\",\n" +
                "          \"DM\",\n" +
                "          \"DO\",\n" +
                "          \"DZ\",\n" +
                "          \"EC\",\n" +
                "          \"EE\",\n" +
                "          \"EG\",\n" +
                "          \"ES\",\n" +
                "          \"FI\",\n" +
                "          \"FJ\",\n" +
                "          \"FM\",\n" +
                "          \"FR\",\n" +
                "          \"GA\",\n" +
                "          \"GB\",\n" +
                "          \"GD\",\n" +
                "          \"GH\",\n" +
                "          \"GM\",\n" +
                "          \"GN\",\n" +
                "          \"GQ\",\n" +
                "          \"GR\",\n" +
                "          \"GT\",\n" +
                "          \"GW\",\n" +
                "          \"GY\",\n" +
                "          \"HK\",\n" +
                "          \"HN\",\n" +
                "          \"HR\",\n" +
                "          \"HT\",\n" +
                "          \"HU\",\n" +
                "          \"ID\",\n" +
                "          \"IE\",\n" +
                "          \"IL\",\n" +
                "          \"IN\",\n" +
                "          \"IQ\",\n" +
                "          \"IS\",\n" +
                "          \"IT\",\n" +
                "          \"JM\",\n" +
                "          \"JO\",\n" +
                "          \"JP\",\n" +
                "          \"KE\",\n" +
                "          \"KG\",\n" +
                "          \"KH\",\n" +
                "          \"KI\",\n" +
                "          \"KM\",\n" +
                "          \"KN\",\n" +
                "          \"KR\",\n" +
                "          \"KW\",\n" +
                "          \"KZ\",\n" +
                "          \"LA\",\n" +
                "          \"LB\",\n" +
                "          \"LC\",\n" +
                "          \"LI\",\n" +
                "          \"LK\",\n" +
                "          \"LR\",\n" +
                "          \"LS\",\n" +
                "          \"LT\",\n" +
                "          \"LU\",\n" +
                "          \"LV\",\n" +
                "          \"LY\",\n" +
                "          \"MA\",\n" +
                "          \"MC\",\n" +
                "          \"MD\",\n" +
                "          \"ME\",\n" +
                "          \"MG\",\n" +
                "          \"MH\",\n" +
                "          \"MK\",\n" +
                "          \"ML\",\n" +
                "          \"MN\",\n" +
                "          \"MO\",\n" +
                "          \"MR\",\n" +
                "          \"MT\",\n" +
                "          \"MU\",\n" +
                "          \"MV\",\n" +
                "          \"MW\",\n" +
                "          \"MX\",\n" +
                "          \"MY\",\n" +
                "          \"MZ\",\n" +
                "          \"NA\",\n" +
                "          \"NE\",\n" +
                "          \"NG\",\n" +
                "          \"NI\",\n" +
                "          \"NL\",\n" +
                "          \"NO\",\n" +
                "          \"NP\",\n" +
                "          \"NR\",\n" +
                "          \"NZ\",\n" +
                "          \"OM\",\n" +
                "          \"PA\",\n" +
                "          \"PE\",\n" +
                "          \"PG\",\n" +
                "          \"PH\",\n" +
                "          \"PK\",\n" +
                "          \"PL\",\n" +
                "          \"PS\",\n" +
                "          \"PT\",\n" +
                "          \"PW\",\n" +
                "          \"PY\",\n" +
                "          \"QA\",\n" +
                "          \"RO\",\n" +
                "          \"RS\",\n" +
                "          \"RW\",\n" +
                "          \"SA\",\n" +
                "          \"SB\",\n" +
                "          \"SC\",\n" +
                "          \"SE\",\n" +
                "          \"SG\",\n" +
                "          \"SI\",\n" +
                "          \"SK\",\n" +
                "          \"SL\",\n" +
                "          \"SN\",\n" +
                "          \"SR\",\n" +
                "          \"ST\",\n" +
                "          \"SV\",\n" +
                "          \"SZ\",\n" +
                "          \"TD\",\n" +
                "          \"TG\",\n" +
                "          \"TH\",\n" +
                "          \"TJ\",\n" +
                "          \"TL\",\n" +
                "          \"TN\",\n" +
                "          \"TO\",\n" +
                "          \"TR\",\n" +
                "          \"TT\",\n" +
                "          \"TV\",\n" +
                "          \"TW\",\n" +
                "          \"TZ\",\n" +
                "          \"UA\",\n" +
                "          \"UG\",\n" +
                "          \"US\",\n" +
                "          \"UY\",\n" +
                "          \"UZ\",\n" +
                "          \"VC\",\n" +
                "          \"VE\",\n" +
                "          \"VN\",\n" +
                "          \"WS\",\n" +
                "          \"XK\",\n" +
                "          \"ZA\",\n" +
                "          \"ZM\",\n" +
                "          \"ZW\"\n" +
                "        ],\n" +
                "        \"disc_number\": 1,\n" +
                "        \"duration_ms\": 235893,\n" +
                "        \"explicit\": false,\n" +
                "        \"external_ids\": {\n" +
                "          \"isrc\": \"USWU30200093\"\n" +
                "        },\n" +
                "        \"external_urls\": {\n" +
                "          \"spotify\": \"https://open.spotify.com/track/0COqiPhxzoWICwFCS4eZcp\"\n" +
                "        },\n" +
                "        \"href\": \"https://api.spotify.com/v1/tracks/0COqiPhxzoWICwFCS4eZcp\",\n" +
                "        \"id\": \"0COqiPhxzoWICwFCS4eZcp\",\n" +
                "        \"is_local\": false,\n" +
                "        \"name\": \"Bring Me To Life\",\n" +
                "        \"popularity\": 80,\n" +
                "        \"preview_url\": null,\n" +
                "        \"track_number\": 2,\n" +
                "        \"type\": \"track\",\n" +
                "        \"uri\": \"spotify:track:0COqiPhxzoWICwFCS4eZcp\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"limit\": 1,\n" +
                "    \"next\": \"https://api.spotify.com/v1/search?query=track%3Abring+me+to+life&type=track&locale=en-US%2Cen%3Bq%3D0.9&offset=1&limit=1\",\n" +
                "    \"offset\": 0,\n" +
                "    \"previous\": null,\n" +
                "    \"total\": 1645\n" +
                "  }\n" +
                "}";

        oneSongPlaylist = new Playlist("One Song Playlist", "A playlist of one song", true);
        twoSongPlaylist = new Playlist("Two Song Playlist", "a playlist of 2 songs", false);
        noSongPlaylist = new Playlist("No Song Playlist", "a playlist of no songs", false);

        searchItemAPIOneSong = new SearchItemAPI(iTunesXMLFileOneSong, oneSongPlaylist, "token");
        searchItemAPITwoSong = new SearchItemAPI(iTunesXMLFileFullPlaylist, twoSongPlaylist, "token");
        searchItemAPINoSongs = new SearchItemAPI(iTunesXMLFileFullPlaylist, noSongPlaylist, "token");
    }

    @Test
    public void itBuildsJsonSpotifyTrackURIList() throws IOException {
        SearchItemAPI api1 = spy(searchItemAPIOneSong);
        Mockito.doReturn(new ArrayList<>(List.of(jsonMyImmortal))).when(api1).buildJsonTrackListByItemSearchAPI();
        assertThat(api1.buildJsonSpotifyTrackURIList())
                .isEqualTo("{\"uris\":[\"spotify:track:4UzVcXufOhGUwF56HT7b8M\"]}");

        SearchItemAPI api2 = spy(searchItemAPITwoSong);
        Mockito.doReturn(new ArrayList<>(List.of(jsonMyImmortal, jsonBringMeToLife))).when(api2).buildJsonTrackListByItemSearchAPI();
        assertThat(api2.buildJsonSpotifyTrackURIList())
                .isEqualTo("{\"uris\":[\"spotify:track:4UzVcXufOhGUwF56HT7b8M\",\"spotify:track:0COqiPhxzoWICwFCS4eZcp\"]}");

        SearchItemAPI api3 = spy(searchItemAPINoSongs);
        Mockito.doReturn(new ArrayList<>(List.of(jsonNoResults))).when(api3).buildJsonTrackListByItemSearchAPI();
        assertThat(api3.buildJsonSpotifyTrackURIList())
                .isEqualTo("{\"uris\":[]}");
    }
}
