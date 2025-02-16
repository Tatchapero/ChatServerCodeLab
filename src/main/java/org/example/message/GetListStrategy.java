package org.example.message;

import org.example.ClientHandler;
import org.example.IObserver;

import java.util.List;

public class GetListStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        List<IObserver> clients = client.getServer().getClientHandlerList();

        for (IObserver observer : clients) {
            client.notify(observer.getName());
        }
    }
}
