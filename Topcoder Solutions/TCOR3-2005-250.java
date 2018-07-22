import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by omar on 09/08/17.
 */
public class PlayingCubes {
    int INF = (int)1e9;
    int V, s, t, c[][];
    int cubesNum;
    int cube (int idx) {return idx + 1;}
    int letter (int idx) {return 1 + cubesNum + idx;}
    public int[] composeWords(String[] cubes, String[] words) {
        ArrayList<Integer> res = new ArrayList<>();
        V = 2 + 26 + (cubesNum = cubes.length);
        for (int j = 0; j < words.length; j++){
            String wanted = words[j];
            c = new int[V][V];
            t = V - 1;
            for (int i = 0; i < cubesNum; i++) {
                c[s][cube(i)] = 1;
                for (char cc : cubes[i].toCharArray()) c[cube(i)][letter(cc - 'A')] = 1;
            }
            for (char cc : wanted.toCharArray()) c[letter(cc - 'A')][t]++;
            if (pushRelabel() == wanted.length()) res.add(j);
        }
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ret[i] = res.get(i);
        return ret;
    }

    int pushRelabel () { // O(V ^ 3)
        int[] h = new int[V], e = new int[V], f[] = new int[V][V];
        h[s] = V - 1;

        Queue<Integer> q = new LinkedList<>();
        boolean[] isActive = new boolean[V];
        for (int i = 0; i < V; i++) {
            f[i][s] = -(f[s][i] = e[i] = c[s][i]);
            if (i != s && i != t && e[i] > 0) {
                isActive[i] = true;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.peek();
            boolean pushed = false;
            for (int v = 0; v < V && e[u] != 0; v++) {
                if (h[u] > h[v] && c[u][v] - f[u][v] > 0) {
                    int df = Math.min(e[u], c[u][v] - f[u][v]);
                    f[u][v] += df;
                    f[v][u] -= df;
                    e[u] -= df;
                    e[v] += df;
                    if (v != s && v != t && !isActive[v]) {
                        isActive[v] = true;
                        q.add(v);
                    }
                    pushed = true;
                }
            }
            if (e[u] == 0) {
                isActive[u] = false;
                q.remove();
            }

            if (!pushed) {
                h[u] = INF;
                for (int v = 0; v < V; ++v)
                    if (c[u][v] - f[u][v] > 0 && h[v] <= h[u])
                        h[u] = h[v] + 1;
            }
        }
        return e[t];
    }
}
