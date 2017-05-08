import java.util.concurrent.locks.ReentrantLock;

/**
 * Class to get the next even fibonacci, next larger random number, or next prime number
 * Created by ttouc on 4/10/2017.
 */

public class NextNumberProtocol {

    // Initialized values for calculations
    private int nextPrimeNumber = 1;
    private int prevFib = 0;
    private int nextEvenFibNumber = 0;
    private int nextLargerRandomNumber = 0;

    private ReentrantLock fibLock = new ReentrantLock();
    private ReentrantLock randLock = new ReentrantLock();
    private ReentrantLock primeLock = new ReentrantLock();

    public String processInput(String input)
    {
        String output;
        System.out.println(input);
        if(input.equalsIgnoreCase("nextEvenFib"))
        {
            fibLock.lock();
            try{
                output = String.format("%d", nextEvenFib());
            } finally {
                fibLock.unlock();
            }

        }
        else if(input.equalsIgnoreCase("nextLargerRand"))
        {
            randLock.lock();
            try {
                output = String.format("%d", nextLargerRand());
            } finally {
                randLock.unlock();
            }


        }
        else if(input.equalsIgnoreCase("nextPrime"))
        {
            primeLock.lock();
            try {
                output = String.format("%d",  nextPrime());
            } finally {
                primeLock.unlock();
            }
        }
        else
        {
            output = "Not a valid input.  The valid inputs are \"nextEvenFib\", \"nextLargerRand\", & \"nextPrime\" ";
        }
        return output;
    }

    /**
     * Gets the next even fibonacci number
     * Starts over from 0 once it reach past the max signed integer number
     * @return  the next even fibonacci number
     */
    public int nextEvenFib()
    {
        int temp;

        // A base for fib numbers
        if (nextEvenFibNumber == 0){
            nextEvenFibNumber = 1;
            return 0;
        }
        do {
            temp = nextEvenFibNumber;
            try {
                nextEvenFibNumber = Math.addExact(nextEvenFibNumber, prevFib);
                prevFib = temp;
            } catch (ArithmeticException e){
                nextEvenFibNumber = 1;
                prevFib = 0;
                return 0;
            }

        } while (!(nextEvenFibNumber % 2 == 0));

        return nextEvenFibNumber;
    }

    /**
     * Gets the next larger random integer, if it hits the max, it wraps back around
     * Starts over from 0 once it reach past the max signed integer number
     * @return          a somewhat random number larger than the previous one
     */
    public int nextLargerRand()
    {
        try {
            nextLargerRandomNumber = Math.addExact(nextLargerRandomNumber, (int)Math.abs(Math.random()*1000));
        } catch (ArithmeticException e) {
            nextLargerRandomNumber = Math.addExact(0, (int)Math.abs(Math.random()*Integer.MAX_VALUE));
        }
        return nextLargerRandomNumber;
    }


    /**
     * Gets the next prime number
     * Starts over from 2 (the smallest prime number) once it reach past the max signed integer number
     * @return          the next prime number
     */
    public int nextPrime()
    {
        try {
            nextPrimeNumber = Math.incrementExact(nextPrimeNumber);
            while (!isPrime(nextPrimeNumber)) {
                nextPrimeNumber = Math.incrementExact(nextPrimeNumber);
            }
        } catch (ArithmeticException e) {
            nextPrimeNumber = 2;
        }
        return nextPrimeNumber;
    }

    /**
     * Checks if number is prime
     * @param   number  to be checked if prime
     * @return          true if prime, false if not prime
     */
    public boolean isPrime(int number)
    {
        if (number < 2)
        {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0)
            {
                return false;
            }
        }
        return true;
    }


}