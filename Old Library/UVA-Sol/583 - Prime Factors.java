import java.io.*;
import java.util.*;


public class Main {

    static int primes [] ;
    static int MAX = 46341;
    static int N = 0;
    public static void seive ()
    {
        boolean primess [] = new boolean[MAX + 1];
        primess [0] = primess [1] = true;
        primess [2] = false;
        for (int i = 3 ; i * i <= MAX ; i += 2)
            if (!primess[i])
                for (int j = i + i ; j <= MAX ; j += i)
                    primess[j] = true;

        primes = new int[MAX];
        for (int i = 2 ; i <= MAX ; ++i)
            if (!primess[i])
                primes[N++] = i;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        while (true)
        {
            int n = sc.nextInt();
            if (n == 0) break;

            boolean isNeg = n < 0 ;

            ArrayList<Integer> factors = factors (Math.abs(n));
            out.printf("%d =" , n);
            if (isNeg) out.printf(" %d" , -1);
            for (int i = 0 ; i < factors.size() ; ++i)
                if (i == 0 && !isNeg)
                    out.printf(" %d" , factors.get(i));
                else out.printf(" x %d" , factors.get(i));
            out.printf("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }

    static ArrayList<Integer> factors (int n)
    {
        ArrayList<Integer> res = new ArrayList<>();
        for (int PF_IDX = 0 , PF = 2 ; PF * PF <= n ; PF = primes[++PF_IDX])
        {
            if (n % PF == 0)
            {
                while (n % PF == 0){
                    res.add(PF);
                    n /= PF;
                }
            }
            if (PF_IDX == N - 1) break;
        }

        if (n != 1) res.add(n);
        return  res;
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
