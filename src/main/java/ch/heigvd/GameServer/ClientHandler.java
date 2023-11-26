package ch.heigvd.GameServer;

import ch.heigvd.Game.Game;
import ch.heigvd.Game.Joueur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private final Game curentGame;

    private Joueur player;

    public ClientHandler(Socket socket, Game game) {
        this.clientSocket = socket;
        this.curentGame = game;
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
            out.println("Welcome to Battlesheep! Please choose a username. Use the command: username <username>");

            while (true) {
                String[] clientMessage = in.nextLine().split(" ");
                String serverResponse = handleMessage(this, clientMessage);
                out.println(serverResponse);
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

    public synchronized String handleMessage(ClientHandler clientHandler, String[] message) {
        return switch (message[0]) {
            case "username" -> addUsername(message.length < 2 ? "" : message[1]);
            case "start" -> startGame();
            default -> "We didn't understand your message, try again.";
        };
    }

    private String addUsername(String username) {
        if (username.length() <= 15 && username.length() >= 3) {
            player = new Joueur(username);
            return "Welcome, " + player.getUsername() + "! Please write start when you are ready! Use the command: start";
        } else if(username.length() < 3) {
            return "Your username: " + username + " is too short. Use the command: username <username [3-15]>";
        } else if(username.length() > 15) {
            return "Your username: " + username + " is too long. Use the command: username <username [3-15]>";
        } else {
            return "Your username: " + username + " doesn't fit our policies. Use the command: username <username [3-15]>";
        }
    }

    private String startGame() {
        curentGame.setPlayer(player);
        if(curentGame.isReady())
            return "The game will start!";
        return "Your oppenent isn't yet ready!";
    }
}