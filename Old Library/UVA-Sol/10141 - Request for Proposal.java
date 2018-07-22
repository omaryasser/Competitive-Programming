
import java.io.*;
import java.util.*;

public class Main {
    static class RFP implements Comparable<RFP>{
        String name ;
        int requirements ;
        double price ;
        int idx;

        RFP (String n , int r , double p , int i ) {name = n ; requirements = r; price = p ; idx = i;}

        @Override
        public int compareTo(RFP o) {
            if(requirements!=o.requirements) return o.requirements - requirements;
            if(price != o.price) return price - o.price > 0 ? 1 : -1;
            return idx - o.idx;
        }
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(int T = 1 ;  ;  ++T) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int n = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
            if(n == 0 && p ==0) break;
            RFP[] rfps = new RFP[p];
            while (n-- > 0) sc.nextLine();
            for (int i = 0; i < p; ++i) {
                String name = sc.nextLine();
                st = new StringTokenizer(sc.nextLine());
                double price = Double.parseDouble(st.nextToken());
                int requirements = Integer.parseInt(st.nextToken());
                rfps[i] = new RFP(name, requirements, price, i);
                while (requirements-- > 0) sc.nextLine();
            }

            Arrays.sort(rfps);
            if(first) first = false;
            else out.print("\n");

            out.printf("RFP #%d\n" , T);
            out.print(rfps[0].name+"\n");
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
