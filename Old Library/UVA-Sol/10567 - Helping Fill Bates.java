
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static ArrayList<Integer> [] freq;

    static class Pair
    {
        int lower , higher;
        Pair(int l , int h) {lower = l ; higher = h;}
    }

    public static Pair BSManager (char [] pattern)
    {
        int lo = 0 , first = 0;
        for (int i = 0 ; i < pattern.length ; ++i)
        {
            int idx = BS (freq[pattern[i] - 'A'] , lo);
            if (idx == -1) return new Pair(-1 , -1);
            lo = idx + 1;
            if (i == 0) first = idx ;
        }
        return new Pair(first , lo - 1);
    }

    public static int BS (ArrayList<Integer> arr , int search)
    {
        int lo = 0 , hi = arr.size() - 1;
        int lastFound = Integer.MAX_VALUE;
        for (int i = 0 ; i <= 20 ; ++i)
        {
            int mid = lo + ((hi - lo) >> 1) ;
            if(arr.get(mid) >= search)
            {
                lastFound = Math.min(lastFound , arr.get(mid));
                hi = Math.max(mid - 1 , Math.max(lo , 0));
            }
            else lo = Math.min(mid + 1  , Math.min(arr.size() -1 , hi));

        }

        return lastFound == Integer.MAX_VALUE ? -1 : lastFound ;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        char []arr = sc.next().toCharArray();
        freq = new ArrayList[58];
        for (int i = 0 ; i < 58 ; ++i) freq[i] = new ArrayList<>();
        for (int i = 0 ; i < arr.length ; ++i) freq[arr[i] - 'A'].add(i);

        int Q = sc.nextInt();
        while (Q -- > 0)
        {
            char [] pattern = sc.next().toCharArray();
            Pair cur = BSManager (pattern);
            if (cur.lower == -1)
                out.print("Not matched\n");
            else out.printf("Matched %d %d\n" , cur.lower , cur.higher);
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
