import java.io.*;
import java.util.*;


public class Main {

    static HashMap<Character , Integer> hashMap = new HashMap<>();



    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        String cards [] = new String[53];
        while(true){
            int n = sc.nextInt();
            if (n == 0) break;
            Arrays.fill(cards , "omar");
            int idx = 0;
            for (int i =  0 ; i < n ; ++i){
                String first = sc.next();
                String second = sc.next();
                int cnt = second.length();
                boolean isFirst = true;
                do {
                    idx += isFirst ? 0 : 1;
                    isFirst = false;
                    idx %= n;
                    if (cards[idx].equals("omar")) --cnt;
                }while (cnt > 0);
                cards[idx]= first;
            }
            boolean first = true;
            for (int i = 0 ; i < 53 ; ++ i)
                    if (cards[i].equals("omar")) continue;
                    else if (first){
                        first = false;
                        sb.append(cards[i]);
                    }
                    else sb.append(" ").append(cards[i]);
            sb.append("\n");
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
