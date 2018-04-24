
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class Main {
    static int N ;
    static int H ;

    static String makeString (int mask){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; ++i)
            if (((1 << i) & mask) != 0) sb.append('1');
            else sb.append('0');
        return sb.toString();
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        PrintWriter out = new PrintWriter(System.out);
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        boolean first = true;
        while (T -- > 0){
            if (first) first = false;
            else sb.append("\n");
            N = sc.nextInt();
            H = sc.nextInt();
            int mask = (1 << N) - 1;
            ArrayList<String> validStrings = new ArrayList<>();
            while (mask >= 0){
                int ones = 0 ;
                for (int i = 0 ; i < N ; ++i)
                    if (((1 << i) & mask) != 0)
                        ++ ones;
                if (ones == H) validStrings.add(makeString(mask));
                mask -- ;
            }
            Collections.sort(validStrings);
            for (int i = 0 ; i < validStrings.size() ; ++i)
                sb.append(validStrings.get(i) + "\n");
        }
        out.print(sb);
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
