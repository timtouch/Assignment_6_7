/**
 * Created by ttouc on 5/9/2017.
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
        runtimeThread.addResponse(new Response(request, nxtOddEven.handleRequest(request)));
    }
}
