package com.faithfulolaleru.ForUoPeople.CS1103.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Scanner scanner;
    private boolean running = true;
    private String username;


    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
    }

    public void start() {
        try {
            // Get username from user
            scanner = new Scanner(System.in);
            System.out.print("Enter your preferred username: ");
            username = scanner.nextLine().trim();
            if (username.isEmpty()) {
                username = "Anonymous";
            }

            // Setup connection to server
            System.out.println("\nConnecting to server...");
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send username to server (server will still assign a unique ID)
            out.println(username);


            // Print welcome message
            System.out.println("Connected to chat server. Type messages to send.");
            System.out.println("Type '/quit' to exit the chat.");
            System.out.println("\n-------------- MESSAGE HISTORY --------------\n");

            // Start a separate thread to handle incoming messages
            new Thread(this::handleIncomingMessages).start();

            // Handle outgoing messages in the main thread
            handleOutgoingMessages();

        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            // e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void handleIncomingMessages() {
        try {
            String message;
            while (running && (message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            if (running) {
                System.out.println("Error reading from server: " + e.getMessage());
            }
        }
    }

    private void handleOutgoingMessages() {
        while (running) {
            String message = scanner.nextLine();
            out.println(message);

            if (message.equalsIgnoreCase("/quit")) {
                running = false;
                break;
            }
        }
    }

    private void closeConnection() {
        running = false;
        try {
            if (scanner != null) scanner.close();
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            System.out.println("Disconnected from server.");
        } catch (IOException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
