import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.Random;
public class Main {

    static ArrayList<Integer> adjList [];
    static int V;
    static ArrayList<Integer> bfs (int src, int dest) {
        Queue<Integer> q = new LinkedList<>();
        int [] p = new int[V];
        Arrays.fill(p, -1);
        p[src] = src;
        q.add(src);
        int [] dist = new int[V];
        Arrays.fill(dist, V + 100);
        dist[src] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adjList[cur]) {
                if (dist[cur] + 1 < dist[nxt]) {
                    dist[nxt] = dist[cur] + 1;
                    p[nxt] = cur;
                    q.add(nxt);
                }
            }
        }
        if (p[dest] == -1) return null;
        else {
            ArrayList<Integer> res = new ArrayList<>();
            while (true) {
                res.add(dest);
                if (dest == p[dest]) break;
                dest = p[dest];
            }
            Collections.reverse(res);
            return res;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        boolean first = true;

        while (sc.ready()) {
            HashMap<String,Integer> stringInt = new HashMap<>();
            HashMap<Integer,String> intString = new HashMap<>();

            int E = sc.nextInt();
            int mapIdx = 0;
            ArrayList<Integer> uu = new ArrayList<>(), vv = new ArrayList<>();
            while (E-- > 0) {
                String u = sc.next(), v = sc.next();
                if (!stringInt.containsKey(u)) {
                    stringInt.put(u, mapIdx);
                    intString.put(mapIdx++, u);
                }
                if (!stringInt.containsKey(v)) {
                    stringInt.put(v, mapIdx);
                    intString.put(mapIdx++, v);
                }
                uu.add(stringInt.get(u));
                vv.add(stringInt.get(v));
            }

            V = mapIdx;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();

            for (int i = 0; i < uu.size(); i++) {
                int u = uu.get(i), v = vv.get(i);
                adjList[u].add(v);
                adjList[v].add(u);
            }

            if (first) first = false;
            else out.println();

            Integer src = stringInt.get(sc.next()), dst = stringInt.get(sc.next());
            if (src == null || dst == null) out.println("No route");
            else {
                ArrayList<Integer> res = bfs(src, dst);
                if (res == null) out.println("No route");
                else {
                    for (int i = 0; i < res.size() - 1; i++) {
                        out.println(intString.get(res.get(i)) + " " + intString.get(res.get(i + 1)));
                    }
                }
            }
        }

        out.flush();
        out.close();
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

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
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

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
