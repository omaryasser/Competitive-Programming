import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static class Pair implements Comparable<Pair>
    {
        int start , end;
        Pair(int s , int e){start = s ; end = e;}

        @Override
        public int compareTo(Pair o) {
            return start == o.start ? o.end - end : start - o.start;
        }
    }


    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        boolean first = true;
        while (T -- >0)
        {
            int M = sc.nextInt();
            ArrayList<Pair> arr = new ArrayList<Pair>();
            while (true)
            {
                int start = sc.nextInt() , end = sc.nextInt();
                if (start == 0 && end == 0) break;
                else arr.add(new Pair(start , end));
            }
            Collections.sort(arr);
            ArrayList<Pair> res = new ArrayList<>();

            int nw = 0 , curIdx = 0;
            boolean ok = true;
            while (nw < M)
            {
                int bestIdx = -1;
                boolean gotOne = false;
                while (curIdx < arr.size() && arr.get(curIdx).start <= nw)
                    if (arr.get(curIdx).end > nw && (!gotOne || arr.get(curIdx).end > arr.get(bestIdx).end)) {gotOne = true ; bestIdx = curIdx++;}
                    else ++ curIdx;
                if (!gotOne)
                    break;
                else res.add(arr.get(bestIdx));
                nw = arr.get(bestIdx).end;
            }
            if (first) first = false;
            else out.print("\n");

            if (nw < M)
                out.print("0\n");
            else
            {
                out.printf("%d\n" ,res.size());
                for (int i = 0 ; i < res.size() ; ++i)
                    out.printf("%d %d\n" , res.get(i).start , res.get(i).end);
            }
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
