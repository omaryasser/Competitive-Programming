import java.io.*;
import java.util.*;


public class Main {


    static int MAX_N ;
    static char T[] , P[];
    static int b[] , n , m;
    static int cnt = 0 ;
    public static void kmpPreProcess()
    {
        m = P.length ;
        b = new int[m + 1];
        int i = 0 , j = -1 ; b[0] = -1;
        while(i < m)
        {
            while(j >= 0 && P[i] != P[j]) j = b[j];
            i++; j++;
            b[i] = j;
        }
    }

    public static void kmpSearch()
    {
        int i =0 , j =0;
        n = T .length ;
        while(i < n)
        {

            while(j >= 0 && T[i] != P[j]) j = b[j];
            i++; j++;
            if(j == m) {
                ++cnt;
                j = b[j];
            }

        }
    }

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int tt = sc.nextInt();
        while (tt -- > 0)
        {
            String inp = sc.next();
            P = inp.toCharArray();
            T = (inp + inp).toCharArray();
            cnt = 0;
            kmpPreProcess();
            kmpSearch();

            out.printf("%d\n" , (P.length / (cnt - 1)));
            if (tt != 0)
                out.printf("\n");
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
