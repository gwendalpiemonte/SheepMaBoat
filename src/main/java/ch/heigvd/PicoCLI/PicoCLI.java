package ch.heigvd.PicoCLI;

import picocli.CommandLine;

@CommandLine.Command(
        name = "SheepMaBoat - Choose between playing or hosting!",
        version = "1.0",
        description = "A funny battlesheeep! ",
        subcommands = { PicoCLI.StartClient.class, PicoCLI.StartServer.class},
        mixinStandardHelpOptions = true)

public class PicoCLI {
    @CommandLine.Command(
            name = "client",
            version = "1.0",
            description = "Start the client!",
            mixinStandardHelpOptions = true)

    public static class StartClient implements Runnable {

        @Override
        public void run() {

        }
    }

    @CommandLine.Command(
            name = "server",
            version = "1.0",
            description = "Start the server!",
            mixinStandardHelpOptions = true)
    public static class StartServer implements Runnable {

        @Override
        public void run() {

        }
    }
}