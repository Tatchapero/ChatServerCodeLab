package org.example.message;

import org.example.ClientHandler;
import org.example.colorDecorator.ColorDecorator;
import org.example.util.Filter;

public class ColoredMessageStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        client.giveWarning(message);
        String coloredMessage = new ColorDecorator("\u001b[31m").decorate(Filter.message(message));
        client.getServer().broadcast("[GLOBAL] " + client.getName() + ": " + coloredMessage);
    }
}
