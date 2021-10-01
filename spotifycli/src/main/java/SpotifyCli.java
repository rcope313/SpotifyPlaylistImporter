
import commands.AuthCommand;
import commands.ExecuteCommand;
import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(
        subcommands = {AuthCommand.class, ExecuteCommand.class},
        name = "myapp",
        mixinStandardHelpOptions = true,
        version = "1.0")

class SpotifyCli implements Callable<Integer> {

    @Override
    public Integer call() { // business logic
        return 123;
    }

    public static void main(String... args) { // bootstrap the application
        System.exit(new CommandLine(new SpotifyCli()).execute(args));
    }
}


