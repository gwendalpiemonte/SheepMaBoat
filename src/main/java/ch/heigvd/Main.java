package ch.heigvd;

import ch.heigvd.Client.Client;
import ch.heigvd.PicoCLI.PicoCLI;
import ch.heigvd.Server.Server;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {

        String serverAddress = "127.0.0.1";

        Server.start(serverAddress, 5555);
        //Client.run(serverAddress, 5555);

        System.exit(0);

    }
}