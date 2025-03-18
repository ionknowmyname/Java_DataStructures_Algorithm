package com.faithfulolaleru.ForUoPeople.CS1103.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    private static final int PORT = 12345;
    private static Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private static int clientCount = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server started on port " + PORT);
            System.out.println("Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                String clientId = "User" + clientCount;

                System.out.println("New client connected: " + clientId + " from " + clientSocket.getInetAddress().getHostAddress());

                // Create a new handler thread for this client
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientId);
                // clients.put(clientId, clientHandler);

                // Start the client handler thread
                new Thread(clientHandler).start();

                // Broadcast the new user joined message
                broadcast(clientId + " has joined the chat!", null);
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    // send message to all connected clients except the sender.
    public static void broadcast(String message, String senderId) {
        for (Map.Entry<String, ClientHandler> entry : clients.entrySet()) {
            if (!entry.getKey().equals(senderId)) {
                entry.getValue().sendMessage(message);
            }
        }
    }

    public static void removeClient(String clientId) {
        clients.remove(clientId);
        broadcast(clientId + " has left the chat!", null);
        System.out.println(clientId + " has disconnected");
    }

    public static void addClient(String clientId, ClientHandler clientHandler) {
        clients.put(clientId, clientHandler);
        broadcast("SERVER: " + clientHandler.getDisplayName() + " has joined the chat!", null);
        System.out.println(clientHandler.getDisplayName() + " has connected from " +
                clientHandler.getClientAddress());
    }


    //  handle communnication with a single client
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private String clientId;
        private String username;
        private String displayName;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket, String clientId) {
            this.socket = socket;
            this.clientId = clientId;
            try {
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Send welcome message to the client
                // this.sendMessage("Welcome to the chat, " + clientId + "!");
                // this.sendMessage("Type '/quit' to exit the chat.");
            } catch (IOException e) {
                System.out.println("Error setting up streams: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                // First message from client should be their username
                username = in.readLine();
                if (username == null || username.trim().isEmpty()) {
                    username = "Anonymous";
                }
                displayName = username + "(" + clientId + ")";

                // Add client to the map
                addClient(clientId, this);

                // Send welcome message to the client
                sendMessage("SERVER: Welcome to the chat, " + displayName + "!");
                sendMessage("SERVER: Type '/quit' to exit the chat.");

                // Process messages from the client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/quit")) {
                        break;
                    }
                    System.out.println(displayName + ": " + message);
                    broadcast(displayName + ": " + message, clientId);
                }
            } catch (IOException e) {
                System.out.println("Error reading from client: " + e.getMessage());
            } finally {
                closeConnection();
            }
        }

        public String getDisplayName() {
            return displayName;
        }

        public String getClientAddress() {
            return socket.getInetAddress().getHostAddress();
        }


        public void sendMessage(String message) {
            out.println(message);
        }

        private void closeConnection() {
            try {
                removeClient(clientId);
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
