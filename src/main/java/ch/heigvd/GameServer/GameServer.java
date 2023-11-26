package ch.heigvd.GameServer;

import ch.heigvd.Game.Game;
import ch.heigvd.Game.Joueur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private final int MAX_CLIENTS = 2;
    private final List<ClientHandler> activeClients = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(MAX_CLIENTS);
    private boolean isRunning = true;
    private final Game currentGame = new Game();

    public void start(String serverIP, int serverPort) {
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
                        // Utiliser le thread pool pour gérer la communication avec le client
                        ClientHandler clientHandler = new ClientHandler(clientSocket, currentGame, activeClients);
                        activeClients.add(clientHandler);
                        executor.submit(clientHandler);

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
        } finally {
            // Arrêter le pool après que toutes les tâches ont été soumises
            executor.shutdown();
        }
    }

    private void handleShutdownCommand(ServerSocket serverSocket) {
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

    private synchronized boolean isRunning() {
        return isRunning;
    }

    private synchronized void stopServer(ServerSocket serverSocket) {
        try {
            isRunning = false;
            // Fermer le ServerSocket pour débloquer l'accept
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
