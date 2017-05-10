import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkThread extends Thread{

    private int userID;
    private String name;
    private String IPAddress;
    private Socket socket;
    private int portNumber;
    private BufferedReader bufferedReaderFromClient;
    private PrintWriter printWriter;

    private RuntimeThread runtimeThread;
    private Request request;

    NetworkThread (RuntimeThread runtimeThread, int userID, String IPAddress, Request request) {
        this.runtimeThread = runtimeThread;
        this.userID = userID;
        this.IPAddress = IPAddress;
        this.request = request;
    }

    /**
     * Connects to server and requests for a specific value. Once it gets the value, put the result in the response queue
     */
    public void run() {
        try {
            name = String.format("User %d", userID);
            portNumber = 4444;
            socket = new Socket(IPAddress, portNumber);
            bufferedReaderFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(name);

            runtimeThread.addResponse(new Response(request, requestNumber(request)));
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

    /**
     * @param request to the server
     * @return result of the request
     * @throws IOException
     */
    private int requestNumber(Request request) throws IOException {
        printWriter.println(request.toString());
        return Integer.parseInt(bufferedReaderFromClient.readLine());
    }
}
