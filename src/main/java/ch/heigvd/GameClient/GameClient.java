package ch.heigvd.GameClient;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GameClient {
    final static String EOT = "END";
    public static void start(String serverIP, int serverPort) {

        try (Socket socket = new Socket(serverIP, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner consoleInput = new Scanner(System.in)) {

            // Lire les messages du serveur
            System.out.println(in.nextLine());

            // Boucle pour permettre à l'utilisateur d'envoyer des messages au serveur
            while (true) {

                // Ecris le message à envoyer au serveur
                String userMessage = consoleInput.nextLine();

                // Envoyer le message au serveur
                out.println(userMessage);

                // Lire la réponse du serveur
                // Lire la réponse du serveur
                String line;
                while ((line = input.readLine()) != null && !line.equals("END")) {
                    System.out.println(line);
                }


                // Une idee pour jouer les tours
                /*
                // Lire la réponse du serveur
                String line;
                while ((line = input.readLine()) != null) {


                    if (line.equals("END")) {
                        break;

                    } else if (line.equals("PLAY")) {
                        System.out.println("play");
                        break;

                    } else if (line.equals("WAIT")) {

                    }
                    System.out.println(line);
                }
                 */
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}