import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Manages the requests from the request queue and responses in the response queue
 * Created by ttouc on 5/3/2017.
 */

public class RuntimeThread extends Thread {

    private LinkedBlockingQueue<Request> requestQueue = new LinkedBlockingQueue();
    private LinkedBlockingQueue<Response> responseQueue = new LinkedBlockingQueue();

    private boolean hasMoreRequests = true;

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

    public Request removeRequest() throws InterruptedException{
        return requestQueue.take();
    }

    public void addResponse(Response response){
        responseQueue.add(response);
    }

    public Response removeResponse() throws InterruptedException{
        return responseQueue.take();
    }
}
