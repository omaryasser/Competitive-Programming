
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Interval implements Comparable<Interval>
    {
        int start , end;
        Interval (int s , int e) {start = s ; end = e;}

        @Override
        public int compareTo(Interval o) {
            return start == o.start ? o.end - end : start - o.start;
        }
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            int L = sc.nextInt() , G = sc.nextInt();
            if (L == 0 && G == 0) break;
            Interval [] arr = new Interval[G];
            for(int i = 0 ; i < G ; ++i){
                int x = sc.nextInt() , r = sc.nextInt();
                arr[i] = new Interval(x - r , x  + r);
            }

            Arrays.sort(arr);

            int nw = 0 ;
            int idx = 0 ;
            int cnt = 0;
            while (nw < L)
            {
                int bestIdx = - 1;
                while (idx < arr.length && arr[idx].start <= nw)
                {
                    if (arr[idx].end >= nw && (bestIdx == -1 || arr[idx].end > arr[bestIdx].end))
                        bestIdx = idx;

                    ++idx;
                }
                if (bestIdx == -1) break;
                nw = arr[bestIdx].end;
                ++cnt;
            }

            if (nw < L) sb.append("-1\n");
            else sb.append((arr.length - cnt)+"\n");
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
