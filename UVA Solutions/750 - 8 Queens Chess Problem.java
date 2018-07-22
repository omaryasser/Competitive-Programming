
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static PrintWriter out = new PrintWriter(System.out);
    static int requiredR;
    static int requiredC;
    static int answerID;
    static int row [];

    static void backtrack (int col){
        if (col == 8){
            if (row [requiredC] == requiredR){
                out.printf("%2d     " , answerID ++);
                for(int i = 0; i < 8; i++)
                    out.printf(" %d", row[i] + 1);
                out.print("\n");
            }
            return;
        }
        for (int i = 0 ; i < 8 ; ++i){
            if (valid (i , col)){
                row[col] = i;
                backtrack(col + 1);
            }
        }
    }

    static boolean valid (int R , int C){
        for (int i = 0 ; i < C ; ++i){
            if (row[i] == R || Math.abs(R - row[i]) == Math.abs(C - i)) return false;
        }
        return true;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        boolean first = true;
        while (T -- > 0){
            requiredR = sc.nextInt() - 1;
            requiredC = sc.nextInt() - 1;
            answerID = 1;
            row = new int[8];
            Arrays.fill(row , - 1);


            if (first) first = false;
            else out.print("\n");
            out.print("SOLN       COLUMN\n #      1 2 3 4 5 6 7 8\n\n");
            backtrack (0);
        }
        out.flush();
        out.close();
    }















    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
