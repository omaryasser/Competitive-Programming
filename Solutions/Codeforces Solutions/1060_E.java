import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer> T[];
    static int sz[];
    static long[] distances, even, odd;
    static long odd_cnt = 0, distances_;
    static void dfs(int u, int p){
        int cum_sz = 0;
        sz[u] = 1;
        for(int v : T[u]){
            if(v == p)continue;
            dfs(v, u);
            distances_ += 1L * (distances[v] + sz[v]) * (cum_sz + 1) + (distances[u] * sz[v]);
//            System.err.println(v + " " + (1L * (distances[v] + sz[v]) * (cum_sz + 1) + (distances[u] * sz[v])) + " " + distances[v] + " " + distances[u]);
            distances[u] += (distances[v] + sz[v]);
            cum_sz += sz[v];
            odd_cnt += 1L * (even[v] + 1) * even[u] + odd[u] * odd[v];
            odd_cnt += even[v] + 1;
            even[u] += odd[v];
            odd[u] += even[v] + 1;
            sz[u] += sz[v];
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        T = new ArrayList[n];
        distances = new long[n];
        even = new long[n];
        sz = new int[n];
        odd = new long[n];
        for(int i = 0; i < n; i++)T[i] = new ArrayList<>();
        for(int i = 0; i < n - 1; i++){
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
            T[u].add(v);
            T[v].add(u);
        }

        dfs(0, -1);

        out.println((distances_ + odd_cnt) / 2);
        out.flush();
    }
    static class Scanner
    {
        StringTokenizer st; BufferedReader br;
        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
        public Scanner(String s) throws FileNotFoundException {	br = new BufferedReader(new FileReader(new File(s)));}
        public String next() throws IOException {while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
        public int nextInt() throws IOException {return Integer.parseInt(next());}
        public long nextLong() throws IOException {return Long.parseLong(next());}
        public String nextLine() throws IOException {return br.readLine();}
        public boolean ready() throws IOException {return br.ready();}
    }
}
