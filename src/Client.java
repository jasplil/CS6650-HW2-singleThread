import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

/**
 * The Client class starts and contacts the server on a given IP address and PORT 32000.
 * The Client class will pass the server a string up to 80 characters in length.
 * The Client class will print the string upon receiving it and exit.
 */
public class Client {
    private static Socket socket;
    private static int PORT;
    private static String HOST = "localhost";

    public static void main(String[] args) throws IOException {
        /**
         * Get port from console
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the port number:");
        PORT = scanner.nextInt();
        if (PORT < 0) throw new IOException("Port number should be larger than 0");
        /**
         * Create socket connection
         */
        socket = new Socket(HOST, PORT);
        System.out.println("Listening to port " + socket.getPort());
        /**
         * Send data to server
         */
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("This is text to SERVER");
        printWriter.flush();

        /**
         * Read data from server
         */
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String str = br.readLine();

        System.out.println("server sent: " + str);
    }
}
