import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * Created by ttouc on 5/3/2017.
 */

public class RuntimeThread extends Thread {

    private ConcurrentLinkedQueue<String> requestQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Integer> responseQueue = new ConcurrentLinkedQueue();

    private LocalOddEvenThread localThr = new LocalOddEvenThread();
    private NetworkThread networkThr;

    public void run() {
        while(true){
            if(!requestQueue.isEmpty()){
                handleRequest();
            }
        }
    }

    /***
     * Handles the next request on the request queue
     */
    void handleRequest(){
        switch (removeRequest()){
            case "nextOdd":
                addResponse(localThr.nextOdd());
                break;
            case "nextEven":
                addResponse(localThr.nextEven());
                break;
            default:
                break;
        }
    }

    public boolean hasResponse(){
        return !responseQueue.isEmpty();
    }

    void addRequest(String request){
        requestQueue.add(request);
    }

    String removeRequest(){
        return requestQueue.poll().toString();
    }

    void addResponse(int response){
        responseQueue.add(response);
    }

    String removeResponse(){
        return responseQueue.poll().toString();
    }
}
