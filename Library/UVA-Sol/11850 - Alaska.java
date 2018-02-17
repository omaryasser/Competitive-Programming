import java.io.*;
import java.util.*;


public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true){
            int n = sc.nextInt();
            if (n == 0) break;
            int arr [] = new int[n];
            for (int i = 0 ; i < n ; ++i) arr[i] = sc.nextInt();
            Arrays.sort(arr);
            int fuel = 0 ;
            int last = 0 ;
            boolean ok = true;
            for (int i = 0 ; i < n ; ++i){
                if (arr[i] - last > fuel){
                    ok = false;
                    break;
                }
                fuel -= (arr[i] - last);
                fuel = 200;
                last = arr[i];
            }

            if (arr[n - 1] != 1422) {
                if (1422 - last > fuel) {
                    ok = false;
                }
                fuel -= (1422 - last);
            }



            if (!ok || 1422 - arr[n - 1] > fuel) sb.append("IMPOSSIBLE\n");
            else sb.append("POSSIBLE\n");
        }
        out.print(sb.toString());
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
