import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) throws Exception {

        long n = sc.nextLong();
        long found = -1;
        for(long i = 2; i * i <= n; i++){
            if(n % i == 0) {
                found = i;
                break;
            }
        }
        if(found == -1){
            out.println(1);
        }else {
            n  -= found;
            out.println(1 + n / 2);
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