/**
 * This thread will call the passed in NextOddEven object which will handle the request sent to it
 */
public class LocalThread extends Thread{

    private RuntimeThread runtimeThread;
    private Request request;
    private NextOddEven nxtOddEven;

    LocalThread(RuntimeThread runtimeThread, Request request, NextOddEven nxtOddEven){
        this.runtimeThread = runtimeThread;
        this.request = request;
        this.nxtOddEven = nxtOddEven;
    }
    public void run(){
        try {
            runtimeThread.addResponse(new Response(request, nxtOddEven.handleRequest(request)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
