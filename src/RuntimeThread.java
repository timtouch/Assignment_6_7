import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * Created by ttouc on 5/3/2017.
 */

public class RuntimeThread extends Thread {

    ConcurrentLinkedQueue requestQueue = new ConcurrentLinkedQueue();
    ConcurrentLinkedQueue responseQueue = new ConcurrentLinkedQueue();

    LocalOddEvenThread localThr;
    NetworkThread networkThr;


    void addRequest(String request){
        requestQueue.add(request);
    }

    String removeRequest(){
        return requestQueue.poll().toString();
    }

    void addResponse(String response){
        responseQueue.add(response);
    }

    String removeResponse(){
        return responseQueue.poll().toString();
    }
}
