/**
 * Created by ttouc on 4/10/2017.
 */

public class NextNumberProtocol {

    private int nextPrimeNumber = 1;
    private int prevFib = 1;
    private int nextEvenFibNumber = 1;
    private int nextLargerRandomNumber = 0;

    public String processInput(String input)
    {
        String output;
        System.out.println(input);
        if(input.equalsIgnoreCase("nextEvenFib"))
        {
            output = String.format("%d", nextEvenFib());
        }
        else if(input.equalsIgnoreCase("nextLargerRand"))
        {
            output = String.format("%d", nextLargerRand());
        }
        else if(input.equalsIgnoreCase("nextPrime"))
        {
            output = String.format("%d",  nextPrime());
        }
        else
        {
            output ="Not a valid input.  The valid inputs are \"nextevenfib\", \"nextLargerRand\", & \"nextPrime\" ";
        }
        return output;
    }

    /**
     * Gets the next even fibonacci number
     * @return  the next even fibonacci number
     */
    public int nextEvenFib()
    {
        int temp;
        do {
            temp = nextEvenFibNumber;
            nextEvenFibNumber = nextEvenFibNumber + prevFib;
            prevFib = temp;
        } while (!(nextEvenFibNumber % 2 == 0));

        return nextEvenFibNumber;
    }

    /**
     * Gets the next larger random integer, if it hits the max, it wraps back around
     * @return          a somewhat random number larger than the previous one
     */
    public int nextLargerRand()
    {
        return nextLargerRandomNumber = (nextLargerRandomNumber + (int)Math.abs(Math.random()*1000)) % Integer.MAX_VALUE;
    }


    /**
     * Gets the next prime number
     * @return          the next prime number
     */
    public int nextPrime()
    {
        nextPrimeNumber = nextPrimeNumber + 1;
        while(!isPrime(nextPrimeNumber))
        {
            nextPrimeNumber++;
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