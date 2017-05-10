/**
 * Requests for different random services and prints out the responses
 */
public class UserThread extends Thread{
    private int userID;                                 // ID to differentiate from other UserThreads
    private int numOfRequests;                          // Number of request user thread will make
    private RuntimeThread runtimeThr;

    private int responses = 0;                          // Counts the responses that the user received

    UserThread(int userID, int numOfRequests, RuntimeThread runtimeThr) {
        this.userID = userID;
        this.numOfRequests = numOfRequests;
        this.runtimeThr = runtimeThr;
    }

    public void run(){
        runtimeThr.start();

        // Puts in a designated number of random requests
        for (int i = 0; i < numOfRequests; i++) {
            try {
                runtimeThr.addRequest(Request.getRandomRequest());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // While there are still requests that haven't been responded to
        while(responses < numOfRequests){
            try {
                Response res = runtimeThr.removeResponse();
                System.out.printf("User ID: %-3d sent request %-15s and received the value: %10d%n", userID, res.getResponseFrom(), res.getValue());
                responses++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        runtimeThr.finishedAllRequests(); // Signal RuntimeThread that this UserThread is finished
    }
}
