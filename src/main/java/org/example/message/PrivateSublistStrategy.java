package org.example.message;

import org.example.ClientHandler;
import org.example.IObserver;
import org.example.util.Filter;

public class PrivateSublistStrategy implements IMessageStrategy {
    @Override
    public void execute(String message, ClientHandler client) {
        String[] messageArray = message.split("\"");
        String[] nicknames = messageArray[1].split(",");
        String privateMessage = messageArray[2].trim();
        client.giveWarning(privateMessage);

        for (IObserver observer : client.getServer().getClientHandlerList()) {
            for (String nickname : nicknames) {
                if (observer.getName().equals(nickname.trim())) {
                    observer.notify("[PRIVATE] " + client.getName() + ": " + Filter.message(privateMessage));
                }
            }
        }
    }
}
