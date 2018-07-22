import java.io.*;
import java.util.*;

public class Main{

    public static void main (String [] args) throws Exception
    {
        Scanner sc= new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while(true)
        {
            int N = sc.nextInt();
            if(N == 0) break;
            int Q = sc.nextInt();
            int arr [] = new int[N];
            for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();

            SegmentTree segmentTree = new SegmentTree(arr);

            while(Q-- > 0)
                out.println(segmentTree.query(sc.nextInt() - 1 , sc.nextInt() - 1) .max);
        }

        out.flush();
        out.close();
    }

    static class Node {
        int maxLeft , maxMid , maxRight , max;
        Node (int l , int m , int r , int ma){maxLeft = l ; maxMid = m ; maxRight = r; max = ma;}
    }

    static public class SegmentTree {
        Node[] tree ; int [] A ;
        int N;

        SegmentTree(int[] in) {
            A = in.clone();
            N = A.length;
            tree = new Node[(N << 1) << 1];
            build(1, 0, N - 1);
        }

        void build(int node, int start, int end)
        {
            if(start == end)
                tree[node] = new Node(1,1,1,1);

            else
            {
                int mid = mid(start , end);

                build(left(node), start, mid);
                build(right(node), mid+1, end);

                Node leftNode = tree[left(node)] , rightNode = tree[right(node)];

                int left  , right  , maxMid  ;
                if(A[start] == A[mid + 1])
                    left = leftNode.maxLeft + rightNode.maxLeft;
                else left = leftNode.maxLeft;

                if(A[mid] == A[end])
                    right = rightNode.maxLeft + leftNode.maxRight;
                else right = rightNode.maxRight;

                if(A[mid] == A[mid+1])
                    maxMid = leftNode.maxRight + rightNode.maxLeft;
                else maxMid = Math.max(leftNode.maxRight , rightNode.maxLeft);
//                System.out.println("start = " + start + "  end = " + end + "  left = " + left + "  right = " + right + " max = "+max);
                tree[node] = new Node(left , maxMid , right , Math.max(left , Math.max(maxMid , Math.max(right , Math.max(leftNode.max , rightNode.max)))));
            }
        }



        Node query(int l , int r) {return query(1 , 0 , N-1 , l , r);}
        Node query(int node, int start, int end, int l, int r)
        {
            if(start > r || end < l)
                return new Node(0 ,0 ,0 ,0 );

            if(start >= l && end <= r)
                return tree[node];

            int mid = mid(start , end);

            Node leftNode = query(left(node) , start , mid , l , r) , rightNode = query(right(node) , mid + 1 , end , l , r);

            int left  , right  , maxMid  ;
            if(A[start] == A[mid + 1])
                left = leftNode.maxLeft + rightNode.maxLeft;
            else left = leftNode.maxLeft;

            if(A[mid] == A[end])
                right = rightNode.maxLeft + leftNode.maxRight;
            else right = rightNode.maxRight;

            if(A[mid] == A[mid+1])
                maxMid = leftNode.maxRight + rightNode.maxLeft;
            else maxMid = Math.max(leftNode.maxRight , rightNode.maxLeft);
//                System.out.println("start = " + start + "  end = " + end + "  left = " + left + "  right = " + right + " max = "+max);
             return new Node(left , maxMid , right , Math.max(left , Math.max(maxMid , Math.max(right , Math.max(leftNode.max , rightNode.max)))));
        }
        static int mid (int start , int end) {return (start+end) >> 1;}
        static int left (int pos ) {return pos << 1;}
        static int right (int pos ) {return (pos << 1)+1;}


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
