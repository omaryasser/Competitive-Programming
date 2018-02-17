import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> adjList [];
    static boolean visited [];
    static void dfs (int node) {
        visited[node] = true;
        for (int nxt : adjList[node]) if (!visited[nxt]) dfs(nxt);
    }
    static int biPartite (int start) {
        int col [] = new int[N];
        Arrays.fill(col, -1);
        col[start] = 0;
        int cnt[] = new int[2];
        cnt[0] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean isBipartite = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : adjList[cur]) {
                if (col[nxt] == -1) {
                    col[nxt] = 1 - col[cur];
                    cnt[1 - col[cur]]++;
                    q.add(nxt);
                } else if (col[nxt] == col[cur]) {
                    isBipartite = false;
                }
            }
        }
        return !isBipartite ? 0 : Math.max(cnt[0], cnt[1]);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0 ) {
            N = sc.nextInt();
            adjList = new ArrayList[N];
            for (int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int x = sc.nextInt();
                while (x-- > 0) {
                    int node = sc.nextInt() - 1;
                    if (node >= 0 && node < N) {
                        adjList[i].add(node);
                        adjList[node].add(i);
                    }
                }
            }
            visited = new boolean[N];
            int res = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    res += biPartite(i);
                }
            }
            out.println(res);
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
