import java.io.*;
import java.util.*;


public class Main {

    static int primes [] ;
    static int MAX = 3500;
    static int N = 0;
    public static void seive ()
    {
        boolean primess [] = new boolean[MAX + 1];
        primess [0] = primess [1] = true;
        for (int i = 2 ; i * i <= MAX ; ++i)
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
            String [] inp = sc.nextLine().split(" ");
            if (inp.length == 1) break;
            int num = 1 ;
            for (int i = inp.length - 1 ; i>= 0 ; i-=2)
                num *= pow(Integer.parseInt(inp[i -1]) , Integer.parseInt(inp[i]));

            ArrayList<PrimeFactor> res = factors(num - 1);
            Collections.sort(res);
            for (int i = 0 ; i < res.size() ; ++i)
                if (i == 0)
                    sb.append(res.get(i).factor + " " + res.get(i).power);
                else sb.append(" " +res.get(i).factor + " " + res.get(i).power);
            sb.append("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }

    static ArrayList<PrimeFactor> factors (int n)
    {
        ArrayList<PrimeFactor> res = new ArrayList<>();
        for (int PF_IDX = 0 , PF = 2 ; PF * PF <= n ; PF_IDX ++ , PF = primes[PF_IDX])
        {
            if (n % PF == 0)
            {
                int cnt = 0;
                while (n % PF == 0) {n /= PF ; cnt ++;}
                res.add(new PrimeFactor(PF , cnt));
            }

            if (PF_IDX == N - 1) break;;
        }
        if (n != 1) res.add(new PrimeFactor(n , 1));
        return res;

    }

    static int pow (int n , int e)
    {
        int res = 1;
        while (e > 0)
        {
            if ((e & 1) == 1) res *= n;
            n *= n;
            e >>= 1;
        }

        return res;
    }
    static class PrimeFactor implements  Comparable<PrimeFactor>{
        int factor , power;
        PrimeFactor(int f , int p) {factor = f ; power = p;}

        @Override
        public int compareTo(PrimeFactor powerFactor) {
            return powerFactor.factor - factor;
        }

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
