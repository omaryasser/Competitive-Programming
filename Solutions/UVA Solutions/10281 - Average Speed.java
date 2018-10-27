import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DoubleDV;

import java.io.*;
import java.util.*;

public class A {

    public static void main (String [] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        double vel = 0 , dist = 0;
        double hrs = 0;
        int n = 0 ;
        while (sc.ready())
        {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            if (st.countTokens() == 2)
            {
                StringTokenizer st2 = new StringTokenizer(st.nextToken() , ":");
                double hrs2 = Double.parseDouble(st2.nextToken());
                double min2 = Double.parseDouble(st2.nextToken());
                double sec2 = Double.parseDouble(st2.nextToken());
                double newHrs = hrs2 + min2 / 60 + sec2 / 3600;
                double diff = newHrs - hrs;
                dist += (vel * diff);
                vel = Integer.parseInt(st.nextToken());
                hrs = newHrs;
            }
            else {
                String line = st.nextToken();
                StringTokenizer st2 = new StringTokenizer(line , ":");
                double hrs2 = Double.parseDouble(st2.nextToken());
                double min2 = Double.parseDouble(st2.nextToken());
                double sec2 = Double.parseDouble(st2.nextToken());
                double newHrs = hrs2 + min2 / 60 + sec2 / 3600;
                double diff = newHrs - hrs;
                dist += (vel * diff);
                out.printf("%s %.2f km\n" , line , dist);
                hrs = newHrs;
            }
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
