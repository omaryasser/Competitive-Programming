import java.io.*;
import java.util.*;


public class E {



    static int V , number_of_flights;
    static long adjMat [][][];
    static long INF = (long)1e16;

    static long memo [][];
    static long cost (int idx , int day)
    {
        if (day == number_of_flights)
            if (idx == V - 1)
                return 0;
            else return INF;

        if (memo[idx][day] != -1) return memo[idx][day];

        long bestCost = INF;
        for (int i = 0 ; i < V ; ++i)
        {
            if (i != idx && adjMat[idx][i][day % adjMat[idx][i].length] != 0)
                bestCost = Math.min(bestCost , adjMat[idx][i][day % adjMat[idx][i].length] + cost(i , day + 1));
        }

        return memo[idx][day] = bestCost;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        for (int T = 1 ; ; T++){
            V = sc.nextInt();
            number_of_flights = sc.nextInt();
            memo = new long[V + 1][number_of_flights + 1];
            for (int i = 0 ; i <= V ; ++i)
                    Arrays.fill(memo[i] , -1);

            if (V == 0 && number_of_flights == 0) break;
            adjMat = new long[V][V][];

            for (int i = 0 ; i < V ; ++i){
                for (int j = 0; j < V ; ++j){
                    if (i == j) continue;
                    int d = sc.nextInt();
                    adjMat [i][j] = new long[d];
                    for (int k = 0 ; k < d ; ++k) adjMat[i][j][k] = sc.nextInt();
                }
            }

            out.printf("Scenario #%d\n" , T);
            long res = cost (0 , 0);
            if (res >= INF) out.printf("No flight possible.\n\n");
            else out.printf("The best flight costs %d.\n\n" , res);
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
