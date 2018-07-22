
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Main {

    static int N ;
    static PrintWriter out ;
    static boolean primes[];
    static ArrayList<int [] > res ;
    public static void print (int arr [])
    {
        out.printf("1");
        for(int i = 1 ; i < N ; ++i) out.printf(" %d" , arr[i]);
        out.print("\n");
    }


    public static void backtrack (TreeSet<Integer> ts , int arr [] , int idx)
    {
        if(idx == 0) {
            arr[0] = 1;
            ts.add(1);
            backtrack(ts , arr , idx + 1);
        }
        else if(idx == N)
            print (arr);

        else
        {
            for(int i = 1 ; i <= N ; ++i)
                if (!ts.contains(i) && primes [i + arr[idx - 1]] && !(idx == N - 1 && !primes[1 + i]))
                {
                    ts.add(i);
                    arr[idx] = i;
                    backtrack(ts , arr , idx + 1);
                    ts.remove(i);
                }

        }
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        boolean first = true;
        for (int T = 1 ; sc.ready() ; ++ T)
        {
            N = sc.nextInt();
            primes = new boolean[16 +17 + 1];
            Arrays.fill(primes, true);
            primes[0]=primes[1]=false;
            for(int i =0 ; i*i<=16 + 17; i++)
                if(primes[i])
                    for(int j =2 ;i*j <=16 + 17 ; j++)
                        primes [i*j]=false;
            res = new ArrayList<>();
            if(first) first = false;
            else out.print("\n");
            out.printf("Case %d:\n" , T);
            backtrack (new TreeSet<>() , new int[N] , 0);
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
