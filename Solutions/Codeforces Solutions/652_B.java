import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();
        Arrays.sort(a);
        for (int i = 0, l = 0, r = n - 1; i < n; i++) {
            if (i > 0) out.print(" ");
            if (i % 2 == 0) out.print(a[l++]);
            else out.print(a[r--]);
        }
        out.println();

        out.flush();
        out.close();
    }


    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        boolean ready () throws IOException {
            return br.ready();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }

}
