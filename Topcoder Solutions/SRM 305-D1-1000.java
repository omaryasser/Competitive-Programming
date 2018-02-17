import java.math.BigInteger;
import java.util.TreeMap;

/**
 * Created by omar on 26/07/17.
 */
public class PowerCollector {
    public String countPowers(String N) {
        long num = Long.parseLong(N);
        return solve(num, 60) + "";
    }

    TreeMap<Pair,Long> memo = new TreeMap<Pair, Long>();
    class Pair implements Comparable<Pair>{
        long x;
        int p;
        Pair (long xx, int pp) {
            x = xx;
            p = pp;
        }

        @Override
        public int compareTo(Pair pair) {
            return x != pair.x ? Long.compare(x, pair.x) : p - pair.p;
        }
    }
    long solve (long x, int maxP) {
        Long was = memo.get(new Pair(x, maxP));
        if (was != null) return was;
        long res = 0;
        for (int pow = 2; pow <= maxP; pow++) {
            if (!prime(pow)) {
                continue;
            }
            long num = sqrt(x, pow);
            res += num - solve(num, pow - 1);
        }
        memo.put(new Pair(x, maxP), res);
        return res;
    }
    long sqrt (long num, int pow) {
        long lo = 0, hi = num, best = 0;
        for (int cnt = 0; cnt <= 70; cnt++) {
            long mid = lo + ((hi - lo) >> 1);
            if (lessOrEqual(mid, pow, num)) {
                best = Math.max(best, mid);
                lo = Math.min(hi, mid + 1);
            } else {
                hi = Math.max(lo, mid - 1);
            }
        }
        return best;
    }

    boolean lessOrEqual (long num, int pow, long other) {
        BigInteger b = BigInteger.valueOf(num);
        for (int i = 2; i <= pow; i++) {
            b = b.multiply(BigInteger.valueOf(num));
            if (b.compareTo(BigInteger.valueOf(other)) > 0)
                return false;
        }
        return true;
    }
    boolean prime (int num) {
        for (int i = 2; i * i <= num; i++)
            if (num % i == 0) return false;
        return true;
    }
}
