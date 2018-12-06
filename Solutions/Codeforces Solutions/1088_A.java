import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);




    public static void main(String[] args) {

        int x = sc.nextInt();
        for(int a = 1; a <= x; a++)
            for(int b = 1; b <= x; b++){
                if(a % b== 0 && a * b > x && a / b < x){
                    out.println(a  + " "  + b);
                    out.flush();
                    return;
                }
            }

            out.println(-1);
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