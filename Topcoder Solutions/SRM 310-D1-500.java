/**
 * Created by omar on 6/10/17.
 */
public class FloatingMedian {
    int MAX = 65537;
    int FT [] = new int[MAX];
    void update (int idx, int val) {
        idx++;
        while (idx < MAX) {
            FT[idx] += val;
            idx += idx & -idx;
        }
    }
    int rsq (int idx) {
        idx++;
        int res = 0;
        while (idx > 0) {
            res += FT[idx];
            idx -= idx & -idx;
        }
        return res;
    }

    int query (int k) {
        int needed = (k + 1) / 2;
        int lo = 0, hi = MAX - 1, res = hi;
        for (int cnt = 0; cnt <= 23; cnt++) {
            int mid = lo + ((hi - lo) >> 1);
            if (rsq(mid) >= needed) {
                res = mid;
                hi = Math.max(lo, mid - 1);
            } else {
                lo = Math.min(hi, mid + 1);
            }
        }
        return res;
    }
    public long sumOfMedians(int seed, int mul, int add, int N, int K) {
        long res = 0;
        int curT = seed;
        for (int i = 0; i < K; i++) {
           update(curT, 1);
           curT = (int)(((long)curT * mul + add) % (MAX - 1));
        }
        int first = seed;
        res += query(K);
        for (int i = K; i < N; i++) {
            update(curT, 1);
            update(first, -1);
            first = (int)(((long)first * mul + add) % (MAX - 1));
            curT = (int)(((long)curT * mul + add) % (MAX - 1));
            res += query(K);
        }
        return res;
    }
   
}
