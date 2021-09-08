import auth.ASubCommand;
import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(
        subcommands = {ASubCommand.class},
        name = "myapp",
        mixinStandardHelpOptions = true,
        version = "1.0")
class SpotifyCli implements Callable<Integer> {

    @CommandLine.Option(names = "-x")
    boolean x;

    @Override
    public Integer call() { // business logic
        if (x) {
            System.out.print(x);
        } else {
            System.out.print("https://www.google.com/ ");
        }

        return 123;
    }

    public static void main(String... args) { // bootstrap the application
        System.exit(new CommandLine(new SpotifyCli()).execute(args));
    }
}

