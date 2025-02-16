package org.example.message;

import org.example.ClientHandler;
import org.example.IObservable;

public class LeaveStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        IObservable server = client.getServer();
        client.leave();
        server.broadcast(client.getName() + " has left.");
    }
}
