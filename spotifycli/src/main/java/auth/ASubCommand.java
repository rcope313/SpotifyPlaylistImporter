package auth;
import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "add"
)

public class ASubCommand implements Callable<Integer> {
    @CommandLine.Option(names = "-x")
    boolean x;


    @Override
    public Integer call() { // business logic
        if (x) {
            System.out.print("blah blah blah");
        } else {
            System.out.print("ok fine you got me");
        }

        return 123;
    }

}
