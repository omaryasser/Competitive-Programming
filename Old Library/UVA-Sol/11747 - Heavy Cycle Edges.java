
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static Edge []  edgeList;
    static int V , E;
    static boolean taken [] ;
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            V = sc.nextInt(); E = sc.nextInt();
            if (V == 0 && E == 0) break;

            edgeList = new Edge[E];
            taken = new boolean[E];
            for (int i = 0 ; i < E ; ++i)
                 edgeList[i] = new Edge(sc.nextInt() , sc.nextInt() , sc.nextInt());
            kruskal();
            ArrayList<Integer> left = new ArrayList<Integer>();
            for (int i = 0 ; i < E ; ++i)
                if (!taken[i]) left.add(edgeList[i].cost);
            Collections.sort(left);
            if (left.size() == 0)
                sb.append("forest");
            for (int i = 0 ; i < left.size() ; ++i)
                if (i == 0) sb.append(left.get(i));
                else sb.append(" " + left.get(i));
            sb.append("\n");
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


    static void kruskal()		//O(E log E)
    {
        shuffle(edgeList);
        Arrays.sort(edgeList);
        UnionFind uf = new UnionFind(V);
        for(int i = 0 ; i < edgeList.length && uf.numSets > 1; ++i) {
            Edge e = edgeList[i];
            if (!uf.isSameSet(e.from, e.to)) {
                taken[i] = true;
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
