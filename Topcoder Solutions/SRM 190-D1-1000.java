/**
 * Created by omar on 30/07/17.
 */
public class SquareFree {
    int MAX = 1000000;
    int[] moebius;
    void moebius_generator() {
        moebius = new int[MAX + 1];
        boolean isPrime[] = new boolean[MAX + 1];
        for (int i = 2; i <= MAX ; ++i) {
            moebius[i] = 1;
            isPrime[i] = true;
        }
        for (int i = 2 ; i <= MAX ; ++ i)
            if(isPrime[i]) {
                moebius[i] = - 1;
                for (int j = 2 * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                    moebius[j] = (j % (i * i) == 0) ? 0 : - moebius[j];
                }
            }
    }

    boolean ok (int check, int numberOfSquareFreeWanted) {
        int numOfSquareFree = check;
        for (int i = 2; 1L * i * i <= check; i++)
            numOfSquareFree -= (1L * -moebius[i] * (1L * check / (1L * i * i)));
        return numOfSquareFree >= numberOfSquareFreeWanted;
    }
    public int getNumber(int n) {
        moebius_generator();
        long lo = 0, hi = Integer.MAX_VALUE, best = hi;
        for (int cnt = 0; cnt <= 1000; cnt++) {
            long mid = lo + ((hi - lo) >> 1);
            if (ok ((int)mid, n)) {
                best = Math.min(best, mid);
                hi = Math.max(lo, mid - 1);
            } else {
                lo = Math.min(hi, mid + 1);
            }
        }
        return (int)best;
    }
}
