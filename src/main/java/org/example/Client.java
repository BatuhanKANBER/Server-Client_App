package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        InetAddress address = InetAddress.getLocalHost();
        Socket socket = null;
        String line = null;
        String response = null;
        BufferedReader bufferedReaderIn = null;
        BufferedReader reader = null;
        PrintWriter writer = null;

        try {

            socket = new Socket(address, Server.PORT);
            bufferedReaderIn = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException exception) {

            System.err.print(exception.getMessage());
        }

        System.out.println("Client address : " + address + "/" + socket.getLocalPort());
        System.out.println("Enter 'EXIT' to end:");
        try {

            line = bufferedReaderIn.readLine();
            while (!line.equals("EXIT")) {

                writer.println(line);
                writer.flush();
                response = reader.readLine();
                System.out.println("Server Response : " + response);
                line = bufferedReaderIn.readLine();
            }
        } catch (IOException exception) {

            System.err.println(exception.getMessage() + ": SOCKET READ ERROR");
        } finally {

            reader.close();
            writer.close();
            bufferedReaderIn.close();
            socket.close();
            System.out.println("Connection Closed");
        }
    }
}

