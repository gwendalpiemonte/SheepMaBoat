package ch.heigvd.GameServer;

import ch.heigvd.Game.Game;
import ch.heigvd.Game.Player;
import ch.heigvd.Game.Position;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private final Game currentGame;
    private final List<ClientHandler> activeClients;
    private Player player;
    private boolean isReady = false;
    private final String EOT = "END";

    private boolean check = false;

    public ClientHandler(Socket socket, Game game, List<ClientHandler> activeClients) {
        this.clientSocket = socket;
        this.currentGame = game;
        this.activeClients = activeClients;
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            out.println("Welcome to Battlesheep! Please choose a username. Use the command: username <username>");

            while (true) {
                // idee pour afficher a qui de jouer etc
                /*
                if(check){
                    if(currentGame.getRound() % 2 != 0){
                        activeClients.get(0).sendMessage("It's your turn to play!");
                        activeClients.get(1).sendMessage("It's your opponent's turn to play!");
                    } else {
                        activeClients.get(1).sendMessage("It's your turn to play!");
                        activeClients.get(0).sendMessage("It's your opponent's turn to play!");
                    }
                }
                */
                String[] clientMessage = in.nextLine().split(" ");
                handleMessage(clientMessage);
                System.out.println("Client " + clientSocket + " says: " + Arrays.toString(clientMessage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public void sendMessage(String message) {
        out.println(message);
        out.println(EOT); // il fallait l'envoyer en deux fois pour que ca marche et pasd l'ajouter a la fin de chaque message
    }

    public Player getPlayer() { return this.player; }

    public synchronized void handleMessage(String[] message) {
        switch (message[0]) {
            case "username" -> sendMessage(addUsername(message.length < 2 ? "" : message[1]));
            case "start" -> startGame();
            case "shoot" -> shootSheep(message.length < 2 ? "" : message[1]);
            default -> sendMessage("We didn't understand your message, try again.");
        };
    }

    private synchronized String addUsername(String message) {
        if (message.length() <= 15 && message.length() >= 3) {
            this.player = new Player(message);
            currentGame.setPlayer(this.player);
            return "Welcome, " + message + "! Please write start when you are ready! Use the command: start";
        } else if(message.length() < 3) {
            return "Your username: " + message + " is too short. Use the command: username <username [3-15]>";
        } else if(message.length() > 15) {
            return "Your username: " + message + " is too long. Use the command: username <username [3-15]>";
        } else {
            return "Your username: " + message + " doesn't fit our policies. Use the command: username <username [3-15]>";
        }
    }
    private void startGame() {
        this.isReady = true;
        if(currentGame.isReady() && activeClients.get(0).isReady() && activeClients.get(1).isReady()) {
            check = true;
            for(ClientHandler activeClient : activeClients)
                activeClient.sendMessage(currentGame.printGame(activeClient.getPlayer()));
        }
    }
    private void shootSheep(String message) {

        char column = message.charAt(0);
        int row = Integer.parseInt(message.substring(1));

        if(message.length() == 2) {
            if(currentGame.getRound() % 2 != 0){
                currentGame.playRound(activeClients.get(0).getPlayer(), activeClients.get(1).getPlayer(), column, row);
                activeClients.get(0).sendMessage(currentGame.printGame(activeClients.get(0).getPlayer()) + "\n" + "It's your opponent's turn to play!");
                activeClients.get(1).sendMessage(currentGame.printGame(activeClients.get(1).getPlayer()) + "\n" + "It's your turn to play!");
            } else {
                currentGame.playRound(activeClients.get(1).getPlayer(), activeClients.get(0).getPlayer(), column, row);
                activeClients.get(0).sendMessage(currentGame.printGame(activeClients.get(0).getPlayer()) + "\n" + "It's your turn to play!");
                activeClients.get(1).sendMessage(currentGame.printGame(activeClients.get(1).getPlayer()) + "\n" + "It's your opponent's turn to play!");
            }

            currentGame.nextRound();

        }
    }
}