/**
 * Requests for different random services and prints out the responses
 * Created by ttouc on 5/3/2017.
 */
public class UserThread extends Thread{
    private int numOfRequests;                          // Number of request user thread will make
    private RuntimeThread runtimeThr;                   // Runtime thread class

    private int responses = 0;                          // Counts the responses that the user received

    UserThread(int numOfRequests, RuntimeThread runtimeThr) {
        this.numOfRequests = numOfRequests;
        this.runtimeThr = runtimeThr;
    }

    public void run(){
        runtimeThr.start();
        for (int i = 0; i < numOfRequests; i++) {
            runtimeThr.addRequest(Request.getRandomRequest());
        }

        while(responses < numOfRequests){
            if(runtimeThr.hasResponse()){
                Response res = runtimeThr.removeResponse();
                System.out.printf("User ID: %-3d sent request %-15s amd received the value: %10d%n", getId(), res.getResponseFrom(), res.getValue());
                responses++;
            }
        }
        runtimeThr.finishedAllRequests(); // Signal runtime thread that we are finished
    }

    /**
     * @return a random request
     */


}
