import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Manages the requests from the request queue and responses in the response queue
 */

public class RuntimeThread extends Thread {

    private ConcurrentLinkedQueue<Request> requestQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<Response> responseQueue = new ConcurrentLinkedQueue();

    private ReentrantLock reqLock = new ReentrantLock();
    private ReentrantLock resLock = new ReentrantLock();

    private Condition notEmptyRequestQueue = reqLock.newCondition();
    private Condition notEmptyResponseQueue = resLock.newCondition();

    private boolean hasMoreRequests = true;

    private int totalUserRequests;
    private int requestCounter = 0;

    RuntimeThread (int totalUserRequests){
        this.totalUserRequests = totalUserRequests;
    }

    public void run()  {
        try {
            while (requestCounter < totalUserRequests) {
                handleRequest(removeRequest());
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
        reqLock.lock();
        try {
            requestQueue.add(request);
            notEmptyRequestQueue.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reqLock.unlock();
        }
    }

    public Request removeRequest(){
        reqLock.lock();
        try {
            while ( requestQueue.isEmpty()){
                notEmptyRequestQueue.await();
            }
            requestCounter++;
            return requestQueue.poll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            reqLock.unlock();
        }
        return null;
    }

    public void addResponse(Response response){
        resLock.lock();
        try {
            responseQueue.add(response);
            notEmptyResponseQueue.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resLock.unlock();
        }

    }

    public Response removeResponse(){
        resLock.lock();
        try {
            while (responseQueue.isEmpty()) {
                notEmptyResponseQueue.await();
            }
            return responseQueue.poll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resLock.unlock();
        }
        return null;
    }
}
