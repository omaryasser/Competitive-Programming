import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int MAX = (int)1e5 + 10, MOD = (int)1e9 + 7;
        int sum_powers[] = new int[MAX];
        int powers[] = new int[MAX];

        powers[0] = 1; sum_powers[0] = 1;
        for(int i = 1; i < MAX; i++){
            powers[i] = (int)(1L * powers[i - 1] * 2 % MOD);
            sum_powers[i] = sum_powers[i - 1] + powers[i];
            if (sum_powers[i] >= MOD) sum_powers[i] -= MOD;
        }


        int n = sc.nextInt(), q = sc.nextInt();
        int[] arr = new int[n], ones = new int[n];
        char[] x = sc.next().toCharArray();
        for(int i = 0; i < n; i++){
            arr[i] = x[i] - '0';
            ones[i] = arr[i];
            if(i > 0)ones[i] += ones[i - 1];
        }

        while (q-- > 0){
            int l = sc.nextInt() - 1, r = sc.nextInt() - 1;
            int ones_cnt = sum(ones, l, r), zeros_cnt = r - l + 1 - ones_cnt;
            if(ones_cnt == 0){
                out.println(0);
                continue;
            }
            int res = sum_powers[ones_cnt - 1];
            int res2 = (int)(1L * sum_powers[ones_cnt - 1] * (zeros_cnt == 0 ? 0 : sum_powers[zeros_cnt - 1]) % MOD);
            out.println((res + res2) % MOD);
        }

        out.flush();
        out.close();
    }

    static int sum (int[] a, int l, int r){
        return a[r] - (l == 0 ? 0 : a[l - 1]);
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