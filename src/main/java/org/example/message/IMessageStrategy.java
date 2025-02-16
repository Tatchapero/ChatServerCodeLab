package org.example.message;

import org.example.ClientHandler;

public interface IMessageStrategy {
    void execute(String message, ClientHandler client);
}
