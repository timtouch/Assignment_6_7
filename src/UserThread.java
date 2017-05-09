/**
 * Requests for different random services and prints out the responses
 */
public class UserThread extends Thread{
    private int numOfRequests;                          // Number of request user thread will make
    private RuntimeThread runtimeThr;                   // Runtime thread class

    private int responses = 0;                          // Counts the responses that the user received

    UserThread(int numOfRequests) {
        this.numOfRequests = numOfRequests;
        runtimeThr = new RuntimeThread(numOfRequests);
    }

    public void run(){
        runtimeThr.start();
        for (int i = 0; i < numOfRequests; i++) {
            runtimeThr.addRequest(Request.NEXTEVEN);
        }

        while(responses < numOfRequests){
            Response res = runtimeThr.removeResponse();
            System.out.printf("User ID: %-3d sent request %-15s and received the value: %10d%n", getId(), res.getResponseFrom(), res.getValue());
            responses++;
        }
    }
}
