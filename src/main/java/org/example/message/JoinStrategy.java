package org.example.message;

import org.example.ClientHandler;

public class JoinStrategy implements IMessageStrategy {

    @Override
    public void execute(String message, ClientHandler client) {
        if (message == null || message.isEmpty() || message.isBlank()) {
            client.notify("Invalid username");
            return;
        }

        if (message.trim().contains(" ")) {
            client.notify("Name cannot contain spaces.");
            return;
        }

        client.setName(message);
        client.getServer().broadcast("Username has been changed to: " + message);
    }
}
