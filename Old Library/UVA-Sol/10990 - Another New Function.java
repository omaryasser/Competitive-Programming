import java.io.*;
import java.util.*;


public class Main {
    static int MAX = 2000000;
    static int SDOF [] = new int[MAX + 1];
    static int coPrimes [] = new int[MAX + 1];
    static int ans (int num)
    {
        if (num == 1 || num == 2) return 1;
        return 1 + ans(coPrimes[num]);
    }
    static void EulerPhi ()
    {
        for (int i = 2 ; i <= MAX ; ++ i)
            coPrimes[i] = i ;

        for (int i = 2 ; i <= MAX ; ++i)
            if (coPrimes[i] == i)
                for (int j = i ; j <= MAX ; j += i)
                    coPrimes[j] -= coPrimes[j] / i;
        for (int i = 2 ; i <= MAX ; ++i)
            SDOF[i] = SDOF[i - 1] + ans(i);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        EulerPhi();
        int T = sc.nextInt();
        while (T -- > 0)
            out.printf("%d\n" , - SDOF[sc.nextInt() - 1] + SDOF[sc.nextInt()]);
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
