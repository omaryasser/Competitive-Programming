
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            int N = sc.nextInt();
            if(N == 0) break;
            TreeMap<Long , Integer> tm = new TreeMap<>();
            long cnt = 0;
            while (N -- > 0)
            {
                int k = sc.nextInt();
                while (k -- >0)
                {
                    long x = sc.nextLong();
                    if (tm.containsKey(x)) tm.put(x , tm.get(x) + 1);
                    else tm.put(x , 1);
                }
                cnt += (tm.lastKey() - tm.firstKey());
                Integer first = tm.get(tm.firstKey()) , second = tm.get(tm.lastKey());
                if(first == 1)
                    tm.remove(tm.firstKey());
                else tm.put(tm.firstKey() , tm.get(tm.firstKey()) - 1);

                if(second == 1)
                    tm.remove(tm.lastKey());
                else tm.put(tm.lastKey() , tm.get(tm.lastKey()) - 1);

            }
            out.printf("%d\n" , cnt);
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
