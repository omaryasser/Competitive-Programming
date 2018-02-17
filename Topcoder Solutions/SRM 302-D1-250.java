import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by omar on 25/07/17.
 */
public class DivisorInc {
    public int countOperations(int N, int M) {
        int[] dist = new int[Math.max(N, M) + 1];
        Arrays.fill(dist, -1);
        dist[N] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 2; 1L * i * i <= cur; i++) {
                if (cur % i == 0) {
                    if (cur + i <= M && (dist[cur + i] == -1 || dist[cur + i] > dist[cur] + 1)) {
                        dist[cur + i] = dist[cur] + 1;
                        q.add(cur + i);
                    }
                    int j = cur / i;
                    if (cur + j <= M && (dist[cur + j] == -1 || dist[cur + j] > dist[cur] + 1)) {
                        dist[cur + j] = dist[cur] + 1;
                        q.add(cur + j);
                    }
                }
            }
        }
        return dist[M];
    }
}
