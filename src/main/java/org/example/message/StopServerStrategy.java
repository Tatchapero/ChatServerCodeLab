package org.example.message;

import org.example.ClientHandler;
import org.example.IObservable;
import org.example.IObserver;

import java.util.List;

public class StopServerStrategy implements IMessageStrategy{
    @Override
    public void execute(String message, ClientHandler client) {
        List<IObserver> clients = client.getServer().getClientHandlerList();
        IObservable server = client.getServer();
        server.broadcast("Server is shutting down...");

        while (!clients.isEmpty()) {
            IObserver observer = clients.get(0);
            String name = observer.getName();
            observer.leave();
            server.broadcast(name + " has left.");
        }

        server.stopServer();
    }
}
