package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    String line = null;
    BufferedReader serverResponse = null;
    PrintWriter outPutStream = null;
    Socket socket = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            serverResponse = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outPutStream = new PrintWriter(socket.getOutputStream());
        } catch (IOException exception) {

            System.out.println(exception.getMessage());
        }

        try {
            line = serverResponse.readLine();
            while (!line.equals("EXIT")) {

                outPutStream.println(line);
                outPutStream.flush();
                System.out.println("Response to Client  :  " + line);
                line = serverResponse.readLine();
            }
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
}
