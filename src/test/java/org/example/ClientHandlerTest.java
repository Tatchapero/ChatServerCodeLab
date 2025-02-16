package org.example;

import org.junit.jupiter.api.*;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientHandlerTest {
    private ClientHandler client;;
    private Socket socket;
    private ChatServer server;
    private StringWriter stringWriter;

    @BeforeEach
    void setUp() throws IOException {
        socket = mock(Socket.class);
        server = mock(ChatServer.class);
        client = new ClientHandler(socket, server);
        stringWriter = new StringWriter();
        client.setIn(new BufferedReader(new StringReader("")));
        client.setOut(new PrintWriter(stringWriter, true));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void notifyTest() {
        // Arrange
        String expected = "My message\r\n";

        // Act
        client.notify("My message");
        String actual = stringWriter.toString();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void runTest() {
        // Arrange
        String expected = "Connection established\r\n";

        // Act
        client.run();
        String actual = stringWriter.toString();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void leave() {
        // Arrange

        // Act
        Runnable actual = () -> client.leave();

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void giveWarningTest() {
        // Arrange

        // Act
        Runnable actual = () -> client.giveWarning("");

        // Assert
        assertDoesNotThrow(actual::run);
    }

    @Test
    void bannedTest() {
        // Arrange
        String expected = "Connection established\r\nYou have been banned\r\n";
        client.giveWarning("shit");
        client.giveWarning("shit");
        client.setIn(new BufferedReader(new StringReader("#MESSAGE shit")));

        // Act
        client.run();
        String actual = stringWriter.toString();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void joinTest() {
        // Arrange
        client.setIn(new BufferedReader(new StringReader("#JOIN Thomas")));

        // Act
        client.run();

        // Assert
        verify(server, times(1)).broadcast("Username has been changed to: Thomas");
    }

    @Test
    void messageTest() {
        // Arrange
        client.setIn(new BufferedReader(new StringReader("#MESSAGE Hello everyone.")));

        // Act
        client.run();

        // Assert
        verify(server, times(1)).broadcast("[GLOBAL] Guest: Hello everyone.");
    }

    @Test
    void leaveTest() {
        // Arrange
        client.setIn(new BufferedReader(new StringReader("#LEAVE")));

        // Act
        client.run();

        // Assert
        verify(server, times(1)).broadcast("Guest has left.");
    }
}