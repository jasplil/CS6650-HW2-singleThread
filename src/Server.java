import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket client;
    private static int PORT = 32000;

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
        serverSocket = new ServerSocket(PORT);
        client = serverSocket.accept();
        System.out.println("Listening to port " + PORT);

        /**
         * Create single thread
         */
        Thread t = new Thread(new ClientHandler(client));
        t.start();
    }
}
