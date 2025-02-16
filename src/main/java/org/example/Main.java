package org.example;

public class Main {
    public static void main(String[] args) {
        ChatServer server = ChatServer.getInstance();
        server.startServer(12345);
    }
}