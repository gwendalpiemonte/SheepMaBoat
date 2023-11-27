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
    private Game currentGame;
    private final List<ClientHandler> activeClients;
    private Player player;
    private boolean isReady = false;
    private boolean isPlaying = true;
    private final String EOT = "END";
    private static boolean oneOrTwo = false; // true player one is playing flase player two is playing
    private String state = "";
    private static boolean gameIsRunning = false;
    private boolean setUsernames = false;




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
        if(gameIsRunning){
            if (message[0].equals("shoot")) {
                if(message.length != 2){
                    sendMessage("You have to use the correct syntax of the shoot command.");
                }
                else{
                    shootSheep(message[1]);
                    /*
                    char column = message[1].charAt(0);
                    int row = Integer.parseInt(message[1].substring(1));
                    if(column < 'A' || column > 'E' || row < 1 || row > 5){
                        sendMessage("We didn't understand your message, try again.");
                    }
                    else{
                        shootSheep(message[1]);
                    }
                     */
                }
            }
            else{
                sendMessage("You have to enter a shoot to play.");
            }
        }
        else{
            if(!this.setUsernames){
                if (message[0].equals("username")) {
                    sendMessage(addUsername(message.length < 2 ? "" : message[1]));
                }
                else{
                    sendMessage("You have to enter a username first.");
                }
            }
            else{
                if (message[0].equals("start")) {
                    startGame();
                }
                else{
                    sendMessage("You have to enter start to start the game.");
                }
            }
        }
    }

    private synchronized String addUsername(String message) {
        if (message.length() <= 15 && message.length() >= 3) {
            this.setUsernames = true;
            this.player = new Player(message);
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
        if(activeClients.get(0).isReady() && activeClients.get(1).isReady()) {
            // Crée une game
            currentGame = new Game();

            // Y ajoute les joueurs
            currentGame.setPlayer(activeClients.get(0).getPlayer());
            currentGame.setPlayer(activeClients.get(1).getPlayer());

            gameIsRunning = true;

            //client 1 joue en premier
            activeClients.get(0).sendMessage(currentGame.printGame(activeClients.get(0).getPlayer()) + "\n"
                    + "Last shoot in : \n"
                    + "Oponent shoot in : \n"
                    + "Use the command: shoot <column [A-E]> <row [1-5]>");
        }
    }
    private void shootSheep(String message) {
//        Player player1 = activeClients.get(0).getPlayer();
//        Player player2 = activeClients.get(1).getPlayer();
//
//        ClientHandler player1Client = activeClients.get(0);
//        ClientHandler player2Client = activeClients.get(1);
//
//        char column = message.charAt(0);
//        int row = Integer.parseInt(message.substring(1));
//
//        if(message.length() == 2) {
//            oneOrTwo = !oneOrTwo;
//
//            // le client 1 joue
//            if(oneOrTwo){
//                activeClients.get(0).state = currentGame.playRound(player1, player2, column, row);
//                activeClients.get(0).state = column + "" + row + " -> "  + activeClients.get(0).state;
//                player2Client.sendMessage(currentGame.printGame(player2) + "\n"
//                        + "Your last shoot in : " + activeClients.get(1).state + "\n"
//                        + "Oponent   shoot in : " + activeClients.get(0).state + "\n"
//                        + "Use the command : shoot <column [A-E]> <row [1-5]>");}
//                if(currentGame.isHasWinner()){
//                    player1Client.isReady = false;
//                    player2Client.isReady = false;
//                    activeClients.get(0).sendMessage("You won ! \nyou can replay with the replay command or disconnect with the disconnect command");
//                    activeClients.get(1).sendMessage("You lose ! \nyou can replay with the replay command or disconnect with the disconnect command");
//                    oneOrTwo = false;
//                    gameIsRunning = false;
//                }
//            }
//            //le client 2 joue
//            if(!oneOrTwo) {
//                activeClients.get(1).state = currentGame.playRound(player2, player1, column, row);
//                activeClients.get(1).state = column + "" + row + " -> "  + activeClients.get(1).state;
//                player1Client.sendMessage(currentGame.printGame(player1) + "\n"
//                        + "Your last shoot in : " + activeClients.get(0).state + "\n"
//                        + "Oponent   shoot in : " + activeClients.get(1).state  + "\n"
//                        + "Use the command : shoot <column [A-E]> <row [1-5]>");
//                if(currentGame.isHasWinner()){
//                    player1Client.isReady = false;
//                    player2Client.isReady = false;
//                    activeClients.get(0).sendMessage("You lose ! \nyou can replay with the replay command or disconnect with the disconnect command");
//                    activeClients.get(1).sendMessage("You won ! \nyou can replay with the replay command or disconnect with the disconnect command");
//                    oneOrTwo = false;
//                    gameIsRunning = false;
//                }
//            }

            char column = message.charAt(0);
            int row = Integer.parseInt(message.substring(1));

            if(message.length() == 2) {
                oneOrTwo = !oneOrTwo;

                int currentPlayer = oneOrTwo ? 0 : 1;
                int waitingPlayer = oneOrTwo ? 1 : 0;

                activeClients.get(currentPlayer).state = currentGame.playRound(activeClients.get(currentPlayer).getPlayer(), activeClients.get(waitingPlayer).getPlayer(), column, row);

                if(currentGame.isHasWinner()) {
                    activeClients.get(currentPlayer).sendMessage("You won ! \nThe game is over thanks!");
                    activeClients.get(waitingPlayer).sendMessage("You lose ! \nThe game is over thanks!");

                    activeClients.get(currentPlayer).closeSocketConnexion();
                    activeClients.get(waitingPlayer).closeSocketConnexion();

                } else {
                    activeClients.get(currentPlayer).state = column + "" + row + " -> "  + activeClients.get(currentPlayer).state;
                    activeClients.get(waitingPlayer).sendMessage(currentGame.printGame(activeClients.get(waitingPlayer).getPlayer()) + "\n"
                            + "Your last shoot in : " + activeClients.get(waitingPlayer).state + "\n"
                            + "Oponent   shoot in : " + activeClients.get(currentPlayer).state + "\n"
                            + "Use the command : shoot <column [A-E]> <row [1-5]>");}
                }
        }
        private void HandleEndGame(Player winner, Player loser) {

        /*
            if(currentGame.isHasWinner()){
                player1Client.isReady = false;
                player2Client.isReady = false;
                activeClients.get(0).sendMessage("You lose ! \nyou can replay with the replay command or disconnect with the disconnect command");
                activeClients.get(1).sendMessage("You won ! \nyou can replay with the replay command or disconnect with the disconnect command");
                oneOrTwo = false;
                gameIsRunning = false;
            }

         */
        }

        private void closeSocketConnexion() {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

