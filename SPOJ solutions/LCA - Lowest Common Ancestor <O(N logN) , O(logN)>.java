import java.io.*;
import java.util.*;

public class Main {
    static int N ;
    static int parent [] ;
    static int parentLog [][] ;
    static int level [] ;
    static int root ;
    static int maxLog ;
    static ArrayList<Integer> adjList [] ;
    static void fillLevels (int node) {
        for (int child : adjList[node]) {
            if (child != parent[node]) {
                level[child] = level[node] + 1;
                fillLevels(child);
            }
        }
    }

    static int query (int x , int y) {
        if (level[x] < level[y]) {
            x ^= y;
            y ^= x;
            x ^= y;
        }
        // Now X is in the same level or below Y

        // Now get the ancestor of X which is in the same level as Y
        for (int log = maxLog ; log >= 0 ; -- log)
            if (level[x] - (1 << log) >= level[y])
                x = parentLog[x][log];
        // Now X is in the same level as Y

        if (x == y) return x;
        for (int log = maxLog ; log >= 0 ; -- log)
            if (parentLog[x][log] != - 1 && parentLog[x][log] != parentLog[y][log]) {
                x = parentLog[x][log];
                y = parentLog[y][log];
            }
        return parent[x];
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int T = sc.nextInt();
        for (int TT = 1 ; TT <= T ; TT ++ ) {
            N = sc.nextInt();
            adjList = new ArrayList[N];
            parent = new int[N];
            level = new int[N];
            Arrays.fill(parent , - 1);

            for (int i = 0 ; i < N ; ++ i)
                adjList[i] = new ArrayList<>();

            for (int i = 0 ; i < N ; ++ i) {
                int M = sc.nextInt();
                while (M -- > 0) {
                    int child = sc.nextInt() - 1 ;
                    parent[child] = i;
                    adjList[i].add(child);
                }
            }

            for (int i = 0 ; i < N ; ++ i)
                if (parent[i] == - 1)
                    root = i;
            level[root] = 0;
            fillLevels(root);

            maxLog = 0;
            for ( ; 1 << maxLog <= N ; maxLog ++ );
            maxLog -- ;

            parentLog = new int[N][maxLog + 1];
            for (int i = 0 ; i < N ; ++ i)
                Arrays.fill(parentLog[i] , - 1);

            for (int i = 0 ; i < N ; ++ i)
                parentLog[i][0] = parent[i];

            for (int j = 1 ; j <= maxLog ; ++ j)
                for (int i = 0 ; i < N ; ++ i)
                    if (parentLog[i][j - 1] != - 1)
                        parentLog[i][j] = parentLog[parentLog[i][j - 1]][j - 1];

            out.printf("Case %d:\n" , TT);
            int Q = sc.nextInt();
            while (Q -- > 0) {
                out.println(query(sc.nextInt() - 1 , sc.nextInt() - 1) + 1);
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
