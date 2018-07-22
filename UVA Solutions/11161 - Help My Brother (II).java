import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by omar on 22/06/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        BigInteger[] res = new BigInteger[1501];
        res[0] = BigInteger.ZERO;
        res[1] = BigInteger.ONE;
        BigInteger start = BigInteger.valueOf(2);
        BigInteger fib[] = {res[1], res[1]};
        for (int i = 2; i <= 1500; i++) {
            BigInteger curFib = fib[0].add(fib[1]);
            fib[0] = fib[1];
            fib[1] = curFib;
            BigInteger end = start.add(curFib.subtract(BigInteger.ONE));
            res[i] = (start.add(end)).divide(BigInteger.valueOf(2));
            start = end.add(BigInteger.ONE);
        }

        for (int t = 1; ; t++){
            int n = sc.nextInt();
            if (n == 0) break;
            out.printf("Set %d:\n%s\n", t, res[n - 1]);
        }

        out.flush();
        out.close();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
