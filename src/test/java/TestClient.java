import org.example.Client;
import org.example.Server;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class TestClient {
    private Client mockClient;
    private Process mockCmd;
    private String mockLine;
    private StringWriter mockStringWriter;
    private PrintWriter mockWriter;
    private BufferedReader mockReader;

    @Before
    public void setUp() {
        mockStringWriter = new StringWriter();
        mockWriter = new PrintWriter(mockStringWriter);
        mockReader = new BufferedReader(new StringReader("response"));
    }

    @Test
    public void testCmdCommandExecute() {
        try {
            mockLine = "mkdir";
            Object result = mockClient.cmdCommandExecute(mockLine, mockCmd);
            assertNotNull(result);
            assertTrue(result instanceof Process);
        } catch (IOException exception) {
            exception.getMessage();
        }
    }

    @Test
    public void testServerResponse() {
        try {

            String line = "response";
            String response = mockClient.serverResponse(mockWriter, line, null, mockReader);

            assertNotNull(response);
            assertEquals("response", response);

        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
