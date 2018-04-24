import java.io.*;
import java.util.*;

public class Main {

    // My solution is overkill.
    // A better solution is to make the start day is 0 and continue to take days if ur sum is > 0 whenerever 
    // it is < 0 at i then all the indices form 0 - i cannot be taken as the start point.
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int tt = 1 ; tt <= T ; ++tt){
            int N = sc.nextInt();
            long available[] = new long[N];
            long need [] = new long[N];
            long arr [] = new long[N];
            for (int i = 0 ; i < N ; ++i) available[i] = sc.nextLong();
            for (int i = 0 ; i < N ; ++i) need[i] = sc.nextLong();
            for (int i = 0 ; i < N ; ++i) arr[i] = available[i] - need[i];

            long sum = 0 ;
            for (int i = 0 ; i < N ; ++i) sum += arr[i];
            if (sum < 0 || N == 0){
                sb.append("Case " + tt + ": Not possible\n");
                continue;
            }

            int i = 0 , j = 0;
            sum = 0;
            boolean moved = false;
            while (true){
                if (i == j && moved){
                    break;
                }
                moved = true;
                sum += arr[j];
                j = (j + 1) % N;
                while (sum < 0){
                    moved = false;
                    sum -= arr[i ++];
                }
            }

            sb.append("Case " + tt +": Possible from station " + (i + 1) + "\n");
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
