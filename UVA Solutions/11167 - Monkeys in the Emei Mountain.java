import java.io.*;
import java.util.*;


public class Main {
    static final int INF = (int) 1e9;
    static int V, s, t, monkiesNum;
    static ArrayList<Integer>[] adjList;
    static int[][] res;
    static int[] p;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 1;; tc++){
            monkiesNum = sc.nextInt();
            if (monkiesNum == 0) break;
            int m = sc.nextInt();
            int MAX = 50_001;
            boolean[] point = new boolean[MAX];
            int totalWanted = 0;
            int[] from = new int[monkiesNum], to = new int[monkiesNum], wanted = new int[monkiesNum];
            for (int i = 0; i < monkiesNum; i++) {
                totalWanted += (wanted[i] = sc.nextInt());
                point[from[i] = sc.nextInt()] = point[to[i] = sc.nextInt()] = true;
            }

            ArrayList<Interval> intervals = new ArrayList<>();
            int last = -1;
            for (int i = 0; i < MAX; i++) {
                if (point[i]) {
                    if (last == -1) last = i;
                    else {
                        intervals.add(new Interval(last, i));
                        last = i;
                    }
                }
            }

            int[] intervalIdx = new int[MAX];
            for (int i = 0; i < intervals.size(); i++) intervalIdx[intervals.get(i).s] = i;

            V = monkiesNum + intervals.size() + 2;
            res = new int[V][V];
            adjList = new ArrayList[V];
            t = V - 1;
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            for (int i = 0; i < monkiesNum; i++) addEdge(s, monkey(i), wanted[i]);
            for (int i = 0; i < intervals.size(); i++) addEdge(interval(i), t, m * (intervals.get(i).e - intervals.get(i).s));
            for (int i = 0; i < monkiesNum; i++)
                for (int j = from[i]; j < to[i]; j++)
                    if (point[j])
                        addEdge(monkey(i), interval(intervalIdx[j]), intervals.get(intervalIdx[j]).e - intervals.get(intervalIdx[j]).s);

            boolean ok = edmondsKarp() == totalWanted;
            out.printf("Case %d: %s\n", tc, ok ? "Yes" : "No");
            if (!ok) continue;

            int[] taken = new int[MAX];
            for (int i = 0; i < monkiesNum; i++) {
                ArrayList<Interval> pointsTaken = new ArrayList<>();
                for (int j = from[i]; j < to[i]; j++)
                    if (point[j]) {
                        if (res[interval(intervalIdx[j])][monkey(i)] > 0) {
                            int curIntervalIdx = intervalIdx[j];
                            Interval curInterval = intervals.get(curIntervalIdx);
                            int wantedHere = res[interval(intervalIdx[j])][monkey(i)];
                            for (int search = 0, soFar = 0; search < m; search++)
                                for (int x = curInterval.s; soFar < wantedHere && x < curInterval.e; x++) {
                                    if (taken[x] == search) {
                                        taken[x]++;
                                        soFar++;
                                        pointsTaken.add(new Interval(x, x + 1));
                                    }
                                }
                        }
                    }
                Collections.sort(pointsTaken);
                ArrayList<Integer> pointssTaken = new ArrayList<>();
                for (Interval interval : pointsTaken) {
                    pointssTaken.add(interval.s);
                    pointssTaken.add(interval.e);
                }
                int[] finished = new int[MAX];
                finished[0] = pointssTaken.get(0);
                finished[1] = pointssTaken.get(1);
                int lastIdx = 1;
                for (int j = 2; j < pointssTaken.size(); j += 2) {
                    if (pointssTaken.get(j) == finished[lastIdx]) finished[lastIdx] = pointssTaken.get(j + 1);
                    else {
                        finished[++lastIdx] = pointssTaken.get(j);
                        finished[++lastIdx] = pointssTaken.get(j + 1);
                    }
                }
                out.print((lastIdx + 1) / 2);
                for (int j = 0; j <= lastIdx; j += 2)
                    out.printf(" (%d,%d)", finished[j], finished[j + 1]);
                out.println();
            }
        }

        out.close();
    }

    static int monkey (int idx) {return idx + 1;}
    static int interval (int idx) {return idx + 1 + monkiesNum;}
    static class Interval implements Comparable<Interval>{
        int s, e;
        Interval (int ss, int ee) {
            s = ss;
            e = ee;
        }

        @Override
        public int compareTo(Interval interval) {
            return s - interval.s;
        }
    }
    static void addEdge (int u, int v, int c) {
        res[u][v] = c;
        adjList[u].add(v);
        adjList[v].add(u);
    }
    static int augment(int v, int flow) {
        if (v == s)
            return flow;
        flow = augment(p[v], Math.min(res[p[v]][v], flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static int edmondsKarp() {
        int mf = 0;
        while (true) {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            p[s] = s;
            q.add(s);
            while (!q.isEmpty()) {
                int u = q.remove();
                if (u == t)
                    break;
                for (int v : adjList[u])
                    if (res[u][v] > 0 && p[v] == -1) {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if (p[t] == -1)
                break;
            mf += augment(t, INF);

        }
        return mf;
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
