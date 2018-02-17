
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static Edge []  edgeList;
    static ArrayList<Pair> adjList [];
    static int V , E;
    static boolean visited [] ;
    static boolean found ;
    static int max ;
    public static void dfs (int u , int end , int ans)
    {
        visited[u] = true;
        if (u == end) {
            found = true;
            max = ans;
        }
        for (Pair v : adjList[u])
            if (!visited[v.to])
                dfs(v.to , end , Math.max(ans , v.weight));
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int T = 1 ; ; T ++)
        {
            V = sc.nextInt(); E = sc.nextInt() ; int  Q = sc.nextInt();
            if (V == 0 && E == 0 && Q == 0) break;
            adjList = new ArrayList[V];
            for (int i = 0 ; i < V ; ++i) adjList[i] = new ArrayList<>();
            edgeList = new Edge[E];
            for (int i = 0 ; i < E ; ++i)
                edgeList[i] = new Edge(sc.nextInt() - 1 , sc.nextInt() - 1 , sc.nextInt());
            kruskal();

            if (first) first = false;
            else out.printf("\n");
            out.printf("Case #%d\n" , T);
            while (Q --  > 0)
            {
                int u = sc.nextInt() , v = sc.nextInt();
                found = false;
                max = (int)- 1e9;
                visited = new boolean[V];
                dfs (u - 1,v - 1 , (int)-1e9);
                if (!found) out.printf("no path\n");
                else out.printf("%d\n" , max);
            }
        }
        out.printf(sb.toString());
        out.flush();
        out.close();
    }


    public static void shuffle(Edge[] a)
    {
        int n = a.length;
        for(int i = 0; i < n; i++)
        {
            int r = i + (int)(Math.random() * (n - i));
            Edge tmp = new Edge(a[i].from , a[i].to , a[i].cost);
            a[i] = new Edge(a[r].from , a[r].to , a[r].cost);
            a[r] = tmp;
        }
    }

    static class Pair {
        int to , weight ;
        Pair (int t , int w) {to = t ; weight = w;}
    }
    static void kruskal()		//O(E log E)
    {
        shuffle(edgeList);
        Arrays.sort(edgeList);
        UnionFind uf = new UnionFind(V);
        for(int i = 0 ; i < edgeList.length && uf.numSets > 1; ++i) {
            Edge e = edgeList[i];
            if (!uf.isSameSet(e.from, e.to)) {
                adjList[e.from].add(new Pair (e.to , e.cost));
                adjList[e.to].add(new Pair (e.from , e.cost));
                uf.unionSet(e.from, e.to);
            }
        }

    }

    static class Edge implements Comparable<Edge>
    {
        int from, to ,   cost;

        Edge(int a, int b, int c)
        {
            from = a; to = b; cost = c;
        }

        public int compareTo(Edge e)
        {
            if(cost != e.cost)
                return cost - e.cost > 0 ? 1 : - 1;
            if(from != e.from)
                return from - e.from;
            return to - e.to;
        }
    }

    static class UnionFind {
        int[] p, rank, setSize;
        int numSets;

        public UnionFind(int N)
        {
            p = new int[N];
            rank = new int[N];
            setSize = new int[N];
            numSets = N;
            for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
        }

        public int findSet(int i)
        {
            if (p[i] == i) return i;
            else return p[i] = findSet(p[i]);
        }

        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        public void unionSet(int i, int j)
        {
            if (isSameSet(i, j))
                return;
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
            else
            {	p[x] = y; setSize[y] += setSize[x];
                if(rank[x] == rank[y]) rank[y]++;
            }
        }

        public int numDisjointSets() { return numSets; }

        public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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

        public double nextDouble() throws IOException
        {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if(x.charAt(0) == '-')
            {
                neg = true;
                start++;
            }
            for(int i = start; i < x.length(); i++)
                if(x.charAt(i) == '.')
                {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                }
                else
                {
                    sb.append(x.charAt(i));
                    if(dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg?-1:1);
        }

        public boolean ready() throws IOException {return br.ready();}


    }
}
