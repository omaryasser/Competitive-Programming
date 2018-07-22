
import java.io.*;
import java.util.*;

public class Main {

    static class Triple {
        String name ;
        int l , h;
        Triple (String n , int ll , int hh) {name = n ; l = ll ; h = hh;}
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        boolean first = true;
        while (T -- > 0)
        {
            int D = sc.nextInt();
            Triple arr [] = new Triple[D];
            for(int i = 0 ; i < D ; ++i) arr[i] = new Triple(sc.next() , sc.nextInt() , sc.nextInt());

            if (first) first = false;
            else sb.append("\n");

            int Q = sc.nextInt();
            while (Q -- > 0)
            {
                int cnt = 0;
                String res = "" ;

                int p = sc.nextInt();

                for (int i = 0 ; i < D ; ++i) if (p >= arr[i].l && p<=arr[i].h) {++cnt ; res = arr[i].name;}



                if(cnt != 1) sb.append("UNDETERMINED\n");
                else sb.append(res+"\n");
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
