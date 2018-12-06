import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);




    public static void main(String[] args) {

        int n = sc.nextInt();
        long[] a = new long[n];

        int wanted = (int)1e5;
        out.println(n + 1);
        long[] add = new long[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            add[i] = wanted - a[i];
            wanted++;
        }
        long cum = add[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            add[i] -= cum;
            cum += add[i];
        }
        int MOD = (int)1e5;
        for(int i = 0; i < n; i++){
            int added = (int)(((add[i] % MOD) + MOD) % MOD);
            for(int j = i; j >= 0; j--){
                a[j] += added;
            }
            out.println(1 + " " + (i + 1) + " " + added);
        }
        out.println(2 + " " + n + " " + (int)1e5);
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