import java.io.*;
import java.util.*;


public class A {


    static int x [];
    static int y [];
    static int w;
    static int n ;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tt = 1 ; tt <= T ; ++tt)
        {
            int l = sc.nextInt();
            w = sc.nextInt();

            n = sc.nextInt();
            x = new int[n];
            y = new int[n];
            for (int i = 0 ; i < n ; ++i)
            {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
            }

            double EPS = 1e-7;
            double lo = 0.0 , hi = w;
            while (Math.abs(lo - hi) > EPS)
            {
                double width = (lo + hi) / 2.0;
                if (ok(width))
                    lo = width;
                else hi = width;
            }
            out.printf("Maximum size in test case %d is %.4f.\n" , tt , lo);
        }
        out.flush();
        out.close();
    }

    static boolean ok (double width)
    {
        int UP = n;
        int DOWN = n + 1;
        UnionFind UF = new UnionFind(n + 2);
        double EPS = 1e-7;
        for (int i = 0 ; i < n ; ++i)
        {
            if (y[i] < width + EPS)
                UF.unionSet(i , DOWN);
            if (w - y[i]  < width + EPS)
                UF.unionSet(i , UP);
            for (int j = i + 1 ; j < n ; ++j)
                if (dist (i,j) < width * width + EPS)
                    UF.unionSet(i , j);
        }

        if (UF.isSameSet(n , n + 1)) return false;
        return true;
    }

    static double dist (int i , int j) {return ((x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]));}








    static class UnionFind {
        private int [] p , rank , setSize;
        private int numSets ;
        public UnionFind(int size)
        {
            p = new int[size] ;
            rank = new int[size] ;
            setSize = new int[size];
            numSets = size ;
            for(int i = 0 ; i < size ; ++i) {
                p[i] = i;
                setSize[i] = 1;
            }
        }

        int findSet(int i ) {return (p[i] == i)? i : (p[i] = findSet(p[i]));}
        boolean isSameSet(int i , int j) {return findSet(i) == findSet(j);}
        void unionSet(int i , int j)
        {
            if(!isSameSet(i,j))
            {
                --numSets;
                int x = findSet(i) , y = findSet(j);
                if(rank[x] > rank[y]) {p[y] = x; setSize[x] = setSize[x] + setSize[y];}
                else
                {
                    p[x] = y;
                    setSize[y] = setSize[y] + setSize[x];
                    if(rank[x] == rank[y]) ++rank[y];
                }
            }
        }

        int numDisjointSets ()
        {
            return numSets;
        }

        int sizeOfSet(int i) {return setSize[findSet(i)];}
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
