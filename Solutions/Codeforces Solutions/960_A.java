import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by omar on 07/04/18.
 */
public class First {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        char[] a = sc.next().toCharArray();
        int[] last = new int[3];
        Arrays.fill(last, -1);
        for (int i = 0; i < a.length; i++) {
            last[a[i] - 'a'] = i;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = a[i] - 1; j >= 'a'; j--) {
                if (i < last[j - 'a']) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        if (last[0] == -1 || last[1] == -1) {
            System.out.println("NO");
            return;
        }
        out.println(last[2] - last[1] == last[1] - last[0] ||
        last[2] - last[1] == last[0] + 1 ? "YES" : "NO");
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
