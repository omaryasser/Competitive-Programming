
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static boolean nextPermutation(char[] A) {
        for (int a = A.length - 2; a >= 0; --a) {
            if (A[a] < A[a + 1]) {
                for (int b = A.length - 1;; --b) {
                    if (A[b] > A[a]) {
                        char t = A[a];
                        A[a] = A[b];
                        A[b] = t;
                        for (++a, b = A.length - 1; a < b; ++a, --b) {
                            t = A[a];
                            A[a] = A[b];
                            A[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            char [] arr = sc.next().toCharArray();
            if(arr[0] == '#') break;
            if(nextPermutation(arr))
                for(int i = 0 ; i < arr.length ; ++i)
                    sb.append(arr[i]);
            else sb.append("No Successor");
            sb.append("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }
    static  class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException
        {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if(x.charAt(0) == '-')
            {
                neg = true;
                start++;
            }
            for(int i = start; i < x.length(); i++)
                if(x.charAt(i) == '.')
                {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                }
                else
                {
                    sb.append(x.charAt(i));
                    if(dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg?-1:1);
        }

        public boolean ready() throws IOException {return br.ready();}


    }

}
