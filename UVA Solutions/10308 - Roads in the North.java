import java.io.*;
import java.util.*;

public class A {

    static int MAX = 10001;
    static ArrayList<Edge> adjList [];
    static Edge dfs (int node, int parent) {
        int heights [] = {-1, -1, -1};
        int bestDiam = 0;
        for (Edge e : adjList[node]) {
            int child = e.to;
            int weight = e.w;
            if (child != parent) {
                Edge nxt = dfs(child, node);
                bestDiam = Math.max(bestDiam, nxt.to);
                heights[0] = weight + nxt.w;
                Arrays.sort(heights);
            }
        }
        for (int i = 0; i < 3; i++) heights[i] = heights[i] == -1 ? 0 : heights[i];
        return new Edge(Math.max(bestDiam, heights[1] + heights[2]), heights[2]);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (sc.ready()) {
            adjList = new ArrayList[MAX];
            for (int i = 0; i < MAX; i++) adjList[i] = new ArrayList<>();
            String line;
            while (sc.ready() && !((line = sc.nextLine()).isEmpty())) {
                StringTokenizer st = new StringTokenizer(line);
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
                adjList[a].add(new Edge(b, w));
                adjList[b].add(new Edge(a, w));
            }
            out.println(dfs(1, -1).to);
        }

        out.flush();
        out.close();
    }

    static class Edge {
        int to, w;
        Edge (int t, int ww) {to = t; w = ww;}
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner (FileReader f) {br = new BufferedReader(f);}

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
