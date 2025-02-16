package org.example.message;

import org.example.ClientHandler;
import org.example.util.Filter;

public class GetBannedWordsStrategy implements IMessageStrategy {
    @Override
    public void execute(String message, ClientHandler client) {
        for (String word : Filter.getBannedWords()) {
            client.notify(word);
        }
    }
}
