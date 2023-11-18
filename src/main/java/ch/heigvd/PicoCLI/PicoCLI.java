package ch.heigvd.PicoCLI;

import picocli.CommandLine;

@CommandLine.Command(
        name = "SheepMaBoat - Try to beat everyone!",
        version = "1.0",
        description = "A funny battlesheeep! ",
        subcommands = { PicoCLI.Client.class, PicoCLI.Server.class},
        mixinStandardHelpOptions = true)
public class PicoCLI{

    @CommandLine.Command(
            name = "client",
            version = "1.0",
            description = "Start the client.",
            mixinStandardHelpOptions = true)
    public static class Client implements Runnable{
        @CommandLine.Option(
                names = {"-a", "--address"},
                required = true,
                description = "IP address to connect.")
        private String address;
        @CommandLine.Option(
                names = {"-p", "--port"},
                description = "Port to connect.")
        private String port;
        @Override
        public void run() {
            System.out.println("Le client va démarrer!");
        }
    }

    @CommandLine.Command(
            name = "server",
            version = "1.0",
            description = "Start the server.",
            mixinStandardHelpOptions = true)
    public static class Server implements Runnable{
        @CommandLine.Option(
                names = {"-a", "--address"},
                description = "IP address to connect.")
        private String address;
        @CommandLine.Option(
                names = {"-p", "--port"},
                description = "Port to connect.")
        private String port;
        @Override
        public void run() {
            System.out.println("Le serveur va démarrer!");
        }
    }
}