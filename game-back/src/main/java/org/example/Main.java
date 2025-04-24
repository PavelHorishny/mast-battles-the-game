package org.example;

import org.example.server.Server;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("web socket server added");
        Server server = new Server(8080);
        server.start();
    }
}