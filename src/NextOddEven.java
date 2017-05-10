import java.util.concurrent.locks.ReentrantLock;

/**
 * This class calculates the next odd or even number, both independent of one another
 * Each UserThread has their own odd and even numbers to keep track of
 */
public class NextOddEven {
    private int odd = -1;
    private int even = -2;

    // These locks are meant to keep
    private ReentrantLock evenLock = new ReentrantLock();
    private ReentrantLock oddLock = new ReentrantLock();

    /**
     * Handles requests brought to it
     * @param request   to be calculated
     * @return the result of the request or -1 if the request can't be handled by this class
     */
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

    /**
     * Generates the next odd number
     * @return the next odd number of this class, starts back at 1 if it reaches past the max integer value
     */
    private int nextOdd()
    {
        try {
            odd = Math.addExact(odd, 2);
        } catch (ArithmeticException e){
            odd = 1;
        }

        return odd;
    }

    /**
     * Generates the next even number
     * @return the next even number of this class, starts back at 0 if it reaches past the max integer value
     */
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