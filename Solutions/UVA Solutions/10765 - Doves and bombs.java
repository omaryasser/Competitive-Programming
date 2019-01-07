
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] adjList;
    static int[] dfs_low, dfs_num, parent;
    static int V, counter, root;
    static int[] artPoints;


    static void findArtPointsAndBridges() {
        Arrays.fill(artPoints, 1);
        for (int i = 0; i < V; ++i)
            if (dfs_num[i] == 0) {
                root = i;
                artPoints[root] = 0;
                dfs(i);
            }
    }

    static void dfs(int u) {
        dfs_num[u] = dfs_low[u] = ++counter;
        for (int v : adjList[u])
            if (dfs_num[v] == 0) {
                parent[v] = u;
                if (u == root)
                    artPoints[root]++;
                dfs(v);
                if (u != root && dfs_low[v] >= dfs_num[u])
                    artPoints[u]++;
                dfs_low[u] = Math.min(dfs_low[v], dfs_low[u]);
            } else if (parent[u] != v)
                dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            V = sc.nextInt();
            int M = sc.nextInt();
            if (V == M && V == 0) {break;}
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<>();
            }
            dfs_low = new int[V];
            dfs_num = new int[V];
            parent = new int[V];
            artPoints = new int[V];

            while (true) {
                int u = sc.nextInt(), v = sc.nextInt();
                if(u == -1) {break;}
                adjList[u].add(v);
                adjList[v].add(u);
            }

            findArtPointsAndBridges();
            Pair[] pairs = new Pair[V];
            for(int i = 0; i < V; i++)
                pairs[i] = new Pair(i, artPoints[i]);
            Arrays.sort(pairs);
            for (int i = 0; i < M; i++) {
                out.println(pairs[i].u + " " + pairs[i].CC);
            }
            out.println();
        }

        out.flush();
        out.close();
    }

    static class Pair implements Comparable<Pair> {
        int u, CC;

        public Pair(int u, int CC) {
            this.u = u;
            this.CC = CC;
        }

        @Override
        public int compareTo(Pair o) {
            return CC != o.CC ? o.CC - CC : u - o.u;
        }
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
    }
}
