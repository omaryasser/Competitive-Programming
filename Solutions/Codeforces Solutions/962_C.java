import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by omar on 07/04/18.
 */
public class C {

    static int make (long wanted, long num) {
        int lenNum = ("" + num).length();
        int lenWanted = ("" + wanted).length();
        char[] W = ("" + wanted).toCharArray(), N = ("" + num).toCharArray();
        int i, j;
        for (i = 0, j = 0; i < W.length && j < N.length; j++) {
            if (W[i] == N[j]) {
                i++;
            }
        }
        if (i != W.length) return Integer.MAX_VALUE;
        return lenNum - lenWanted;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        ArrayList<Long> squares = new ArrayList<>();
        for (long i = 1; i * i <= (long)3e9; i++) {
            squares.add(i * i);
        }

        long n = sc.nextLong();
        int min = Integer.MAX_VALUE;
        for (long nums : squares) {
            min = Math.min(min, make(nums, n));
        }
        out.println(min == Integer.MAX_VALUE ? -1 : min);
        out.flush();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader bf;

        Scanner(InputStream in) {
            bf = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
}
