import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages the requests from the request queue and responses in the response queue
 * Created by ttouc on 5/3/2017.
 */

public class RuntimeThread extends Thread {

    private ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Response> responseQueue = new ConcurrentLinkedQueue();

    private boolean hasMoreRequests = true;

    public void run()  {
        try {
            while (hasMoreRequests) {
                if (!requestQueue.isEmpty()) {
                    handleRequest();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Handles the next request on the request queue
     */
    private void handleRequest() throws IOException{
        Request request = removeRequest();
        switch (request){
            case NEXTODD:
            case NEXTEVEN:
                new LocalOddEvenThread(this, request).start();
                break;
            case NEXTPRIME:
            case NEXTEVENFIB:
            case NEXTLARGERRAND:
                new NetworkThread(this, request).start();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    public void finishedAllRequests() {
        hasMoreRequests = false;
    }

    public boolean hasResponse(){
        return !responseQueue.isEmpty();
    }

    public void addRequest(Request request){
        requestQueue.add(request);
    }

    public Request removeRequest(){
        return requestQueue.poll();
    }

    public void addResponse(Response response){
        responseQueue.add(response);
    }

    public Response removeResponse(){
        return responseQueue.poll();
    }
}
