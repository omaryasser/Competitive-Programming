
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            int N = sc.nextInt() , M = sc.nextInt();
            if(N == 0 && M == 0) break;
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            while (N-- >0)
            {
                int x = sc.nextInt();
                if(treeMap.containsKey(x))treeMap.put(x , 2);
                else treeMap.put(x , 1);
            }

            while (M-- >0)
            {
                int x = sc.nextInt();
                if(treeMap.containsKey(x))treeMap.put(x , 2);
                else treeMap.put(x , 1);
            }

            int cnt = 0;
            for(Map.Entry<Integer,Integer> map : treeMap.entrySet())
                if (map.getValue() == 2) ++cnt;

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
