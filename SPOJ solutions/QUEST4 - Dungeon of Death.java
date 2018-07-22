import java.io.*;
import java.util.*;


public class Main {
    static boolean visited [];
    static int matched [];
    static ArrayList<Integer> adjList [];
    static int V;
    static boolean aug (int u) {
        if (visited[u]) return false;
        visited[u] = true;

        for (int node : adjList[u]) {
            if (matched [node] == -1 || aug (matched[node])) {
                matched[node] =  u; return true;
            }
        }

        return false;
    }

    static int MCBM (int leftSize) {
        int res = 0;
        matched = new int[V];
        Arrays.fill(matched, -1);
        for (int i = 0; i < leftSize; ++i) {
            visited = new boolean[V];
            res += (aug(i) ? 1 : 0);
        }

        return res;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            V = 2 * 120;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < n; i++) adjList[sc.nextInt()].add(sc.nextInt() + 120);
            out.println(MCBM(120));
        }

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
