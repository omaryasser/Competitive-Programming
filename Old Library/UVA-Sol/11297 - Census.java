
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int arr [] = new int[N * N];
        for(int i= 0 ; i < N * N; ++i) arr[i] = sc.nextInt();
        SegmentTree segmentTree = new SegmentTree(arr , N);
        int Q = sc.nextInt();
        while (Q -- > 0)
        {
            char q = sc.next().charAt(0);
            if(q == 'q'){
                int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1, x2 = sc.nextInt() - 1, y2 = sc.nextInt() - 1;

                int max = Integer.MIN_VALUE , min = Integer.MAX_VALUE;

                for(int i = x1 ; i <= x2 ; ++i)
                {
                    max = Math.max(max , segmentTree.query(1 , 0 , N * N - 1 , i * N + y1 , i * N + y2).max);
                    min = Math.min(min , segmentTree.query(1 , 0 , N * N - 1 , i * N + y1 , i * N + y2).min);
                }

                out.printf("%d %d\n",max , min);
            }
            else {
                segmentTree.update(sc.nextInt() - 1 , sc.nextInt() - 1, sc.nextInt());
            }

        }
        out.flush();
        out.close();
    }


    static class Node {
        int max , min ;
        Node(int maxx , int minn) {max = maxx ; min = minn;}
    }

    static class SegmentTree {
        int [] A; Node [] tree ;
        int N , COL;
        SegmentTree (int arr [] , int col)
        {
            A = arr;
            N = arr.length;
            COL = col;
            tree = new Node[(N << 1) << 1];
            build (1 , 0 , N - 1);
        }

        void build (int node , int start , int end)
        {
            if(start == end) tree[node] = new Node(A[start] , A[start]);

            else
            {
                int mid = start + end >> 1;
                build(node << 1 , start , mid);
                build(node << 1 | 1 , mid + 1 , end);
                tree[node] = new Node(Math.max(tree[node << 1].max , tree[node << 1 | 1].max) ,
                                      Math.min(tree[node << 1].min , tree[node << 1 | 1].min));
            }
        }

        Node query (int node , int start , int end , int l , int r)
        {
            if(start >= l && end <= r)  return tree[node];


            if(start > r || end < l) return new Node(Integer.MIN_VALUE , Integer.MAX_VALUE);

            int mid = start + end >> 1;
            Node first = query(node << 1 , start , mid , l , r) , second = query(node << 1 | 1 , mid + 1 , end , l , r);
            return new Node(Math.max(first.max , second.max) ,
                            Math.min(first.min , second.min));
        }

        void update (int x , int y , int val) {update(1 , 0 , N - 1 , x * COL + y , val);}
        void update (int node , int start , int end , int idx , int val)
        {
            if(start == end && start == idx) {
                tree[node] = new Node(val , val);
                return;
            }
            else if (start == end) return;

            int mid = start + end >> 1;

            if(idx <= mid) update(node << 1 , start , mid , idx , val);
            else update(node << 1 | 1 , mid + 1 , end , idx , val);

            tree[node] = new Node (Math.max(tree[node << 1].max , tree[node << 1 | 1].max) ,
                                   Math.min(tree[node << 1].min , tree[node << 1 | 1].min));
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
