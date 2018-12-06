import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] a = new int[n + 2];
        a[0] = 0;
        a[n + 1] = 1001;
        for(int i = 1; i <= n; i++)a[i] = sc.nextInt();
        int max = 0;
        for(int i = 1; i <= n; i++){
            for(int j = i; j <= n; j++){
                boolean ok = true;
                for(int k = i; k <= j; k++){
                    if(k > i)ok &= a[k] == a[k - 1] + 1;
                    if(k < j)ok &= a[k] == a[k + 1] - 1;
                }
                ok &= a[j + 1] - a[i - 1] == j - i + 2;
                if(ok)max = Math.max(max, j - i + 1);
            }
        }

        out.println(max);
        out.flush();
        out.close();
    }












    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        public Scanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public Scanner(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}