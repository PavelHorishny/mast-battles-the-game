package org.example.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server extends WebSocketServer {
    public Server (int port) {
        super(new InetSocketAddress(port));
    }

/*    public Server (InetSocketAddress address) {
        super(address);
    }

    public Server (int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.singletonList(draft));
    }*/
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
        webSocket.send("Message received: " + s);
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
