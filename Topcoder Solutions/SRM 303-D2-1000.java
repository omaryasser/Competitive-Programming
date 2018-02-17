import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by omar on 25/07/17.
 */
public class PrimePalindromic {
    public int count(int A, int B) {
        int res = 0;
        for (int num = A; num <= B; num++) {
            int[] div = new int[10000];
            int max = 0;
            int tmp = num;
            for (int i = 2; tmp > 1; i++) {
                while (tmp % i == 0) {
                    tmp /= i;
                    div[i]++;
                    max = Math.max(max, i);
                }
            }
            ArrayList<Integer> primes = new ArrayList<>();
            for (int i = 0; i <= max; i++)
                if (div[i] > 0) {
                    if (i < 10 || i == 11) {
                        if (div[i] % 2 == 1) primes.add(i);
                        else {primes.add(i); primes.add(i);}
                    }
                    else {
                        for (int j = 0; j < div[i]; j++)
                            primes.add(i);
                    }
                }
            res += can(primes) ? 1 : 0;
        }
        return res;
    }
    boolean can (ArrayList<Integer> primes) {
        int[] p = new int[primes.size()];
        for (int i = 0; i < primes.size(); i++) p[i] = primes.get(i);
        Arrays.sort(p);
        boolean ok = false;
        do {
            ok |= ok(p);
            if (ok) break;
        } while (next_permutation(p));
        return ok;
    }

    boolean ok (int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int num : a) sb.append(num);
        return sb.toString().equals(sb.reverse().toString());
    }
    boolean next_permutation(int[] A) {
        for (int a = A.length - 2; a >= 0; --a) {
            if (A[a] < A[a + 1]) {
                for (int b = A.length - 1;; --b) {
                    if (A[b] > A[a]) {
                        int t = A[a];
                        A[a] = A[b];
                        A[b] = t;
                        for (++a, b = A.length - 1; a < b; ++a, --b) {
                            t = A[a];
                            A[a] = A[b];
                            A[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
