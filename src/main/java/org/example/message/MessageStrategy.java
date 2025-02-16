package org.example.message;

import org.example.ClientHandler;
import org.example.util.Filter;

public class MessageStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        client.giveWarning(message);
        client.getServer().broadcast("[GLOBAL] " + client.getName() + ": " + Filter.message(message));
    }
}
