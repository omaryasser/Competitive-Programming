import java.util.*;

/**
 * Created by omar on 6/5/17.
 */
public class FlowerGarden {
    public int[] getOrdering(int[] height, int[] start, int[] end) {
        int n = height.length;
        ArrayList<Integer> adjList [] = new ArrayList[n];
        for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
        int inDeg [] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((start[i] >= start[j] && start[i] <= end[j] || end[i] >= start[j] && end[i] <= end[j]) ||
                        (start[j] >= start[i] && start[j] <= end[i] || end[j] >= start[i] && end[j] <= end[i])) {
                    System.err.println(i + " " + j);
                    if (height[i] > height[j]) {
                        adjList[j].add(i);
                        inDeg[i]++;
                    } else {
                        adjList[i].add(j);
                        inDeg[j]++;
                    }
                }
            }
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return height[i2] - height[i1];
            }
        });
        for (int i = 0; i < n; i++)
            if (inDeg[i] == 0)
                priorityQueue.add(i);

        int res [] = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            int cur = priorityQueue.poll();
            res[idx++] = height[cur];
            for (int nxt : adjList[cur]) {
                if (--inDeg[nxt] == 0) priorityQueue.add(nxt);
            }
        }
        return res;
    }
    public static void main (String [] args) {
        System.err.println(Arrays.toString(new FlowerGarden().getOrdering(new int [] {3,2,5,4}, new int[] {1,2,11,10}, new int [] {4,3,12,13})));
    }
}
