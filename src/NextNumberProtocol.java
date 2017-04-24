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
        Max = lastNumber(Rand)+1;
        lastPrime = lastNumber(Prime);
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
        int temp = min;
        String Rand = "";
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        for (int i=0; i<5; i++)
        {
            int randomNum = rand.nextInt((1000 - temp)+1) + temp;
            Rand+=" " +Integer.toString(randomNum) ;
            if (temp <995)
            {
                temp = randomNum;
            }
            else
            {
                temp = 0;
            }
        }
        return Rand;
    }

    /**
     * @param s
     * @return
     */
    public int lastNumber(String s){
        int lastNumber=0;
        if(s.contains(" ")){
            int lastIndex = s.lastIndexOf(' ');
            s = s.substring(lastIndex+1);
            lastNumber = Integer.parseInt(s);
        }
        return lastNumber;
    }
}