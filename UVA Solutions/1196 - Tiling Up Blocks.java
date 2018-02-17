import java.io.*;
import java.util.*;


public class C {

    static class Block implements  Comparable<Block>{
        int l , r;
        Block (int ll , int rr)
        {
            l = ll;
            r = rr;
        }

        @Override
        public int compareTo(Block block) {
            return l == block.l ? r - block.r : l - block.l;
        }
    }

    public static void shuffle(Block[] a)
    {
        int n = a.length;
        for(int i = 0; i < n; i++)
        {
            int r = i + (int)(Math.random() * (n - i));
            Block tmp = new Block(a[i].l , a[i].r);
            a[i] = new Block(a[r].l , a[r].r);
            a[r] = tmp;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {

            int n = sc.nextInt();
            if (n == 0) break;

            Block blocks [] = new Block[n];
            for (int i = 0 ; i < n ; ++i) blocks[i] = new Block(sc.nextInt() , sc.nextInt());
            shuffle(blocks);
            Arrays.sort(blocks);


            int dp [] = new int[n];
            Arrays.fill(dp , 1);
            int max = 0 ;
            for (int i = 0 ; i < n ; ++i)
                for (int j = i + 1 ; j < n ; ++j)
                    if (blocks[j].r >= blocks[i].r)
                    {
                        dp[j] = Math.max(dp[j] , dp[i] + 1);
                        max = Math.max(max , dp[j]);
                    }
            out.printf("%d\n" , max);
        }
        out.print("*\n");
        out.flush();
        out.close();
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
