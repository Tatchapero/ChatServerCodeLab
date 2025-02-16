package org.example;

import org.example.message.MessageStrategyFactory;
import org.example.util.Filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler implements Runnable, IObserver {
    private Socket clientSocket;
    private IObservable server;
    private PrintWriter out;
    private BufferedReader in;
    private String name = "Guest";
    private int warnings = 0;

    public ClientHandler(Socket clientSocket, IObservable server) throws IOException {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void notify(String message) {
        out.println(message);
    }

    @Override
    public void run() {
        String command;
        String message;
        String restMessage;

        try {
            notify("Connection established");

            while ((message = in.readLine()) != null) {
                String[] messageArray = message.split(" ");
                command = messageArray[0];
                restMessage = messageArray.length > 1 ? message.replace(command, "").trim() : message;
                MessageStrategyFactory.getStrategy(command).execute(restMessage, this);

                if (warnings > 2) {
                    notify("You have been banned");
                    leave();
                }
                else if (message.equals("#STOPSERVER")) {
                    break;
                }
                message = null;
            }
        }
        catch (SocketException ex) {
            // Ignore
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void leave() {
        try {
            clientSocket.close();
            in.close();
            out.close();
            server.removeObserver(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void giveWarning(String message) {
        if (Filter.hasBannedWord(message)) {
            this.warnings++;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PrintWriter getOut() {
        return this.out;
    }

    public IObservable getServer() {
        return this.server;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
