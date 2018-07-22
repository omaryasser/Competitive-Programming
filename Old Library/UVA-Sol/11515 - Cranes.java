import java.io.*;
import java.util.*;


public class B {


    static Circle [] circles;
    static int C;

    static long memo [][];
    static long dp (int idx , int mask)
    {
        if (idx == C) return 0;
        if (memo[idx][mask] != -1) return memo[idx][mask];

        if ((mask & (1 << idx)) == 0) return memo[idx][mask] = dp(idx +1 , mask);

        int tmpMask= mask;
        for (int i = idx + 1 ; i < C ; ++i)
            if (circles[i].intersect(circles[idx]))
                tmpMask &= ~(1 << i);

        return memo[idx][mask] = Math.max(dp(idx +1 , mask) , circles[idx].r * circles[idx].r + dp(idx + 1 , tmpMask));
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T --  > 0)
        {
            C = sc.nextInt();
            circles = new Circle[C];
            memo = new long[C][(1 << 15)];
            for (int i = 0 ; i < C ; ++i) Arrays.fill(memo[i] , -1);
            for (int i = 0 ; i < C ; ++i)
                circles[i] = new Circle(new Point(sc.nextInt() , sc.nextInt() ), sc.nextInt());

            out.printf("%d\n" , dp (0 , (1 << C) - 1));
        }
        out.flush();
        out.close();
    }

    static class Point
    {
        int x , y;
        Point (int xx , int yy){x = xx; y =yy;}
        public double dist(Point p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)); }

        static double sq(double x) { return x * x; }
    }
    static class Circle
    {

        Point c;
        long r;
        Circle (Point cc , long rr) {c = cc ; r = rr;}

        boolean intersect(Circle cir)
        {
            return c.dist(cir.c) <= r + cir.r ;
        }
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
