package org.example.message;

import org.example.ClientHandler;

import java.util.HashMap;
import java.util.Map;

public class MessageStrategyFactory {
    private static Map<String, IMessageStrategy> strategies = new HashMap();

    static {
        strategies.put("#JOIN", new JoinStrategy());
        strategies.put("#MESSAGE", new MessageStrategy());
        strategies.put("#COLOREDMESSAGE", new ColoredMessageStrategy());
        strategies.put("#LEAVE", new LeaveStrategy());
        strategies.put("#PRIVATE", new PrivateStrategy());
        strategies.put("#GETLIST", new GetListStrategy());
        strategies.put("#PRIVATESUBLIST", new PrivateSublistStrategy());
        strategies.put("#HELP", new HelpStrategy());
        strategies.put("#STOPSERVER", new StopServerStrategy());
        strategies.put("#BANWORD", new BanWordStrategy());
        strategies.put("#UNBANWORD", new UnbanWordStrategy());
        strategies.put("#GETBANNEDWORDS", new GetBannedWordsStrategy());
    }

    public static IMessageStrategy getStrategy(String strategy) {
        return strategies.getOrDefault(strategy, new IMessageStrategy() {
            @Override
            public void execute(String message, ClientHandler client) {
                client.notify("Invalid command: " + message);
            }
        });
    }
}
