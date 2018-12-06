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

        char[] a = sc.next().toCharArray();
        int aa=0,b=0;
        for (char c:a)
            if(c=='-')aa++;
            else b++;

        out.println((b==0||(aa%b==0))?"YES":"NO");

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
