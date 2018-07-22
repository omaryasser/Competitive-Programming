
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N , M;
    static boolean taken [] ;
    static int p [] , q [];
    static int last1 , last2 ;
    static boolean found;
    public static void search (int second , int done)
    {
        if(done == N) {
            if (second == last1) found = true ;
            return;
        }
        for(int i = 0 ; i < M ; ++i)
            if(!taken[i] && p[i] == second)
            {
                taken[i] = true;
                search(q[i] , done + 1);
                taken[i] = false;
            }
            else if (!taken[i] && q[i] == second)
            {
                taken[i] = true;
                search(p[i] , done + 1);
                taken[i] = false;
            }
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            N = sc.nextInt(); if (N == 0) break; M = sc.nextInt();

            int first1 = sc.nextInt() , first2 = sc.nextInt();
            last1 = sc.nextInt(); last2 = sc.nextInt();
            p = new int[M]; q = new int[M]; taken = new boolean[M];
            for(int i= 0 ; i < M ; ++i) {p[i] = sc.nextInt(); q[i] = sc.nextInt();}
            found = false;
            search (first2 , 0);
            if(found) out.print("YES\n");
            else out.print("NO\n");
        }
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
