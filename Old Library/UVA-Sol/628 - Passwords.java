
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static String [] words;
    static StringBuilder sb ;
    static String rule ;
    public static void printAll (StringBuilder current , int idx )
    {
        if(idx == rule.length()) {
            sb.append(current+"\n");
            return;
        }

        if(rule.charAt(idx) == '0')
            for (int i = 0 ; i < 10 ; ++i) printAll(new StringBuilder(current).append(i) , idx + 1);
        else for (int i = 0 ; i < words.length ; ++i) printAll(new StringBuilder(current).append(words[i]) , idx + 1);
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        sb = new StringBuilder();
        int t = 0 ;
        while (sc.ready())
        {
            sb.append("--\n");
            int N = sc.nextInt() ; words = new String[N];
            for(int i = 0 ; i < N ; ++i) words[i] = sc.next();

            int M = sc.nextInt();
            while (M -- > 0)
            {
                rule = sc.next();
                printAll(new StringBuilder() , 0);
            }
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
