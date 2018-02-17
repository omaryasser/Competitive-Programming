/**
 * Created by omar on 17/10/17.
 */
public class Divisibility {
    static int numDiv (int max, int div) {return max / div;}
    static long gcd (long a, long b) {return b == 0 ? a : gcd(b, a % b);}
    static long LCM (long a, long b) {return a * (b / gcd(a, b));}
    public int numDivisible(int L, int R, int[] a) {
        long res = 0;
        for (int mask = 1, sz = a.length; mask < 1 << sz; mask++) {
            int sign = (Integer.bitCount(mask) & 1) == 1 ? 1 : -1;
            long mult = 1;
            boolean bad = false;
            for (int i = 0; i < a.length; i++)
                if ((mask & (1 << i)) != 0) {
                    mult = LCM(mult, a[i]);
                    if (mult > 1000_000_000) bad = true;
                }
            if (bad) continue;
            res += sign * (numDiv(R, (int)mult) - numDiv(L - 1, (int)mult));
        }
        return (int)res;
    }
}
