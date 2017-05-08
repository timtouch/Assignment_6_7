import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connects with the server, requests from the server, and receives from the server the result of the requests
 *
 * Created by ttouc on 5/3/2017.
 */
public class NetworkThread extends Thread{

    private String name;
    private String IPAddress;
    private Socket socket;
    private int portNumber;
    private BufferedReader bufferedReaderFromClient;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private RuntimeThread runtimeThread;
    private Request request;

    NetworkThread (RuntimeThread runtimeThread, Request request) {
        this.runtimeThread = runtimeThread;
        this.request = request;
    }

    public void run() {
        try {
            name = "Client 1";
            IPAddress = "localhost";
            portNumber = 4444;
            socket = new Socket(IPAddress, portNumber);
            bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(name);
            bufferedReader = new BufferedReader(new InputStreamReader((System.in)));

            requestNumber(request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void requestNumber(Request request) throws IOException {
        int result;
        printWriter.println(request.toString());
        result = Integer.parseInt(bufferedReaderFromClient.readLine());
        runtimeThread.addResponse(new Response(request, result));
    }
}
