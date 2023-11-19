package ch.heigvd.GameServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private String username;

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
            out.println("Welcome to Battlesheep! Please choose a username. Use the command: username <username>");

            while (true) {
                String clientMessage = in.nextLine();
                String serverResponse = handleMessage(this, clientMessage);
                out.println(serverResponse);
                System.out.println("Client " + username + " says: " + clientMessage);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public synchronized String handleMessage(ClientHandler clientHandler, String message) {

        if (message.startsWith("username")) {

            String username = message.replace("username ", "");
            if (username.length() <= 15 && username.length() >= 3) {
                clientHandler.setUsername(username);
                return "Welcome, " + username + "! Please write start when you are ready! Use the command: start";
            } else if(username.length() < 3) {
                return "Your username: " + username + " is too short. Use the command: username <username [3-15]>";
            } else if(username.length() > 15) {
                return "Your username: " + username + " is too long. Use the command: username <username [3-15]>";
            } else {
                return "Your username: " + username + " doesn't fit our policies. Use the command: username <username [3-15]>";
            }

        } else if (message.contains("start")) {

            if(username.isEmpty())
                return "You need to set a username first. Use the command : username <username>";
            else
                return "We are waiting for your oponnent to start the game.";

        } else {

            return "We didn't understand your message, try again.";

        }
    }
}