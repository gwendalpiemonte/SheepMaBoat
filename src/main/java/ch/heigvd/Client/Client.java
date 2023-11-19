package ch.heigvd.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 5555);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());
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
                System.out.println(in.nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}