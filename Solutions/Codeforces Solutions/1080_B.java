import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        int q = sc.nextInt();
        while (q-- > 0){
            long l = sc.nextLong(), r = sc.nextLong();
            if(l % 2 == 0){
                if(r % 2 == 1){
                    out.println(-(r - l + 1) / 2);
                }else {
                    out.println(-(r - l) / 2 + r);
                }
            }else {
                if(r % 2 == 0){
                    out.println((r - l + 1) / 2);
                }else {
                    out.println((r - l) / 2 - r);
                }
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