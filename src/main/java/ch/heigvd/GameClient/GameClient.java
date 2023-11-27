package ch.heigvd.GameClient;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GameClient {
    Socket socket;
    PrintWriter out;
    Scanner in;
    BufferedReader bin;
    Scanner consoleInput;
    final static String EOT = "END";
    public void start(String serverIP, int serverPort) {

        try {

            socket = new Socket(serverIP, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            consoleInput = new Scanner(System.in);


            // Boucle pour permettre à l'utilisateur d'envoyer des messages au serveur
            while (true) {

                // Lire la réponse du serveur
                String line;
                StringBuilder textOut = new StringBuilder();
                while ((line = bin.readLine()) != null && !line.equals("END")) {
                    textOut.append(line + " ");
                }

                handleMessage(textOut.toString().split("_"));

                // Ecris le message à envoyer au serveur
                String userMessage = consoleInput.nextLine();

                // Envoyer le message au serveur
                out.println(userMessage);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(String[] messages) {
        if(messages[0].equals("EGE")){
            //fermer les sockerts
            closeSocketConnexion();

        }
        else if(messages[0].equals("ERR")){
            switch(messages[1]) {
                case "1":
                    System.out.println("You have to use the correct syntax of the shoot command.");
                    break;
                case "2":
                    System.out.println("cacapouette");
                    break;
                case "3":
                    System.out.println("You have to enter a shoot to play.");
                    break;
                case "4":
                    System.out.println("You have to enter a username first.");
                    break;
                case "5":
                    System.out.println("You have to enter start to start the game.");
                    break;
                default:
                    System.out.println("Error, something went wrong.");
                    break;
            }
        }
        else if (messages[0].equals("CMD")) {
            System.out.print("CMD ");
            for(int i = 1 ; i < messages.length ; ++i)
                System.out.print(messages[i] + " ");
            System.out.println();
        }
        else if(messages[0].equals("TXT")) {System.out.print("TXT ");
            for(int i = 1 ; i < messages.length ; ++i)
                System.out.println(messages[i]);

        }
    }

    private void closeSocketConnexion() {
        try {

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            consoleInput = new Scanner(System.in);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}