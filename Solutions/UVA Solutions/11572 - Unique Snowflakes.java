
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T-- > 0)
        {
            int N = sc.nextInt();
            int arr [] = new int[N];
            for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
            TreeMap<Integer,Integer> idx = new TreeMap<Integer,Integer>();
            int cnt = 0 , max = 0;
            for(int i = 0 ; i < N ; ++i)
                if(idx.containsKey(arr[i])) {
                    i = idx.get(arr[i]);
                    cnt = 0;
                    idx.clear();
                }
                else {
                    idx.put(arr[i] , i);
                    max = Math.max(max , ++ cnt);
                }
            out.printf("%d\n" , max) ;
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
