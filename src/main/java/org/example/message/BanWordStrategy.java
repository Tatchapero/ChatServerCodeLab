package org.example.message;

import org.example.ClientHandler;
import org.example.util.Filter;

public class BanWordStrategy implements IMessageStrategy {
    @Override
    public void execute(String message, ClientHandler client) {
        String word = message.replace("#BANWORD ", "");
        Filter.addBannedWord(word);
        client.notify("Word has been banned: " + word);
    }
}
