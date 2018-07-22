import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int V;
    static ArrayList<Integer> adjList [];
    static boolean visited [];

    static void dfs (int node) {
        visited[node] = true;
        for (int nxt : adjList[node]) {
            if (!visited[nxt]) dfs(nxt);
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.next().charAt(0) - 'A' + 1;
            visited = new boolean[V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            String line;
            while (sc.ready() && !(line = sc.nextLine()).isEmpty()) {
                adjList[line.charAt(0) - 'A'].add(line.charAt(1) - 'A');
                adjList[line.charAt(1) - 'A'].add(line.charAt(0) - 'A');
            }
            int cnt = 0;
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    cnt++;
                    dfs (i);
                }
            }
            out.println(cnt);
            if (tc != 0) out.println();
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
