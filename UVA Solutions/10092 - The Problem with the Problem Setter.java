import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main
{
    static int V;
    static int [][] res;
    static ArrayList<Integer> adjList [];
    static int S, D;
    static boolean visited [];
    static int curFlow;
    static int INF = (int)1e9;
    static int nxtNode [];
    static boolean aug (int node, int bottle) {
        visited[node] = true;
        if (node == D) {curFlow = bottle; return true;}
        for (int nxt : adjList[node]) {
            if (!visited[nxt] && res[node][nxt] != 0) {
                if (aug(nxt, Math.min(bottle, res[node][nxt]))) {
                    nxtNode[node] = nxt;
                    return true;
                }
            }
        }
        return false;
    }
    static int ford_fulkerson () {
        int maxFlow = 0;
        while (true) {
            visited = new boolean[V];
            nxtNode = new int[V];
            curFlow = 0;
            aug(S, INF);
            if (curFlow == 0) { break; }
            int curNode = S;
            while (curNode != D) {
                res[curNode][nxtNode[curNode]] -= curFlow;
                res[nxtNode[curNode]][curNode] += curFlow;
                curNode = nxtNode[curNode];
            }
            maxFlow += curFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int categories = sc.nextInt();
            int problems = sc.nextInt();
            if (categories == 0 && problems == 0) break;
            V = categories + problems + 2;
            S = 0;
            D = V - 1;
            res = new int[V][V];
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            for (int i = 0; i < problems; i++) {
                adjList[S].add(i + 1);
                adjList[i + 1].add(S);
                res[S][i + 1] = 1;
            }
            int sumNeeded = 0;
            for (int i = 0; i < categories; i++) {
                int needed = sc.nextInt();
                sumNeeded += needed;
                res[problems + i + 1][D] = needed;
                adjList[problems + i + 1].add(D);
                adjList[D].add(problems + i + 1);
            }
            for (int i = 0; i < problems; i++) {
                int num = sc.nextInt();
                while (num-- > 0) {
                    int can = sc.nextInt() - 1;
                    adjList[i + 1].add(can + problems + 1);
                    adjList[can + problems + 1].add(i + 1);
                    res[i + 1][can + problems + 1] = 1;
                }
            }
            int maxFlow = ford_fulkerson();
            if (maxFlow == sumNeeded) {
                out.println("1");
                ArrayList<Integer> resCat [] = new ArrayList[categories];
                for (int i = 0; i < categories; i++) resCat[i] = new ArrayList<>();
                for (int i = 0; i < problems; i++) {
                    for (int cat : adjList[i + 1]) {
                        if (cat != S && res[i + 1][cat] == 0) resCat[cat - problems - 1].add(i + 1);
                    }
                }
                for (int i = 0; i < categories; i++) {
                    for (int j = 0; j < resCat[i].size(); j++) {
                        if (j > 0) out.print(" ");
                        out.print(resCat[i].get(j));
                    }
                    out.println();
                }
            } else {
                out.println("0");
            }
        }

        out.flush();
        out.close();
    }








































    static class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public boolean ready() throws IOException {return br.ready();}
    }
}
