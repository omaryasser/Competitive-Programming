import java.io.*;
import java.util.*;


public class B {



    static int V;
    static ArrayList<Integer> adjList[];

    static int memo [];
    static boolean visited[];

    static int solve (int u)
    {
        int ans = 0 ;
        visited[u] = true;
        for (int v : adjList[u])
            if (!visited[v]) ans += 1 + solve(v);
        return ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            V = sc.nextInt();
            if (V == 0) break;
            adjList = new ArrayList[V];
            memo = new int[V];
            visited  = new boolean[V];
            Arrays.fill(memo , - 1);
            for (int i = 0 ; i < V ; ++i) adjList[i] = new ArrayList<>();
            for (int i = 0 ; i < V ; ++i)
            {
                int N = sc.nextInt();
                while (N -- > 0) adjList[i].add(sc.nextInt() - 1);
            }
            int max = -1 , maxIdx = -1;
            for (int i = 0 ; i < V ; ++i)
            {
                Arrays.fill(visited , false);
                int res = solve (i);
                if (res > max)
                {
                    max = res;
                    maxIdx = i + 1;
                }
            }

            out.printf("%d\n" , maxIdx);
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
