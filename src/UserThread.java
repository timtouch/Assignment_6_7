/**
 * Requests for different random services and prints out the responses
 * Created by ttouc on 5/3/2017.
 */
public class UserThread extends Thread{
    int numIterations = 20;
    String[] commands = { "nextEven", "nextOdd"};//, "nextEvenFib", "nextPrime", "nextLargerRand"}; // For a smaller set
    RuntimeThread runtimeThr;

    UserThread(){
        runtimeThr = new RuntimeThread();
    }

    public void run(){
        runtimeThr.start();
        for (int i = 0; i < numIterations; i++) {
            String request = commands[(int)(Math.random()*commands.length)]; // Choose randomly from a set of requests
            runtimeThr.addRequest(request);
        }

        while(true){
            if(runtimeThr.hasResponse()){
                System.out.println(runtimeThr.removeResponse());
            }
        }
    }

}
