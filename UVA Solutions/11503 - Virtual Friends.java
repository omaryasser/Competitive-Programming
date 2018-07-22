
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int N = sc.nextInt();
            TreeMap<String , Integer> map = new TreeMap<String , Integer>(); int cnt = 0;
            UnionFind UF = new UnionFind(2 * 100001);
            while (N -- > 0)
            {
                String first = sc.next() , second = sc.next();
                if (!map.containsKey(first)) map.put(first , cnt ++ );
                if (!map.containsKey(second)) map.put(second , cnt ++);
                UF.unionSet(map.get(first) , map.get(second));
                out.printf("%d\n" , UF.sizeOfSet(map.get(second)));
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

        int sizeOfSet(int i) {return setSize[findSet(i)];}
    }

    static  class Scanner
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
