/**
 * This class keeps track of and returns the next odd or even number when requested
 * In this implementation, each User Thread has their own LocalOddEvenThread class.
 * In other words, User Threads do not share the same instance of this class
 *
 * Created by ttouc on 5/3/2017.
 */
public class LocalOddEvenThread {
    private int odd = -1;
    private int even = -2;

    public int nextOdd()
    {
        try {
            odd = Math.addExact(odd, 2);
        } catch (ArithmeticException e){
            odd = 1;
        }

        return odd;
    }

    public int nextEven()
    {
        try {
            even = Math.addExact(even, 2);
        } catch (ArithmeticException e) {
            even = 0;
        }

        return even;
    }
}
