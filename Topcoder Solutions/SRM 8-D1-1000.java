import java.util.ArrayList;

/**
 * Created by omar on 28/07/17.
 */
public class PendingTasks {
    ArrayList<Integer>[] child;
    int[] subSize;
    public int latestProcess(int[] p) {
        int n = p.length;
        subSize = new int[n];
        child = new ArrayList[n];
        for (int i = 0; i < n; i++) child[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) child[p[i]].add(i);
        go(n - 1);
        return solve(n - 1);
    }

    int solve (int node) {
        int best = 0;
        for (int c1 : child[node]) {
            for (int c2 : child[node]) {
                if (c1 == c2) continue;
                int sum = 0;
                for (int c3 : child[node]) {
                    if (c3 != c1 && c3 != c2)
                        sum += dont(c3);
                }
                best = Math.max(best, sum + subSize[c1] + 1 + solve(c2) + 1);
            }
            best = Math.max(best, subSize[c1] + 1);
        }
        return best;
    }

    int dont (int node) {
        int max = 0;
        for (int c1 : child[node]) {
            int sum = 0;
            for (int c2 : child[node])
                if (c1 != c2)
                    sum += dont(c2);
            max = Math.max(max, 1 + subSize[c1] + sum);
        }
        return max;
    }

    int go (int node) {
        for (int c : child[node])
            subSize[node] += 1 + go(c);
        return subSize[node];
    }
}
