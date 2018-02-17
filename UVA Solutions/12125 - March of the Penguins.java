import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            V = 2 * n + 2;
            t = V - 1;
            double d = sc.nextDouble();
            TreeMap<Pair,Integer> map = new TreeMap<>();
            double[] x = new double[n], y = new double[n];
            int total = 0;
            int[] num = new int[n], times = new int[n];
            int mapIdx = 1;
            for (int i = 0; i < n; i++) {
                Pair p = new Pair(x[i] = sc.nextDouble(), y[i] = sc.nextDouble());
                num[i] = sc.nextInt();
                times[i] = sc.nextInt();
                total += num[i];
                if (!map.containsKey(p)) {
                    map.put(p, mapIdx++);
                    mapIdx++;
                }
            }

            ArrayList<Integer> ret = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                res = new int[V][V];
                adjList = new ArrayList[V];
                for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            if (new Pair(x[i], y[i]).dist(new Pair(x[j], y[j])) <= d * d + EPS) {
                                edge(map.get(new Pair(x[i], y[i])) + 1, map.get(new Pair(x[j], y[j])), INF);
                            }
                        }
                    }
                    edge(map.get(new Pair(x[i], y[i])), map.get(new Pair(x[i], y[i])) + 1, times[i]);
                }
                for (int i = 0; i < n; i++)
                    edge(s, map.get(new Pair(x[i], y[i])), num[i]);
                edge(map.get(new Pair(x[k], y[k])), t, INF);
                if (edmondsKarp() == total)
                    ret.add(k);
            }
            if (ret.size() == 0) out.println(-1);
            else {
                for (int i = 0; i < ret.size(); i++) {
                    if (i > 0) out.print(" ");
                    out.print(ret.get(i));
                }
                out.println();
            }
        }

        out.close();
    }

    static double EPS = 1e-9;
    static class Pair implements Comparable<Pair>{
        double x, y;
        Pair (double xx, double yy) {
            x = xx;
            y = yy;
        }

        @Override
        public int compareTo(Pair pair) {
            if (Math.abs(x - pair.x) < EPS)
                return Double.compare(y, pair.y);
            return Double.compare(x, pair.x);
        }

        double sq (double x) {return x * x;}
        double dist (Pair p) {
            return sq(x - p.x) + sq(y - p.y);
        }
    }
    static void edge (int u, int v, int c) {
        res[u][v] = c;
        adjList[u].add(v);
        adjList[v].add(u);
    }
    static final int INF = (int)1e9;
    static int V, s, t;
    static ArrayList<Integer>[] adjList;
    static int[][] res;
    static int[] p;

    static int augment(int v, int flow)
    {
        if(v == s)
            return flow;
        flow =  augment(p[v], Math.min(res[p[v]][v],flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static int edmondsKarp()
    {
        int mf = 0;
        while(true)
        {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            p[s] = s;
            q.add(s);
            while(!q.isEmpty())
            {
                int u = q.remove();
                if(u == t)
                    break;
                for(int v : adjList[u])
                    if(res[u][v] > 0 && p[v] == -1)
                    {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if(p[t] == -1)
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
        public double nextDouble() {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }
    }
}
