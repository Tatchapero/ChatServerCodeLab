package org.example;

import java.util.List;

public interface IObservable {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void broadcast(String message);
    List<IObserver> getClientHandlerList();
    void stopServer();
}
