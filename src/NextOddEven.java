import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ttouc on 5/9/2017.
 */
public class NextOddEven {
    private int odd = -1;
    private int even = -2;

    private ReentrantLock evenLock = new ReentrantLock();
    private ReentrantLock oddLock = new ReentrantLock();

    public int handleRequest(Request request){
        int result;
        switch (request) {
            case NEXTEVEN:
                evenLock.lock();
                try {
                    result = nextEven();
                } finally {
                    evenLock.unlock();
                }
                break;
            case NEXTODD:
                oddLock.lock();
                try {
                    result = nextOdd();
                } finally {
                    oddLock.unlock();
                }
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    private int nextOdd()
    {
        try {
            odd = Math.addExact(odd, 2);
        } catch (ArithmeticException e){
            odd = 1;
        }

        return odd;
    }

    private int nextEven()
    {
        try {
            even = Math.addExact(even, 2);
        } catch (ArithmeticException e) {
            even = 0;
        }

        return even;
    }
}
