/**
 * Created by ttouc on 5/3/2017.
 */
public class LocalOddEvenThread {
    private int odd = 1;
    private int even = 0;

    public int nextOdd()
    {
        odd = odd + 2;
        return odd;
    }

    public int nextEven()
    {
        even = even + 2;
        return even;
    }


}
