import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int parent [];
    static int level [];
    static int levelParent [];
    static int root ;
    static int sqrt;
    static ArrayList<Integer> adjList [];
    static void fillLevels (int node) {
        for (int child : adjList[node]) {
            if (child != parent[node]) {
                level[child] = level[node] + 1;
                fillLevels(child);
            }
        }
    }
    static void dfs (int node) {
        if (level[node] < sqrt)
            levelParent[node] = 1;
        else if (level[node] % sqrt == 0)
            levelParent[node] = parent[node];
        else
            levelParent[node] = levelParent[parent[node]];
        for (int child : adjList[node])
            dfs(child);
    }
    static int query (int x , int y) {
        while (levelParent[x] != levelParent[y]) {
            if (level[x] > level[y])
                x = levelParent[x];
            else
                y = levelParent[y];
        }

        while (x != y) {
            if (level[x] > level[y])
                x = parent[x];
            else
                y = parent[y];
        }
        return x;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int TT = 1 ; TT <= T ; TT ++ ){
            N = sc.nextInt();
            parent = new int[N];
            level = new int[N];
            levelParent = new int[N];
            Arrays.fill(parent , - 1);
            adjList = new ArrayList[N];
            for (int i = 0 ; i < N ; ++ i)
                adjList[i] = new ArrayList<>();
            sqrt = (int)Math.sqrt(N);
            for (int i = 0 ; i < N ; ++ i) {
                int M = sc.nextInt();
                while (M -- > 0) {
                    int child = sc.nextInt() - 1;
                    adjList[i].add(child);
                    parent[child] = i;
                }
            }
            for (int i = 0 ; i < N ; ++ i)
                if (parent[i] == - 1)
                    root = i;
            level[root] = 0;
            fillLevels(root);
            dfs(root);
            out.printf("Case %d:\n" , TT);
            int Q = sc.nextInt();
            while (Q -- > 0) {
                int u = sc.nextInt() - 1 , v = sc.nextInt() - 1;
                out.println(query(u , v) + 1);
            }
        }

        out.close();
    }

























    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
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
