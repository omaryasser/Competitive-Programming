import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by omar on 07/04/18.
 */
public class B {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        ArrayList<Integer> empty = new ArrayList<>();
        int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
        char[] arr = sc.next().toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                int len = 0;
                int j;
                for ( j = i; ; j++) {
                    if (j == arr.length || arr[j] == '*') {
                        len = j - i;
                        break;
                    }
                }
                empty.add(len);
                i = j;
            }
        }

        int res = 0;
        for (int nums : empty) {
            int parity = 1;
            if (a > b) {
                parity = 0;
            }
            for (int i = 0; i < nums; i++) {
                if (parity == 0) {
                    if (a > 0) {
                        a--;
                        res ++;
                    }
                } else {
                    if (b > 0) {
                        b--;
                        res++;
                    }
                }
                parity = 1 ^ parity;
            }
        }
        out.println(res);

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
