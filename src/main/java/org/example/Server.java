package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    static final int PORT = 1234;

    public static void main(String[] args) {

        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Connection Successfully...");
        } catch (IOException exception) {
            System.err.println(exception.getMessage() + ": SERVER ERROR!");
        }

        while (true) {
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
                System.out.println("Client "+clientHandler.getName()+" Connection Successfully...");
            } catch (Exception exception) {
                System.err.println(exception.getMessage() + ": CONNECTION ERROR!");
            }
        }
    }
}
