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
        BufferedReader serverResponse = null;
        PrintWriter outPutStream = null;

        try {

            socket = new Socket(address, Server.PORT);
            bufferedReaderIn = new BufferedReader(new InputStreamReader(System.in));
            serverResponse = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outPutStream = new PrintWriter(socket.getOutputStream());
        } catch (IOException exception) {

            System.err.print(exception.getMessage());
        }

        System.out.println("Client address : " + address + "/" + socket.getLocalPort());
        System.out.println("Enter 'EXIT' to end:");
        try {

            line = bufferedReaderIn.readLine();
            while (!line.equals("EXIT")) {

                outPutStream.println(line);
                outPutStream.flush();
                response = serverResponse.readLine();
                System.out.println("Server Response : " + response);
                line = bufferedReaderIn.readLine();
            }
        } catch (IOException exception) {

            System.err.println(exception.getMessage() + ": SOCKET READ ERROR");
        } finally {

            serverResponse.close();
            outPutStream.close();
            bufferedReaderIn.close();
            socket.close();
            System.out.println("Connection Closed");
        }
    }
}

