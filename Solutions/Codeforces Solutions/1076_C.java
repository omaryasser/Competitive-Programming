import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) throws Exception {

        double EPS = 1e-7;
        int testCases = sc.nextInt();
        while(testCases-- > 0) {
            int d = sc.nextInt();
            double sqrt = Math.sqrt(d);
            double lo = 0, hi = sqrt;
            for(int dumy = 0; dumy < (int)5e4; dumy++){
                double mid = (lo + hi) / 2;
                if(mid * (d - mid) > d){
                    hi = mid;
                }else lo = mid;
            }


            if(Math.abs(lo * (d - lo) - d) < EPS){
                out.printf("Y %.10f %.10f\n", lo, d - lo);
            }else {
                out.printf("N\n");
            }
        }




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