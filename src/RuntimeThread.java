import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages the requests from the request queue and responses in the response queue
 */

public class RuntimeThread extends Thread {

    private int userID;
    private String serverIPAddress;
    private LinkedBlockingQueue<Request> requestQueue = new LinkedBlockingQueue();
    private LinkedBlockingQueue<Response> responseQueue = new LinkedBlockingQueue();
    private NextOddEven nextOddEven = new NextOddEven();
    private boolean hasMoreRequests = true;

    RuntimeThread (int userID, String serverIPAddress) {
        this.userID = userID;
        this.serverIPAddress = serverIPAddress;
    }
    public void run()  {
        try {
            while (hasMoreRequests) {
                if (!requestQueue.isEmpty()) {
                    handleRequest(removeRequest());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles the next request on the request queue
     */
    private void handleRequest(Request request) throws IOException{
        switch (request){
            case NEXTODD:
            case NEXTEVEN:
                new LocalThread(this, request, nextOddEven).start();
                break;
            case NEXTPRIME:
            case NEXTEVENFIB:
            case NEXTLARGERRAND:
                new NetworkThread(this, userID, serverIPAddress, request).start();
                break;
            default:
                break;
        }
    }

    /**
     *  Poison pill for the while loop in RuntimeThreads run() function
     */
    public void finishedAllRequests() {
        hasMoreRequests = false;
    }

    public void addRequest(Request request) throws InterruptedException{
        requestQueue.put(request);
    }

    public Request removeRequest() throws InterruptedException{
        return requestQueue.take();
    }

    public void addResponse(Response response) throws InterruptedException{
        responseQueue.put(response);
    }

    public Response removeResponse() throws InterruptedException{
        return responseQueue.take();
    }
}
