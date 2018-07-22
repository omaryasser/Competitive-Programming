package codes;

import java.util.ArrayList;
import java.util.Arrays;

public class PointyWizardHats {
    int V;
    boolean []visited;
    int []matched;
    ArrayList<Integer> []adjList;
    boolean aug (int node) {
        if (visited[node]) return false;
        visited[node] = true;
        for (int nxt : adjList[node])
            if (matched[nxt] == -1 || aug(matched[nxt])) {
                matched[nxt] = node;
                return true;
            }
        return false;
    }
    int MCBM (int leftSize) {
        matched = new int[V];
        Arrays.fill(matched, -1);
        int res = 0;
        for (int i = 0; i < leftSize; i++) {
            visited = new boolean[V];
            res += aug(i) ? 1 : 0;
        }
        return res;
    }
    public int getNumHats(int[] topHeight, int[] topRadius, int[] bottomHeight, int[] bottomRadius) {
        V = topHeight.length + bottomHeight.length;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < topHeight.length; i++) {
            for (int j = 0; j < bottomHeight.length; j++) {
                if (topRadius[i] >= bottomRadius[j]) continue;
                if (bottomHeight[j] * topRadius[i] < topHeight[i] * bottomRadius[j])
                    adjList[i].add(j + topHeight.length);
            }
        }
        return MCBM(topHeight.length);
    }
}
