import java.util.concurrent.locks.ReentrantLock;

/**
 * This class keeps track of and returns the next odd or even number when requested
 * In this implementation, each User Thread has their own LocalOddEvenThread class.
 * In other words, User Threads do not share the same instance of this class
 *
 * Created by ttouc on 5/3/2017.
 */
public class LocalOddEvenThread extends Thread{
    private static int odd = -1;
    private static int even = -2;

    private RuntimeThread runtimeThread;
    private Request request;
    private ReentrantLock evenLock = new ReentrantLock();
    private ReentrantLock oddLock = new ReentrantLock();

    LocalOddEvenThread(RuntimeThread runtimeThread, Request request){
        this.runtimeThread = runtimeThread;
        this.request = request;
    }

    public void run(){
        switch (request) {
            case NEXTEVEN:
                evenLock.lock();
                try {
                    runtimeThread.addResponse(new Response(request, nextEven()));
                } finally {
                    evenLock.unlock();
                }
                break;
            case NEXTODD:
                oddLock.lock();
                try {
                    runtimeThread.addResponse(new Response(request, nextOdd()));
                } finally {
                    oddLock.unlock();
                }
                break;
        }
    }

    private static int nextOdd()
    {
        try {
            odd = Math.addExact(odd, 2);
        } catch (ArithmeticException e){
            odd = 1;
        }

        return odd;
    }

    private static int nextEven()
    {
        try {
            even = Math.addExact(even, 2);
        } catch (ArithmeticException e) {
            even = 0;
        }

        return even;
    }
}
