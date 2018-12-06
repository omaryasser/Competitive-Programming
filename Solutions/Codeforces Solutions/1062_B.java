import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int max = 0;
        int res = 1;
        int tmp = n;
        for(int i = 2; 1L * i * i <= n; i++){
            if(n % i == 0){
                int cnt = 0;
                while(n % i == 0){
                    cnt++;
                    n /= i;
                }
                res *= i;
                int log = 0, pow = 1;
                while(cnt > pow){log++; pow <<= 1;}
                max = Math.max(max, log);

            }
        }

        boolean ok = true;
        for(int i = 0; i < max; i++){
            int sqrt = (int)Math.sqrt(tmp);
            while (sqrt * sqrt < tmp) sqrt++;
            while (sqrt * sqrt > tmp) sqrt--;
            if(sqrt * sqrt != tmp) ok = false;
            tmp = sqrt;
        }
        if(n != 1){
            res *= n;
        }
        out.println(res + " " + (!ok ? max + 1 : max));
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