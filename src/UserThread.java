import java.io.IOException;

/**
 * Requests for different random services and prints out the responses
 * Created by ttouc on 5/3/2017.
 */
public class UserThread extends Thread{
    private int numIterations = 20;
    private int responses = 0;
    private RuntimeThread runtimeThr;
    private final Request[] values = Request.values();

    UserThread() throws IOException {
        runtimeThr = new RuntimeThread();
    }

    public void run(){
        runtimeThr.start();
        for (int i = 0; i < numIterations; i++) {
            runtimeThr.addRequest(getRandomRequest());
        }

        while(responses < numIterations){
            if(runtimeThr.hasResponse()){
                System.out.println("User ID: " + getId() + " received response " + runtimeThr.removeResponse());
                responses++;
            }
        }
        return;
    }

    /**
     * @return a random request
     */
    public Request getRandomRequest(){
        return values[(int)(Math.random()*values.length)];
    }

}
