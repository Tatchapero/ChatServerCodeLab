package org.example.message;

import org.example.ClientHandler;
import org.example.IObserver;
import org.example.util.Filter;

public class PrivateStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        String[] messageArray = message.split(" ");
        String nickname = messageArray[0]; // Doesn't work for names with spaces
        String privateMessage = message.substring(nickname.length() + 1);
        client.giveWarning(privateMessage);

        for (IObserver observer : client.getServer().getClientHandlerList()) {
            if (observer.getName().equals(nickname)) {
                observer.notify("[PRIVATE] " + client.getName() + ": " + Filter.message(privateMessage));
            }
        }
    }
}
