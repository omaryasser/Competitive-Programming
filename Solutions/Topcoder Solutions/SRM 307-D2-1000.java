import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Created by omar on 25/07/17.
 */
public class PreprimeNumbers {
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
    public int nthNumber(int n) {
        seive(10000000);
        ArrayList<Integer> treeSet = new ArrayList<>();
        main : for (int i = 0; i < primes.size(); i++) {
            for (int j = i + 1; j < primes.size(); j++) {
                if (1L * primes.get(i) * primes.get(j) < 7e6)
                    treeSet.add(primes.get(i) * primes.get(j));
                else break;
            }
        }
        for (int i = 0; i < primes.size(); i++) {
            if (1L * primes.get(i) * primes.get(i) * primes.get(i) < 7e6) {
                treeSet.add(primes.get(i) * primes.get(i) * primes.get(i));
            }
            else break;
        }
        Collections.sort(treeSet);
        return treeSet.get(n - 1);
    }
}
