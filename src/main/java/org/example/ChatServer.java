package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements IObservable {
    private static volatile ChatServer server;
    private ServerSocket serverSocket;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private final List<IObserver> clientHandlerList = new ArrayList<>();
    private boolean isDone = false;

    private ChatServer() {}

    // Double-checked locking
    public static ChatServer getInstance() {
        ChatServer result = server;

        if (result == null) {
            synchronized (ChatServer.class) {
                if (server == null) {
                    ChatServer.server = new ChatServer();
                    return ChatServer.server;
                }
            }
        }
        return result;
    }

    public void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);

            while (!isDone) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandler.setOut(new PrintWriter(clientSocket.getOutputStream(), true));
                clientHandler.setIn(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
                addObserver(clientHandler);
                threadPool.submit(clientHandler);
            }
        }
        catch (SocketException ex) {
            // Ignore
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addObserver(IObserver observer) {
        this.clientHandlerList.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.clientHandlerList.remove(observer);
    }

    @Override
    public void broadcast(String message) {
        for (IObserver ch : clientHandlerList) {
            ch.notify(message);
        }
    }

    @Override
    public List<IObserver> getClientHandlerList() {
        return this.clientHandlerList;
    }

    @Override
    public void stopServer() {
        this.isDone = true;

        try {
            threadPool.shutdown();
            serverSocket.close();
            System.out.println("Server has been closed.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
