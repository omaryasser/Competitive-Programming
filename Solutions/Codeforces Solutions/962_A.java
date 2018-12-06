import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Created by omar on 07/04/18.
 */
public class A {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] arr=  new int[n];
        long sum = 0;
        for (int i =0 ; i < n; i++) {
            sum += arr[i] = sc.nextInt();
        }

        long sum2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += arr[i];
            if (sum2 >= (sum + 1) / 2) {
                System.out.println(i + 1);
                return;
            }
        }

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
