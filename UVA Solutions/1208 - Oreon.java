
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Edge> edgeList;
    static int V;
    static ArrayList<Result> res;

    static class Result implements  Comparable<Result>{
        int first , second , weight;
        Result(int f , int s , int w) {first = f ; second = s ; weight = w;}

        @Override
        public int compareTo(Result o) {
            return weight != o.weight? weight - o.weight : first!=o.first ? first - o.first : second - o.second;
        }
    }

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        for (int t = 1 ; t <= T ; ++t)
        {
            V = Integer.parseInt(sc.nextLine());
            edgeList = new ArrayList<>();
            res = new ArrayList<>();
            for (int i = 0 ; i < V ; ++i)
            {
                StringTokenizer st = new StringTokenizer(sc.nextLine() , ", ");
                for (int j = 0 ; j < V ; ++j)
                {
                    int w = Integer.parseInt(st.nextToken());
                    if (w > 0)
                        edgeList.add(new Edge(i , j , w));
                }
            }
            kruskal();
            Collections.sort(res);
            sb.append("Case "+t+":\n");
            for (int i = 0 ; i < res.size() ; ++i)
                sb.append((char)(res.get(i).first + 'A')+"-"+(char)(res.get(i).second + 'A')+ " " + res.get(i).weight + "\n");
        }
        out.printf(sb.toString());
        out.flush();
        out.close();
    }


    static int kruskal()		//O(E log E)
    {
        int mst = 0;
        Collections.sort(edgeList);
        UnionFind uf = new UnionFind(V);

        for(int i = 0 ; i < edgeList.size() && uf.numSets > 1; ++i) {
            Edge e = edgeList.get(i);
            if (!uf.isSameSet(e.from, e.to)) {
                res.add(new Result(e.from , e.to , e.cost));
                mst += e.cost;
                uf.unionSet(e.from, e.to);
            }
        }

        return mst;
    }

    static class Edge implements Comparable<Edge>
    {
        int from, to, cost;

        Edge(int a, int b, int c)
        {
            from = a; to = b; cost = c;
        }

        public int compareTo(Edge e)
        {
            if(cost != e.cost)
                return cost - e.cost;
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
