# SpotifyPlaylistImporter 🎸

## Project Description 
Have some old emo playlists from your ITunes that you have been 
wanting to bust out recently? SpotifyPlaylistImporter is a command 
line application that quickly and easily imports ITunes playlists 
into your Spotify playlist library.

## Design Principles
The Spotify API authentication is implemented according to [theOAuth 
2.0 specification](https://developer.spotify.com/documentation/general/guides/authorization/). 
As described in the [authorization code flow](https://developer.spotify.com/documentation/general/guides/authorization/code-flow/),
an application requests user authorization for data access. Upon 
authorization, the user is redirected back to the application via
the predefined redirect uri. Spotify Accounts Service supplies a 
code upon callback, in which the application can exchange the code 
for access & refresh tokens; the access token is necessary to make
requests to all Web API endpoints. Because SpotifyPlaylistImporter 
only operates in the command line, it was not obvious how it should
interact with browser flow to receive the code for token exchange.
Instead, the application creates a temporary localhost web server,
living just as long as the authorization code is received.

From the command line, the user launches SpotifyPlaylistImporter
with the execute subcommand. This prompts the application to 
start the temporary server; this server directs the user to the 
browser to authenticate and then redirects the request to the 
predefined redirect url. The application parses the authorization 
code from the redirect uri. From there, the application continues
through authorization code flow as mentioned.

## Using SpotifyPlaylistImporter
SpotifyPlaylistImporter supports the single sub-command ```execute```,
which handles the entire application flow.
- Jar File: SPICli/target/Cli-1.0-SNAPSHOT-jar-with-dependencies.jar
- Command: ```java -jar <pathToJar> execute <args>```

#### ```<args>```
| Type                 | Type                            | About                                                                                              |   
|----------------------|---------------------------------|----------------------------------------------------------------------------------------------------|
| .xml file            | required parameter @ index 0    | Path name to the exported <iTunesplaylist>.xml.                                                    |
| playlist name        | required parameter @ index 1    | If including spaces, contain name within quotes.                                                   |
| playlist description | optional: "-d", "--description" | Limit is 300 characters, including spaces. If including spaces, contain description within quotes. |
| is playlist public   | optional, "-p", "--public"      | Whether the Spotify playlist will be public or private.                                            |

To get the .xml file of the respective ITunes playlist, in the Music app, 
select a playlist, then choose File > Library > Export Playlist. 

[![Screen-Shot-2022-04-25-at-5-54-29-PM.png](https://i.postimg.cc/PxRbQ51s/Screen-Shot-2022-04-25-at-5-54-29-PM.png)](https://postimg.cc/vg56QMP3)

The application will then prompt the user for authentication. 

[![start-Authentication.png](https://i.postimg.cc/QxB4PRMJ/start-Authentication.png)](https://postimg.cc/kBmvRYK2)

In some terminal emulators, the user will need to copy and paste the 
given link. In the browser, the user is prompted to sign into Spotify 
(if already signed-in, this step is skipped). This authorizes access,
and on success, the server will display instructions to return to the
respective terminal emulator. 

<p align="center">
  <img src="https://media.giphy.com/media/mDjZ6VB0tIAQfx7iyT/giphy.gif"/>
</p>

Behind the scenes, the applications requests and retrieves an access
token to make varied requests to Spotify Web API to create a new 
empty playlist and populate that playlist with the songs from the 
ITunes .xml file. If while searching tracks the application does 
not receive any results, no track will be added.

Finally, after all able tracks have been imported to Spotify, the 
application informs the user of its completion. 

[![playlist-Imported.png](https://i.postimg.cc/CMrrcFwK/playlist-Imported.png)](https://postimg.cc/V5MF6Pry)

## For the Future
For better transportability, it would be interesting to refactor 
SpotifyPlaylistImporter into a web application. While it would still 
follow the same authorization code flow–a web app running on the 
server–the command line interaction with the browser would obviously 
be negligible. 

## Credits
[Picocli - A Mighty Tiny Command Interface](https://picocli.info/)

[Spotify For Developers](https://developer.spotify.com/)

