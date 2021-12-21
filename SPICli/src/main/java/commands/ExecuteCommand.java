package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

@CommandLine.Command(name = "execute")
public class ExecuteCommand implements Runnable, Command {

    @CommandLine.Option(names = {"-f", "--file"}, required = true)
    public String xmlFile;
    @CommandLine.Option(names = {"-pn", "--playlist"}, required = true)
    public String playlistName;
    @CommandLine.Option(names = {"-d", "--description"})
    public String playlistDescription;
    @CommandLine.Option(names = {"-p", "--public"})
    public boolean playlistIsPublic;
    public ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run() {

    }


}
