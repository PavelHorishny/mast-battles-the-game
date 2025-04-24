package org.example.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server (int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("Welcome to the external WebSocket API server!");

        System.out.println(
                webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " connected to server");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println(
                webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " disconnected from server");
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("Message from client: " + s);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Message message = gson.fromJson(s, Message.class);
        System.out.println("Message: " + message);
        switch (message.action) {
            case SELECT -> webSocket.send("Player selected");
            case NEW_GAME -> webSocket.send("New game started");
            case RESTORE -> webSocket.send("Game restored");
            case SETTINGS -> webSocket.send("Settings changed");
            case MOVEMENTS_START -> webSocket.send("Movements started");
            case MOVEMENTS_END -> webSocket.send("Movements ended");
            case END -> webSocket.send("Game ended");
            case REPAIR -> webSocket.send("Repair started");
            case HELP -> webSocket.send("Help requested");
            case SHOT -> webSocket.send("Shot fired");
        }
        //webSocket.send("Message received: " + message.action);

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
