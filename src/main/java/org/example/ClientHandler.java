package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private String line = null;
    private BufferedReader reader = null;
    private PrintWriter writer = null;
    private Socket socket = null;

    public ClientHandler(Socket socket) {

        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException exception) {

            System.out.println(exception.getMessage());

        }

        try {
            do {

                responseToClient();

            } while (!line.equals("EXIT"));
        } catch (IOException exception) {

            System.err.println(exception.getMessage() + ": RESPONSE ERROR");

        } catch (NullPointerException exception) {

            System.out.println("Connection Closing...");

        } finally {
            try {

                System.out.println("Client " + this.getName() + " is disconnected.");

                if (socket != null) {

                    socket.close();
                    System.out.println("Socket is closed");

                }
            } catch (IOException exception) {

                System.err.println(exception.getMessage() + ": SOCKET CLOSE ERROR");

            }
        }
    }

    public void responseToClient() throws IOException {

        line = reader.readLine();
        writer.println(line);
        writer.flush();
        System.out.println("Response to Client  :  " + line);

    }
}
