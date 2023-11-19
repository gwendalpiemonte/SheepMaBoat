package ch.heigvd.GameServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameServer {
    private static final int MAX_CLIENTS = 2;
    private static final List<ClientHandler> activeClients = new ArrayList<>();
    private static boolean isRunning = true;

    public static void start(String serverIP, int serverPort) {
        try {
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            try (ServerSocket serverSocket = new ServerSocket(serverPort, 1, serverAddress)) {
                System.out.println("Server waiting connection on " + serverAddress + ":" + serverPort);

                // Créer un thread pour gérer la commande d'arrêt
                new Thread(() -> handleShutdownCommand(serverSocket)).start();

                while (isRunning()) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New connection : " + clientSocket);

                    if (activeClients.size() < MAX_CLIENTS) {
                        // Créer un thread pour gérer la communication avec le client
                        ClientHandler clientHandler = new ClientHandler(clientSocket);
                        activeClients.add(clientHandler);
                        new Thread(clientHandler).start();
                    } else {
                        System.out.println("Connection refused for : " + clientSocket);

                        // Refuser la connexion si le serveur est plein
                        PrintWriter refusedOut = new PrintWriter(clientSocket.getOutputStream(), true);
                        refusedOut.println("Server is full. Try again later.");

                        // Fermer la socket du client refusé
                        clientSocket.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleShutdownCommand(ServerSocket serverSocket) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter 'shutdown' to stop the server.");
            while (isRunning()) {
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("shutdown")) {
                    System.out.println("Server shutting down.");
                    stopServer(serverSocket);
                } else {
                    System.out.println("Unknown command. Enter 'shutdown' to stop the server.");
                }
            }
        }
    }

    private static synchronized boolean isRunning() {
        return isRunning;
    }

    private static synchronized void stopServer(ServerSocket serverSocket) {
        try {
            isRunning = false;
            // Fermer le ServerSocket pour débloquer l'accept
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
