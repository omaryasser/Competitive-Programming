

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {


    static class Pair implements  Comparable<Pair>
    {
        String name;
        int idx ;
        int rel;

        Pair (String n , int r , int i)
        {
            name = n ;
            rel = r;
            idx = i ;
        }
        @Override
        public int compareTo(Pair o) {
            return rel == o.rel ? idx - o.idx : o.rel - rel;
        }
    }
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        for (int tt = 1 ; tt <= T ; tt ++)
        {
            Pair [] pairs = new Pair[10];
            for (int i = 0 ; i < 10 ; ++i){
                pairs[i] = new Pair(sc.next() , sc.nextInt() , i);
            }

            Arrays.sort(pairs);
            int max = pairs[0].rel;
            out.printf("Case #%d:\n" , tt);
            for (int i = 0 ; i < 10 ; ++i){
                if (pairs[i].rel < max) break;
                out.printf("%s\n" , pairs[i].name);
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
