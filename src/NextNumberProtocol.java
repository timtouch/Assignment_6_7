import java.util.Random;

public class NextNumberProtocol {
    private int x = 0;
    private int lastPrime = 0;
    private int Max = 0;

    public synchronized String handleInput(String message){
        System.out.println("Incoming client message: " + message);
        message += "5 values of nextEvenFib: ";
        for (int i =0;i<5;i++)
        {
            int a = i+x;
            message += " " + Integer.toString(nextEvenFib(a));
        }
        String Prime=nextPrime(lastPrime);
        message += "   5 values of nextPrime: " + Prime;
        String Rand=nextLargerRand(Max);
        message += "   5 values of LargeRand: " + Rand;
        //------------------

        if (Max<=100)
        {
            Max = maxNumber(Rand)+1;
        }
        else
        {
            Max = 0;
        }

        //--------------------
        if(Prime.contains(" ")){
            int lastIndex = Prime.lastIndexOf(' ');
            Prime = Prime.substring(lastIndex+1);
            lastPrime = Integer.parseInt(Prime);
        }
        x += 5;
        return message;
    }

    /**
     * Gives the next even fibonacci number
     * @param   n   last even fibonacci number
     * @return
     */
    public int nextEvenFib(int n)
    {
        if (n < 1)
            return n;
        if (n == 1)
            return 2;

        // calculation of Fn = 4*(Fn-1) + Fn-2
        return ((4*nextEvenFib(n-1)) + nextEvenFib(n-2));
    }

    /**
     * Gives the next prime number
     * @param   n   last prime number
     * @return
     */
    public String nextPrime(int n)
    {
        String Prime = "";
        int count = 0;

        for (int i = n+1; i<1000; i++) {

            boolean isPrimeNumber = true;
            // check to see if the number is prime
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }
            // print the number if prime
            if (isPrimeNumber && count<5 && i>=2) {
                Prime +=" " +Integer.toString(i);
                count++;
            }

        }
        return Prime;

    }

    /**
     * Gives the a larger random number
     * @param   min last random number
     * @return
     */
    public String nextLargerRand(int min)
    {
        Random rand = new Random(0);

        String Rand = "";
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        for (int i=0; i<5; i++)
        {
            int randomNum = rand.nextInt(100 - min) + min;
            Rand+=Integer.toString(randomNum) + " ";
        }
        return Rand;

    }

    /**
     * @param s
     * @return
     */
    public int maxNumber(String s){

        String[] testArray = s.split(" ");
        int max = Integer.MIN_VALUE, maxIndex = 0;

        for (int i = 0; i < testArray.length; i++) {
            if (Integer.parseInt(testArray[i]) > max) {
                max = Integer.parseInt(testArray[i]);
                maxIndex = i;
            }
        }
        return max;
    }
}