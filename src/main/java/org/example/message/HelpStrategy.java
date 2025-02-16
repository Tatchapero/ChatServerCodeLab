package org.example.message;

import org.example.ClientHandler;

public class HelpStrategy implements IMessageStrategy {
    @Override
    public void execute(String message, ClientHandler client) {
        client.notify("""
                #JOIN <nickname> - A client joins the chat with a nickname.\r
                #MESSAGE <message> - A client sends a message to all other clients.\r
                #LEAVE - A client leaves the chat.\r
                #PRIVATE <nickname> <message>   - A client sends a private message to another client.\r
                #GETLIST - A client requests a list of all active clients.\r
                #PRIVATESUBLIST "nickname1,nickname2,nickname3" <message> - A client sends a private message to a list of clients.\r
                #HELP - A client requests a list of available commands.\r
                #STOPSERVER - A client requests to stop the server (all clients are first notified, then disconnected, removed from the list and all closable resources are closed).\r
                """);
    }
}
