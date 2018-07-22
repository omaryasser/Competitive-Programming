import java.util.*;

/**
 * Created by omar on 12/3/16.
 */
public class TopView {
    static TreeMap<Character , Integer> treeMap = new TreeMap<>();
    static TreeMap<Integer , Character> intToChar = new TreeMap<>();
    static int n;
    static TreeSet<Integer> adjList [];
    static boolean visited[];
    static void dfs2 (int u) {
        visited[u] = true;
        for (int i : adjList[u]) {
            if (!visited[i])dfs2(i);
        }
    }
    static boolean dfs () {
        visited = new boolean[adjList.length];
        for (int i = 0 ; i < adjList.length ; ++ i) {
                Arrays.fill(visited,false);
                for (int j : adjList[i]) dfs2(j);
                if (visited[i]) return false;
        }
        return true;
    }
    public static String findOrder(String[] grid) {
        n = grid.length;
        int mapIdx = 0;
        for (int i = 0 ; i < n ; ++ i) {
            for (int j = 0 ; j < grid[i].length() ; ++ j) {
                if (grid[i].charAt(j) != '.' && !treeMap.containsKey(grid[i].charAt(j))) {
                    treeMap.put(grid[i].charAt(j) , mapIdx ++ );
                    intToChar.put(mapIdx - 1 , grid[i].charAt(j));
                }
            }
        }
        adjList = new TreeSet[mapIdx];
        TreeSet<Integer> inDeg [] = new TreeSet[mapIdx];
        for (int i = 0 ; i < adjList.length ; ++ i) {
            adjList[i] = new TreeSet<>();
            inDeg[i] = new TreeSet<>();
        }
        boolean ok = true;
        for (Map.Entry<Character,Integer> mp : treeMap.entrySet()){
            char c = mp.getKey();
            int left = 200 , right = - 20 , top = 200 , bottom = -20;
            for (int i = 0 ; i < n ; ++ i) {
                for (int j = 0 ; j < grid[i].length() ; ++ j) {
                    if (grid[i].charAt(j) == c) {
                        left = Math.min(left , j);
                        right = Math.max(right , j);
                        bottom = Math.max(bottom , i);
                        top = Math.min(top , i);
                    }
                }
            }
            for (int i = top ; i <= bottom ; ++ i) {
                for (int j = left ; j <= right ; ++ j) {
                    if (grid[i].charAt(j) == '.') {
                        ok = false;
                    }
                    else if (grid[i].charAt(j) != c) {
                        adjList[treeMap.get(c)].add(treeMap.get(grid[i].charAt(j)));
                        inDeg[treeMap.get(grid[i].charAt(j))].add(treeMap.get(c));
                        if (adjList[treeMap.get(grid[i].charAt(j))].contains(treeMap.get(c))) {
                            ok = false;
                        }
                    }
                }
            }
        }
        if (!dfs() || ! ok) return "ERROR!";
        StringBuilder res = new StringBuilder();
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
        boolean taken [] = new boolean[mapIdx];
        for (int i = 0 ; i < mapIdx ; ++ i) {
            if (inDeg[i].size() == 0){
                priorityQueue.add(intToChar.get(i));
                taken[i] = true;
            }
        }
        while (!priorityQueue.isEmpty()) {
            char c = priorityQueue.poll();
            res.append(c);
            for (int i :adjList[treeMap.get(c)]) {
                inDeg[i].remove(treeMap.get(c));
            }
            for (int i = 0 ; i < mapIdx ; ++ i) {
                if (inDeg[i].size() == 0 && !taken[i]){
                    priorityQueue.add(intToChar.get(i));
                    taken[i] = true;
                }
            }
        }
        return res.toString();
    }
    public static void main (String [] args) {
        System.out.println(TopView.findOrder(new String [] {"bbb666",
                ".655X5",
                "a65AA5",
                "a65AA5",
                "a65555"}));
    }
}
