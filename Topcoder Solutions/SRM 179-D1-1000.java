import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by omar on 23/07/17.
 */
public class AntiMatter {
    public String unstable(int[] a) {
        boolean[] can = new boolean[30_001];
        Queue<Integer> q = new LinkedList<>();
        can[0] = true;
        q.add(0);
        while (!q.isEmpty()) {
            int curDiff = q.poll();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int newDiff = curDiff + a[i] - a[j];
                    if (newDiff >= 0 && newDiff <= 30_000 && !can[newDiff]) {
                        can[newDiff] = true;
                        q.add(newDiff);
                    }
                }
            }
        }

        int all = 10_000 * 10_000;
        int cnt = 0;
        for (int i = -4999; i <= 5000; i++)
            for (int j = -4999; j <= 5000; j++)
                cnt += can[Math.abs(i - j)] ? 1 : 0;
        String x = String.format("%.8f\n", 1.0 * cnt / all);
        return x.charAt(0) == '0' ? x.substring(1) : x;
    }
}
