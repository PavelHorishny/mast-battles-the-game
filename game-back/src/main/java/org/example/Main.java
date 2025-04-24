package org.example;

import org.example.server.Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("game processor added added");
        Server server = new Server(8080);
        server.start();
    }
}