
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int mat [][] = new int[5][5];
            int N = sc.nextInt();
            while (N -- > 0) mat[sc.nextInt()][sc.nextInt()] = sc.nextInt();
            long min = Long.MAX_VALUE;
            int res [] = new int[5];

            for(int a = 0 ; a < 21 ; ++a)
                for(int b = a + 1 ; b < 22 ; ++b)
                    for(int c = b + 1 ; c < 23 ; ++c)
                        for(int d = c + 1 ; d < 24 ; ++d)
                            for (int e = d + 1 ; e < 25 ; ++e)
                            {
                                int sum = 0;
                                for(int search = 0 ; search < 25 ; ++search)
                                {
                                    int row = search / 5 , col = search % 5;
                                    int first   = Math.abs(row - a / 5 ) + Math.abs(col - a % 5);
                                    int second  = Math.abs(row - b / 5 ) + Math.abs(col - b % 5);
                                    int third   = Math.abs(row - c / 5 ) + Math.abs(col - c % 5);
                                    int fourth  = Math.abs(row - d / 5 ) + Math.abs(col - d % 5);
                                    int fifth   = Math.abs(row - e / 5 ) + Math.abs(col - e % 5);
                                    sum += mat[row][col] * Math.min(first , Math.min(second , Math.min(third , Math.min(fourth , fifth))));
                                }

                                if(sum < min)
                                {
                                    res = new int [] {a,b,c,d,e};
                                    min = sum;
                                }
                            }
            out.printf("%d %d %d %d %d\n" , res[0] , res[1] ,res[2] , res[3] ,res[4]);
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
