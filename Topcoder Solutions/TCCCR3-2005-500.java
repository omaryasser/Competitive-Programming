import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by omar on 23/07/17.
 */
public class PackageShipping {
    ArrayList<Edge>[] adjList;
    HashMap<String,Integer> map = new HashMap<>();
    int mapIdx, dest, packageC;
    Pair[][] memo;
    public Pair dp (int idx, int remTime) {
        if (remTime < 0) return new Pair(1e15, (int)1e6);
        if (idx == dest) return new Pair(0, 0);
        if (memo[idx][remTime] != null) return memo[idx][remTime];
        double best = Double.MAX_VALUE;
        double mult = 1e15;
        int add = (int)1e6;
        for (Edge nxt : adjList[idx]) {
            Pair go = dp(nxt.to, remTime - nxt.time);
            if (best > go.mult * (1 - nxt.prob) + nxt.prob * packageC + go.add + nxt.cost) {
                best = go.mult * (1 - nxt.prob) + nxt.prob * packageC + go.add + nxt.cost;
                mult = go.mult * (1 - nxt.prob) + packageC * nxt.prob;
                add = go.add + nxt.cost;
            }
            if (best == go.mult * (1 - nxt.prob) + nxt.prob * packageC + go.add + nxt.cost &&
                    mult > go.mult * (1 - nxt.prob) + nxt.prob * packageC) {
                mult = go.mult * (1 - nxt.prob) + nxt.prob * packageC;
                add = go.add + nxt.cost;
            }
        }
        return memo[idx][remTime] = new Pair(mult, add);
    }
    public double ship(String[] routes, String origin, String destination, int time, int packageCost) {
        map(origin);
        packageC = packageCost;
        dest = map(destination);
        for (int i = 0; i < routes.length; i++) {
            StringTokenizer st = new StringTokenizer(routes[i]);
            map(st.nextToken());
            map(st.nextToken());
        }
        adjList = new ArrayList[mapIdx];
        for (int i = 0; i < mapIdx; i++) adjList[i] = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            StringTokenizer st = new StringTokenizer(routes[i]);
            int from = map(st.nextToken()), to = map(st.nextToken());
            adjList[from].add(new Edge(to, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()) / 100.0));
        }
        memo = new Pair[mapIdx][time + 1];
        Pair res = dp(map(origin), time);
        return res.mult + res.add;
    }

    int map (String s) {
        Integer was = map.get(s);
        if (was == null) map.put(s, mapIdx++);
        return map.get(s);
    }
    class Pair {
        double mult;
        int add;
        Pair (double m, int a) {
            mult = m;
            add = a;
        }
    }
    class Edge {
        int to, time, cost;
        double prob;
        Edge (int to, int time, int c, double p) {
            this.to = to;
            this.time = time;
            cost = c;
            prob = p;
        }
    }
}
