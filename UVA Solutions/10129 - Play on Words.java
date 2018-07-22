import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            int [] inDeg = new int[26], outDeg = new int[26];
            UnionFind uf = new UnionFind(26);
            HashSet<Integer> found = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                int u = s.charAt(0) - 'a', v = s.charAt(s.length() - 1) - 'a';
                found.add(u);
                found.add(v);
                uf.unionSet(u, v);
                inDeg[v]++;
                outDeg[u]++;
            }
            boolean ok = uf.numSets == 26 - found.size() + 1;
            int s = -1, e = -1;
            for (int i = 0; i < 26; i++) {
                if (inDeg[i] == outDeg[i] - 1) {
                    ok &= s == -1;
                    s = i;
                } else if (inDeg[i] - 1 == outDeg[i]) {
                    ok &= e == -1;
                    e = i;
                } else ok &= inDeg[i] == outDeg[i];
            }
            ok &= (s != -1 && e != -1 || s == -1 && e == - 1);
            if (ok) {
                out.println("Ordering is possible.");
            } else {
                out.println("The door cannot be opened.");
            }
        }

        out.flush();
        out.close();
    }

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

        void close ()
        {
            for (int i = 0; i < p.length; i++)
                findSet(i);
        }

        int sizeOfSet(int i) {return setSize[findSet(i)];}
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
