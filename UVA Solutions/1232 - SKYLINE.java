
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
            long cnt = 0 ;
            SegmentTree segmentTree = new SegmentTree();

            while (N -- > 0) cnt += (segmentTree.query(sc.nextInt() , sc.nextInt() - 1 , sc.nextInt()) );

            out.printf("%d\n" , cnt);
        }
        sc.nextInt();
        out.flush();
        out.close();
    }


    static class Node {
        int max , min ;
        Node (int x , int n) {max = x ; min = n;}
    }
    static class SegmentTree {
        int N ;
        Node [] tree ; int []  lazy;

        SegmentTree() {
            N = 100000;
            int n = 1;
            while (n <= N) n<<=1;
            tree = new Node[(n << 1) << 1];
            lazy = new int[(n << 1) << 1];
            for(int i = 0 ; i < (n << 1) ; ++i)
                tree[i] = new Node (0,0);
        }

        int query (int left , int right , int val) {return query(1 , 1 , N , left , right , val);}
        int query (int node , int start , int end , int l , int r , int val)
        {
            if(lazy[node]!=0) propagate(node);

            if(start >= l && end <= r)
                if(val >= tree[node].max)
                {
                    tree[node].max = tree[node].min = val ;
                    lazy[node << 1] = lazy[node << 1 | 1] = val;
                    lazy[node << 1] = lazy[node << 1 | 1] = val;
                    return end - start + 1 ;
                }

                else if (val < tree[node].min) return 0;

            if(start == end || start > r || end < l)  return 0;


            int mid = start + end >> 1;
            int res = query(node << 1 , start , mid , l , r , val) + query(node << 1 | 1 , mid + 1 , end , l , r , val);
            tree[node].max = Math.max(tree[node << 1].max , tree[node << 1 | 1].max);
            tree[node].min = Math.min(tree[node << 1].min , tree[node << 1 | 1].min);
            return res ;
        }

        void propagate (int node)
        {
            tree[node].max = Math.max(tree[node].max , lazy[node]);
            tree[node].min = Math.max(tree[node].max , lazy[node]);
            lazy[node << 1] = lazy[node << 1 | 1] = lazy[node];
            lazy[node] = 0;
        }
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
