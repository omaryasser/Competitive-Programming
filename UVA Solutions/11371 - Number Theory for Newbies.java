import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t=  0;
        while (sc.ready()) {
            String inp = sc.next();
            char[] arr = inp.toCharArray();
            Arrays.sort(arr);
            int i ;
            for (i = 0 ; i  < arr.length ; ++i)
                if (arr[i] != '0') break;
            if (i == 0);
            else {
                arr[0] = arr[i];
                arr[i] = '0';
            }
            StringBuilder first = new StringBuilder();
            for (int j = 0 ; j < arr.length ; ++j)
                first.append(arr[j]);
            Arrays.sort(arr);
            StringBuilder second = new StringBuilder();
            for (int j = arr.length - 1 ; j >= 0 ; --j) second.append(arr[j]);;
            long f = Long.parseLong(first.toString());
            long s = Long.parseLong(second.toString());
            out.printf("%d - %d = %d = 9 * %d\n" , s , f , s - f , (s - f) / 9);
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
