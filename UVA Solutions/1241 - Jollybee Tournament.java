import java.io.*;
import java.util.*;


public class I {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = 1 << sc.nextInt(), m = sc.nextInt();
            boolean absent[] = new boolean[n];
            for (int i = 0; i < m; ++i)
                absent[sc.nextInt() - 1] = true;
            SegmentTree segmentTree = new SegmentTree(n, absent);
            System.out.print(segmentTree.ans()+"\n");
        }
        out.flush();
        out.close();
    }

    static class Pair
    {
        int people , ans;
        Pair (int p , int a )
        {
            people = p;
            ans = a;
        }
        Pair()
        {

        }
    }
    static class SegmentTree {
        boolean arr[]; Pair tree[];
        int n ;

        SegmentTree(int nn, boolean[] arrr) {
            n = nn;
            arr = arrr;
            tree = new Pair[(n << 1) << 1];
            for (int i = 0 ; i < (n << 1) << 1 ; ++i)
                tree[i] = new Pair(1,0);

            build (1 , 0 , n - 1);
//            System.out.println(tree[3].people + " " + tree[6].people + " "  +tree[7].people);

        }

        void build (int node , int start , int end)
        {
            if (start == end)
                tree [node] = new Pair(arr[start] ? 0 : 1 , 0);

            else
            {
                int mid = (start + end) >> 1;
                build(node << 1 , start , mid);
                build(node << 1 | 1 , mid + 1, end);
                Pair cur = new Pair();
                cur.ans = tree[node << 1].ans + tree[node << 1| 1].ans + (((tree[node << 1].people == 0 && tree[node << 1 | 1].people == 0)
                                                                        || (tree[node << 1].people == 1 && tree[node << 1 | 1].people == 1)) ? 0 : 1);

                cur.people = (tree[node << 1].people == 1 || tree[node << 1 |1 ].people == 1 )? 1 : 0;
                tree[node] = cur;
            }
        }

        int ans () {return tree[1].ans;}
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
