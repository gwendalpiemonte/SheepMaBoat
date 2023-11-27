package ch.heigvd.GameServer;

import ch.heigvd.Game.Game;
import ch.heigvd.Game.PlayBoard;
import ch.heigvd.Game.Player;
import ch.heigvd.Game.Position;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private Game currentGame;
    private final List<ClientHandler> activeClients;
    private Player player = null;
    private boolean isReady = false;
    private final String EOT = "END";
    private static boolean oneOrTwo = false; // true player one is playing flase player two is playing
    private String state = "";
    private static boolean gameIsRunning = false;
    private final ExecutorService executor;




    public ClientHandler(Socket socket, Game game, List<ClientHandler> activeClients, ExecutorService executor) {
        this.clientSocket = socket;
        this.currentGame = game;
        this.activeClients = activeClients;
        this.executor = executor;
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
            sendMessage("CMD", "Welcome to Battlesheep! Please choose a username. Use the command: username <username>");

            while (true) {
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

    public void sendMessage(String type, String message) {
        out.println(type + "_");
        out.println(message + "_");
        out.println(EOT);
    }

    public Player getPlayer() { return this.player; }

    public synchronized void handleMessage(String[] message) {
        if(gameIsRunning){
            if (message[0].equals("shoot")) {
                if(message.length == 2){
                    if(message[1].charAt(0) >= PlayBoard.minColumn && message[1].charAt(0) <= PlayBoard.maxColumn &&
                       message[1].charAt(1) >= PlayBoard.minRow && message[1].charAt(1) <= PlayBoard.maxRow){
                        shootSheep(message[1]);
                    } else {
                        sendMessage("ERR", "402");
                    }

                } else{
                    sendMessage("ERR", "401");
                }
            } else{
                sendMessage("ERR", "400");
            }
        }
        else{
            if(player == null){
                if (message[0].equals("username")) {
                    addUsername(message.length < 2 ? "" : message[1]);
                } else if (message[0].equals("start")) {
                    sendMessage("ERR", "204");
                } else{
                    sendMessage("ERR", "200");
                }
            }
            else{
                if (message[0].equals("start")) {
                    startGame();
                }
                else{
                    sendMessage("ERR", "5");
                }
            }
        }
    }

    private synchronized void addUsername(String message) {
        if (message.length() <= 15 && message.length() >= 3) {
            this.player = new Player(message);
             sendMessage("CMD", "Welcome, " + message + "! Please write start when you are ready! Use the command: start");
        } else if(message.length() < 3) {
            sendMessage("ERR", "201");
        } else if(message.length() > 15) {
            sendMessage("ERR", "202");
        } else {
            sendMessage("ERR", "203");
        }
    }
    private void startGame() {
        this.isReady = true;
        if(activeClients.size() == 2 && activeClients.get(0).isReady() && activeClients.get(1).isReady()) {

            // Y ajoute les joueurs
            currentGame.setPlayer(activeClients.get(0).getPlayer());
            currentGame.setPlayer(activeClients.get(1).getPlayer());

            gameIsRunning = true;

            //client 1 joue en premier
            activeClients.get(0).sendMessage("TXT", currentGame.printGame(activeClients.get(0).getPlayer()) + "_"
                    + "Last    shoot in : _"
                    + "Oponent shoot in : _"
                    + "Use the command: shoot <column [A-E]> <row [1-5]>");
        }
    }
    private void shootSheep(String message) {

        char column = message.charAt(0);
        int row = Integer.parseInt(message.substring(1));

        if(message.length() == 2) {
            oneOrTwo = !oneOrTwo;

            int currentPlayer = oneOrTwo ? 0 : 1;
            int waitingPlayer = oneOrTwo ? 1 : 0;

            activeClients.get(currentPlayer).state = currentGame.playRound(activeClients.get(currentPlayer).getPlayer(), activeClients.get(waitingPlayer).getPlayer(), column, row);

            if(currentGame.isHasWinner()) {
                activeClients.get(currentPlayer).sendMessage("EGE", "W");
                activeClients.get(waitingPlayer).sendMessage("EGE", "L");

                activeClients.clear();

            } else {
                activeClients.get(currentPlayer).state = column + "" + row + " -> "  + activeClients.get(currentPlayer).state;
                activeClients.get(waitingPlayer).sendMessage("TXT", currentGame.printGame(activeClients.get(waitingPlayer).getPlayer()) + "_"
                        + "Your last shoot in : " + activeClients.get(waitingPlayer).state + "_"
                        + "Oponent   shoot in : " + activeClients.get(currentPlayer).state + "_"
                        + "Use the command : shoot <column [A-E]> <row [1-5]>");}
            }
        }
    }

