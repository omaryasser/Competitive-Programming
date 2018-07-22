import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by omar on 24/07/17.
 */
public class Refactoring {
    HashMap<Integer,Integer> mapR = new HashMap<>();
    int[] map = new int[(int)1e6];
    int mapIdx;
    int[][] memo;

    int dp (int idx1, int idx2) {
        int rem = map[idx1];
        int div = map[idx2];
        if (rem == 1) return 1;
        if (memo[idx1][idx2] != -1) return memo[idx1][idx2];
        int res = (idx2 + 2) < mapIdx ? dp(idx1, idx2 + 1) : 0;
        if (rem % div == 0) {
            res += dp(mapR.get(rem / div), idx2);
        }
        return memo[idx1][idx2] = res;
    }
    public int refactor(int n) {
        HashSet<Integer> taken = new HashSet<>();
        for (int i = 2; 1L * i * i <= n; i++) {
            if (n % i == 0) {
                if (!taken.contains(i)) {
                    taken.add(i);
                    mapR.put(i, mapIdx);
                    map[mapIdx++] = i;
                }
                if (!taken.contains(n / i)) {
                    taken.add(n / i);
                    mapR.put(n / i, mapIdx);
                    map[mapIdx++] = n / i;
                }
            }
        }
        taken.add(n); mapR.put(n, mapIdx); map[mapIdx++] = n;
        mapR.put(1, mapIdx); map[mapIdx++] = 1;
        memo = new int[mapIdx + 1][mapIdx + 1];
        for (int[] a : memo) Arrays.fill(a, -1);
        return dp(mapIdx - 2, 0) - 1;
    }
}
