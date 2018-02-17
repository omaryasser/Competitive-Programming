import java.io.*;
import java.util.*;


public class Main {

    static int V;
    static ArrayList<Integer> adjList [];
    static int memo [];
    static boolean explored [];
    static int ways (int u)
    {
        if (adjList[u].size() == 0) return 1;
        if (memo[u] != -1) return memo[u];
        int res = 0;
        for (int v : adjList[u])
            res += ways(v);


        return memo[u] = res;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (sc.ready())
        {
            V = sc.nextInt();
            memo = new int[V];
            explored = new boolean[V];
            Arrays.fill(memo,-1);
            adjList = new ArrayList[V];
            for (int i = 0 ; i < V ; ++i)
                adjList[i] = new ArrayList<>();
            for (int i = 0 ; i < V ; ++i)
            {
                int n = sc.nextInt();

                while (n -- > 0)
                    adjList[i].add(sc.nextInt());
            }

            if (first) first = false;
            else out.print("\n");
            out.print(ways(0) + "\n");

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
