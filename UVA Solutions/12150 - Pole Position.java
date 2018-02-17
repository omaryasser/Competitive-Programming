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
            int num [] = new int[n];
            int relPos [] = new int[n];
            int ans [] = new int[n];
            for (int i = 0 ; i < n ; ++i) {
                num[i] = sc.nextInt();
                relPos[i] = sc.nextInt();
            }
            Arrays.fill(ans , - 1);
            boolean ok = true;
            for (int i = 0 ; i < n ; ++i){
                int originalPos = i + relPos[i];
                if (originalPos < 0 || originalPos >= n || ans[originalPos] != - 1) {
                    ok = false;
                    break;
                }
                ans[originalPos] = num[i];
            }
            if (!ok) sb.append(-1 + "\n");
            else{
                for (int i = 0 ; i  < n ; ++i) if (i == 0) sb.append(ans[0]); else sb.append(" " + ans[i]);
                sb.append("\n");
            }
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
