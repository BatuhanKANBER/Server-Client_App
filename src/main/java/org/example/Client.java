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
        BufferedReader in = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        Process cmd = null;

        try {

            socket = new Socket(address, Server.PORT);
            in = new BufferedReader(new InputStreamReader(System.in));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

        } catch (IOException exception) {

            System.err.print(exception.getMessage());

        }

        System.out.println("Client address : " + address + "/" + socket.getLocalPort());
        System.out.println("Enter 'EXIT' to end:");

        try {
            do {

                line = in.readLine();
                cmdCommandExecute(line, cmd);
                serverResponse(writer, line, response, reader);

            } while (!line.equals("EXIT"));
        } catch (IOException exception) {

            System.err.println(exception.getMessage() + ": SOCKET READ ERROR");

        } finally {

            ioClose(in, reader, writer);
            socket.close();
            System.out.println("Connection Closed");

        }
    }

    //IO İSLEMLERİNİ KAPAMA
    public static void ioClose(BufferedReader in, BufferedReader reader, PrintWriter writer) throws IOException {

        reader.close();
        writer.close();
        in.close();

    }

    //CMD KOMUTLARINI ÇALIŞTIRAN METHOD
    public static void cmdCommandExecute(String line, Process cmd) throws IOException {

        cmd = Runtime.getRuntime().exec("cmd /c " + line);

    }

    //PARAMETRELERİ SERVERDAN DÖNDÜRME
    public static void serverResponse(PrintWriter writer, String line, String response, BufferedReader reader) throws IOException {
        writer.println(line);
        writer.flush();
        response = reader.readLine();
        System.out.println("Server Response : " + response);
    }
}

