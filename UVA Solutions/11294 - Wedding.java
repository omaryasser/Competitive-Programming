import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static ArrayList<Integer> adjList [];
    static int [] dfs_num , dfs_low , visited, component, rootOfComponent, res;
    static int dfsNumberCounter , numSCC;
    static Stack <Integer> S;
    static int UNVISITED = 0;
    static int V;
    public static void tarjanSCC(int u)
    {
        dfs_low[u] = dfs_num[u] = ++dfsNumberCounter;
        S.push(u);
        visited[u] = 1;
        for (int v : adjList[u])
        {
            if (dfs_num[v] == UNVISITED)
                tarjanSCC(v);
            if (visited[v] == 1)
                dfs_low[u] = Math.min(dfs_low[u] , dfs_low[v]);
        }

        if (dfs_low[u] == dfs_num[u])
        {
            while (true)
            {
                int v = S.pop();
                visited[v] = UNVISITED;
                component[v] = numSCC;
                if (u == v) break;
            }
            rootOfComponent[numSCC] = u;
            numSCC++;
        }
    }

    public static void tarjanSCC ()
    {
        dfs_low = new int[V];
        dfs_num = new int[V];
        visited = new int[V];
        component = new int[V];
        res = new int[V];
        rootOfComponent = new int[V];
        S = new Stack<>();
        dfsNumberCounter = 0;
        numSCC = 0;
        for (int i = 0 ; i < V ; ++i)
            if (dfs_num[i] == UNVISITED)
                tarjanSCC(i);
    }

    static int NOT (int x) {return x ^ 1;}

    // acceptable values: (0, 1), (1, 0), (1, 1)
    static void or(int a, int b) {
        adjList[NOT(a)].add(b);
        adjList[NOT(b)].add(a);
    }

    // acceptable values: (0, 1), (1, 0)
    static void or_but_not_both(int a, int b) {
        // CNF: (a v b) & (!a v !b)  ==> E.g. can't be true for (1, 1) / (0, 0)
        or(a, b);
        or(NOT(a), NOT(b));
    }

    static void force_value(int i, boolean b) {
        // because after reversed toposort, I give true to what comes first
        if (b)
            adjList[NOT(i)].add(i);
        else
            adjList[i].add(NOT(i));
    }

    static boolean is_solvable() {
        for (int i = 0; i < V; i += 2)
            if (component[i] == component[NOT(i)])
                return false;
        return true;
    }

    static void assign_values() {
        int comp_result [] = new int[numSCC];
        Arrays.fill(comp_result, -1);
        for (int i = 0; i < numSCC; i++) {
            if (comp_result[i] == -1) {
                comp_result[i] = 1;
                int not_ithcomp = component[NOT(rootOfComponent[i])];
                comp_result[not_ithcomp] = 0;
            }
        }

        for (int i = 0; i < V; i++) res[i] = comp_result[component[i]];
    }

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            V = 2 * sc.nextInt();
            int E = sc.nextInt();
            if (V == 0 && E == 0) break;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
            while (E-- > 0) {
                String first = sc.next(), second = sc.next();
                int pairN1 = Integer.parseInt(first.substring(0, first.length() - 1));
                int pairN2 = Integer.parseInt(second.substring(0, second.length() - 1));
                boolean isMan1 = first.charAt(first.length() - 1) == 'h';
                boolean isMan2 = second.charAt(second.length() - 1) == 'h';
                pairN1 <<= 1;
                pairN2 <<= 1;
                if (isMan1) pairN1 ^= 1;
                if (isMan2) pairN2 ^= 1;
                or(pairN1, pairN2);
            }
            force_value(0, true);

            tarjanSCC();
            if (is_solvable()) {
                assign_values();
                int bride = res[0];
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i = 1; i < V; i++) {
                    if (res[i] == bride) {
                        if ((i & 1) == 1) {
                            arrayList.add((i / 2) + "h");
                        } else {
                            arrayList.add((i / 2) + "w");
                        }
                    }
                }
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i > 0) out.print(" ");
                    out.print(arrayList.get(i));
                }
                out.println();
            } else {
                out.println("bad luck");
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
