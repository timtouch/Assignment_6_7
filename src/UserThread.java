/**
 * Created by ttouc on 5/3/2017.
 */
public class UserThread extends Thread{
    int numIterations = 20;
    String[] commands = {"nextEvenFib", "nextPrime", "nextRandom", "nextEven", "nextOdd"};
    RuntimeThread runtimeThr;

    UserThread(){
        runtimeThr = new RuntimeThread();
    }

    public void run(){
        for (int i = 0; i < numIterations; i++) {
            runtimeThr.addRequest(commands[(int)(Math.random()*5)]);
        }

        for (int i = 0; i < numIterations; i++) {
            System.out.println(runtimeThr.removeRequest());
        }
    }

}
