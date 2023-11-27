package ch.heigvd.GameClient;

import ch.heigvd.Game.PlayBoard;

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
                while ((line = bin.readLine()) != null && !line.equals(EOT)) {
                    textOut.append(line);
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
            if(messages[1].equals("W")){
                System.out.println("You won ! \nThe game is over, thanks for playing !");
            }
            else if(messages[1].equals("L")){
                System.out.println("You lose ! \nThe game is over, thanks for playing !");
            }

            // Fermer la connexion
            closeSocketConnexion();

            out.println("GoodBye");
        }
        else if(messages[0].equals("ERR")){
            switch(messages[1]) {
                case "100":
                    System.err.println("The server is full. Please try later.");
                    break;
                case "200":
                    System.err.println("Use command username <username [3 - 15]>.");
                    break;
                case "201":
                    System.err.println("The username is too short. Use command username <username [3 - 15]>.");
                    break;
                case "202":
                    System.err.println("The username is too long. Use command username <username [3 - 15]>.");
                    break;
                case "203":
                    System.err.println("The username doesn't fit our policies. Use command username <username [3 - 15]>.");
                    break;
                case "204":
                    System.err.println("Before using start command you need to choose a username. Use command username <username [3 - 15]>.");
                    break;
                case "300":
                    System.err.println("You have to enter a shoot to play.");
                    break;
                case "400":
                case "401":
                    System.err.println("You need to use the command shoot <position -> B1>");
                    break;
                case "402":
                    System.err.println("Your shoot is out of the play board. Please shoot between : " + PlayBoard.minColumn + PlayBoard.minRow + " - " + PlayBoard.maxColumn + PlayBoard.maxRow);
                    break;
                case "5":
                    System.err.println("You have to enter start to start the game.");
                    break;
                default:
                    System.err.println("Error, something went wrong.");
                    break;
            }
        }
        else if (messages[0].equals("CMD")) {
            for(int i = 1 ; i < messages.length ; ++i)
                System.out.print(messages[i] + " ");
            System.out.println();
        }
        else if(messages[0].equals("TXT")) {
            System.out.flush();
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