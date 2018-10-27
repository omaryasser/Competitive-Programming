import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by omar on 25/07/17.
 */
public class EngineersPrimes {
    public static ArrayList<Integer> primes;
    public static void seive (int n)
    {
        boolean primess [] = new boolean[n+1];
        Arrays.fill(primess, true);
        primess[0]=primess[1]=false;

        for(int i =0 ; i*i<=n; i++)
            if(primess[i])
                for(int j =2 ;i*j <=n ; j++)
                    primess [i*j]=false;

        primes = new ArrayList<>();
        for (int i = 2 ; i < n + 1 ; ++i)
            if (primess[i])
                primes.add(i);
    }
    public long smallestNonPrime(int N) {
        seive((int)10000000);
        return 1L * primes.get(N) * primes.get(N);
    }
}
