import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages the requests from the request queue and responses in the response queue
 * Created by ttouc on 5/3/2017.
 */

public class RuntimeThread extends Thread {

    private ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Integer> responseQueue = new ConcurrentLinkedQueue();

    private LocalOddEvenThread localThr = new LocalOddEvenThread();
    private NetworkThread networkThr;


    RuntimeThread() throws IOException {
        networkThr = new NetworkThread();
    }

    public void run()  {
        while(true){
            if(!requestQueue.isEmpty()){
                try {
                    handleRequest();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /***
     * Handles the next request on the request queue
     */
    void handleRequest() throws IOException{
        Request request = removeRequest();
        switch (request){
            case NEXTODD:
                addResponse(localThr.nextOdd());
                break;
            case NEXTEVEN:
                addResponse(localThr.nextEven());
                break;
            case NEXTPRIME:
            case NEXTEVENFIB:
            case NEXTLARGERRAND:
                addResponse(networkThr.requestNumber(request));
                break;
            default:
                break;
        }
    }

    public boolean hasResponse(){
        return !responseQueue.isEmpty();
    }

    void addRequest(Request request){
        requestQueue.add(request);
    }

    Request removeRequest(){
        return requestQueue.poll();
    }

    void addResponse(int response){
        responseQueue.add(response);
    }

    String removeResponse(){
        return responseQueue.poll().toString();
    }
}
