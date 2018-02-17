import java.io.*;
import java.util.*;


public class Main {

    public static int matchScore (char x , char y)
    {
        int spaceScore = 0 , misMatchScore = -11111 , matchScore = 1;
        if(x==',' || y==',')
            return spaceScore;
        else if(x==y)
            return matchScore;
        else return misMatchScore;
    }



    public static int maxScoreAlignment(char[] x, char[] y) {
        int dp[][] = new int[x.length + 1][y.length + 1];


        dp[0][0] = 0;
        for (int i = 1; i <= x.length; i++)
            dp[i][0] = i * matchScore(',' , ',');
        for (int j = 1; j <= y.length; j++)
            dp[0][j] = j * matchScore(',' , ',');

        for (int i = 1; i < x.length + 1; ++i)
            for (int j = 1; j < y.length + 1; ++j) {
                int diagonal = dp[i - 1][j - 1] + matchScore(x[i - 1], y[j - 1]);
                int up = dp[i - 1][j] + matchScore(x[i - 1], ',');
                int left = dp[i][j - 1] + matchScore(',', y[j - 1]);

                dp[i][j] = Math.max(Math.max(up, left), diagonal);
            }



        return dp[x.length][y.length];
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ; ; T ++)
        {
            char x [] = sc.nextLine().toCharArray();
            if (x.length > 0 && x[0] == '#') break;
            char y [] =  sc.nextLine().toCharArray();
            out.printf("Case #%d: you can visit at most %d cities.\n" , T , maxScoreAlignment(x , y));
        }
        out.flush();
        out.close();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
