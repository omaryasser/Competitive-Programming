import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        for (int t = 1 ; t <= T ; ++t){
            char [] arr = sc.nextLine().toCharArray();
            int cnt [] = new int[100];
            int i = 0;
            for (int idx = 0 ; idx < arr.length ; ++idx){
                char c = arr[idx];
                if (c == '>') ++i;
                else if (c == '<') --i;
                else if (c == '+') ++cnt[i];
                else if (c == '-') --cnt[i];

                if (i == 100) i = 0;
                if (i == -1)  i = 99;
                if (cnt[i] == -1) cnt[i] = 255;
                if (cnt[i] == 256) cnt[i] = 0;
            }
            out.printf("Case %d:" , t);
            for (int idx = 0 ; idx < 100 ; ++idx){
                String cur = Integer.toHexString(cnt[idx]).toUpperCase();
                while (cur.length() < 2) cur = "0" + cur;
                out.print(" " + cur);
            }
            out.print("\n");
        }
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
