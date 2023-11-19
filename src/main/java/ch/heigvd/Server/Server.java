package ch.heigvd.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void start(String serverIP, int serverPort) {
        try {

            // Création de l'objet InetAddress à partir de l'adresse IP fournie
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            try (ServerSocket serverSocket = new ServerSocket(serverPort, 1, serverAddress)) {
                System.out.println("Server waiting connection on " + serverAddress + ":" + serverPort);

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New connection : " + clientSocket);

                    // Créer un thread pour gérer la communication avec le client
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            out.println("Welcome on this Battlesheep server! Please choose a username [max 15 char.] username <myusername> : ");

            while (true) {

                // Lire les messages du client
                String clientMessage = in.nextLine();

                out.println(HandleMessage(clientMessage));

                System.out.println("Client " + clientSocket + " says : " + clientMessage);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Fermer les ressources
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String HandleMessage(String message) {
        if(message.contains("username")) {
            String username = message.replace("username ", "");
            if(username.length() > 15) {
                return username + " is a nice username!";
            } else {
                return "Your username : " + username + " doesn't fit our policies. Try again with username <myusername>";
            }
        } else {
            return "We didn't undersand your message, try again.";
        }
    }

}
