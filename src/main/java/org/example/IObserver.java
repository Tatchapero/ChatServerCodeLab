package org.example;

public interface IObserver {
    void notify(String message);
    void leave();
    String getName();
}
