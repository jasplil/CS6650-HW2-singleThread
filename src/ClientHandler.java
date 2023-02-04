import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class creates a thread using runnable interface
 */
public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(client.getOutputStream());
    }

    /**
     * Read data from client
     */
    @Override
    public void run() {
        String str = null;
        try {
            str = in.readLine();
            System.out.println("client sent: " + str);
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            printWriter.println("This is from server");
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
