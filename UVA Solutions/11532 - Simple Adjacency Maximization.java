import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {



    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int ones = sc.nextInt();
            int zeros = sc.nextInt();
            String res = "";
            while (ones > 1 && zeros != 0)
            {
                res += "1";
                --ones;
                res += "0";
                --zeros;
                res += "1";
                --ones;
            }
            if (zeros > 0 && ones != 0)
            {
                res = "0" + "1" + res;
                --zeros;
                --ones;
            }
            while (ones-- > 0) res += "1";
            sb.append(Long.parseLong(res.equals("") ? "0" : res , 2) +"\n");
        }
        out.printf(sb.toString());
        out.flush();
        out.close();
    }

    static class Scanner
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

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public boolean ready() throws IOException {return br.ready();}
    }
}
