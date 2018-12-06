import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Created by omar on 07/04/18.
 */
public class A {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        TreeSet<String>st = new TreeSet<>();
        for (int i =0 ; i < n;i++){
            char[] a = sc.next().toCharArray();
            TreeSet<Character> s = new TreeSet<>();
            for (char c : a) s.add(c);
            String r = "";
            for (char ss :s) {
                r+=ss+"";
            }
            st.add(r);
        }
        out.println(st.size());

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
