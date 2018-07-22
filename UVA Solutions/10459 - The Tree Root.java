import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N ;
    static ArrayList<Integer> adjList [] ;
    static int bestUp [] ;
    static int bestDown [][];

    static void dfs (int node , int parent){
        for (int child : adjList[node]) {
            if (child != parent) {
                dfs(child , node);
                bestDown[node][1] = Math.max(bestDown[node][1] , bestDown[child][0] + 1);
                if (bestDown[node][1] > bestDown[node][0]) {
                    bestDown [node][1] ^= bestDown[node][0];
                    bestDown [node][0] ^= bestDown[node][1];
                    bestDown [node][1] ^= bestDown[node][0];
                }
            }
        }
    }

    static void dfs2 (int node , int parent , int myBestUp){
        bestUp[node] = myBestUp;
        for (int child : adjList[node])
            if (child != parent) {
                int sendHeight ;
                if (bestDown[node][0] == bestDown[child][0] + 1) sendHeight = bestDown[node][1];
                else sendHeight = bestDown[node][0];
                dfs2(child , node , Math.max(myBestUp + 1 , sendHeight + 1));
            }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (sc.ready())
        {
            N = sc.nextInt();
            adjList = new ArrayList[N];
            bestUp = new int[N];
            bestDown = new int[N][2];
            for (int i = 0 ; i < N ; ++ i) adjList[i] = new ArrayList<>();
            for (int i = 0 ; i < N ; ++ i) {
                int k = sc.nextInt();
                while (k -- > 0) adjList [i].add(sc.nextInt() - 1);
            }

            dfs (0 , - 1);
            dfs2(0 , - 1 , 0);

            int H [] = new int [N];
            for (int i = 0 ; i < N ; ++ i) H[i] = Math.max(bestDown[i][0] , bestUp[i]);

            int maxH = H[0] , minH = H[0];
            for (int i = 1 ; i < N ; ++ i) {
                maxH = Math.max(maxH , H[i]);
                minH = Math.min(minH , H[i]);
            }

            out.print("Best Roots  :");
            for (int i = 0 ; i < N ; ++ i) if (H[i] == minH) out.printf(" %d" , i + 1);
            out.print("\n");
            out.print("Worst Roots :");
            for (int i = 0 ; i < N ; ++ i) if (H[i] == maxH) out.printf(" %d" , i + 1);
            out.print("\n");
        }
        out.print(sb);
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
