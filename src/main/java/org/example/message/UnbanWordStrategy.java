package org.example.message;

import org.example.ClientHandler;
import org.example.util.Filter;

public class UnbanWordStrategy implements IMessageStrategy {
    @Override
    public void execute(String message, ClientHandler client) {
        String word = message.replace("#UNBANWORD ", "");
        Filter.removeBannedWord(word);
        client.notify("Word has been unbanned: " + word);
    }
}
