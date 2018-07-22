import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by omar on 23/07/17.
 */
public class YahtzeeRoll {
    int[] a;
    public double bestChoice(int[] dice) {
        a = dice;
        double max = 0;
        for (int mask = 0; mask < (1 << 5); mask++) {
            max = Math.max(max, solve(mask));
        }
        return max;
    }


    double solve (int mask) {
        if (Integer.bitCount(mask) == 0) return score(a.clone());
        double res = 0;
        int cur = 0;
        for (; cur < 5; cur++)
            if (((1 << cur) & mask) != 0)
                break;
        int tmp = a[cur];
        for (int j = 1; j <= 6; j++) {
            a[cur] = j;
            res += (1.0 / 6) * solve(mask ^ (1 << cur));
        }
        a[cur] = tmp;
        return res;
    }

    int score (int[] a) {
        Arrays.sort(a);
        boolean allSame = true, allConsecutive = true;
        HashSet<Integer> set = new HashSet<>();
        set.add(a[0]);
        for (int i = 1; i < 5; i++) {
            set.add(a[i]);
            allSame &= a[i] == a[i - 1];
            allConsecutive &= a[i] == a[i - 1] + 1;
        }
        if (allSame) return 50;
        if (allConsecutive) return 40;
        for (int start = 1; start <= 3; start++) {
            allConsecutive = true;
            for (int i = start; i <= start + 3; i++)
                allConsecutive &= set.contains(i);
            if (allConsecutive) return 30;
        }
        if (a[0] == a[1] && a[2] == a[3] && a[3] == a[4] || a[0] == a[1] && a[1] == a[2] && a[3] == a[4])
            return 25;
        return 0;
    }
}
