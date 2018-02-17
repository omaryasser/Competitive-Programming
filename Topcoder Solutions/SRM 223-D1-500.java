import java.util.Arrays;

/**
 * Created by omar on 25/07/17.
 */
public class PrimeAnagrams {
    int[] a, best = new int[3], res = new int[3];
    int n;
    long bestProduct = -1;
    boolean[] taken;
    void search (int finished) {
        if (finished == 3) {
            boolean allTaken = true;
            for (boolean t : taken) allTaken &= t;
            if (!allTaken) return;
            if (bestProduct == -1 || 1L * res[0] * res[1] * res[2] < bestProduct) {
                bestProduct = res[0] * res[1] * res[2];
                best[0] = res[0]; best[1] = res[1]; best[2] = res[2];
            }
        } else {
            if (prime(res[finished])) search(finished + 1);
            for (int i = 0; i < n; i++) {
                if (!taken[i]) {
                    if (a[i] != 0 || res[finished] != 0) {
                        taken[i] = true;
                        int tmp = res[finished];
                        res[finished] = res[finished] * 10 + a[i];
                        search(finished);
                        res[finished] = tmp;
                        taken[i] = false;
                    }
                }
            }
        }
    }

    boolean prime (int num) {
        if (num == 0 || num == 1) return false;
        for (int i = 2; 1L * i * i <= num; i++)
            if (num % i == 0) return false;
        return true;
    }
    public int[] primes(String s) {
        n = s.length();
        a = new int[n];
        taken = new boolean[n];
        for (int i = 0; i < n; i++)
            a[i] = s.charAt(i) - '0';
        search(0);
        if (best[0] == 0) return new int[]{};
        else {
            Arrays.sort(best);
            return best;
        }
    }
}
