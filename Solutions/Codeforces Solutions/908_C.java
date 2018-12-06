import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static double [] res;
    static double r;
    static double solve (int xMe, int xHim, int idxHim) {
        if (Math.abs(xMe - xHim) > 2 * r) return r;
        double hisY = res[idxHim];
        double lo = hisY, hi = hisY + 2 * r, best = hi;
        for (int cnt = 0; cnt <= 50; cnt++) {
            double myY = (lo) + ((hi - lo) / 2);
            if (notIntersect(xMe, myY, xHim, hisY)) {
                best = Math.min(best, myY);
                hi = Math.max(lo, myY);
            } else
                lo = Math.min(hi, myY);
        }
        return best;
    }
    static boolean notIntersect (double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) >= 2 * r  * 2 * r;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = sc.nextInt();
        res = new double[n];
        r = sc.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++)
            x[i] = sc.nextInt();

        for (int i = 0; i < n; i++) {
            double max = r;
            for (int j = 0; j < i; j++) {
                max = Math.max(max, solve(x[i], x[j], j));
            }
            if (i > 0) out.print(" ");
            res[i] = max;
            out.printf("%.10f", max);
        }
        out.println();

        out.flush();
        out.close();
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public Scanner(FileReader s) throws FileNotFoundException {	br = new BufferedReader(s);}

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
