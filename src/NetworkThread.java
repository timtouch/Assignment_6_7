import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connects with the server, requests from the server, and receives from the server
 *
 * Created by ttouc on 5/3/2017.
 */
public class NetworkThread {

    private String name;
    private String IPAddress;
    private Socket socket;
    private int portNumber;
    private BufferedReader bufferedReaderFromClient;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    NetworkThread () throws IOException{
        name = "Client 1";
        IPAddress = "localhost";
        portNumber = 4444;
        socket = new Socket(IPAddress, portNumber);
        bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(name);
        bufferedReader = new java.io.BufferedReader(new InputStreamReader((System.in)));
    }

    int requestNumber(Request request) throws IOException{
        int result;

        socket = new Socket(IPAddress, portNumber);
        printWriter.println(request.toString());
        result = Integer.parseInt(bufferedReaderFromClient.readLine());

        socket.close();
        return result;
    }
}
