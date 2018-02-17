
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class Main {


    public static boolean same (char [][] B , int x , int y , char S [][] )
    {
        for(int i = 0 ; i < S .length ; ++i)
            for(int j = 0 ; j < S.length ; ++j)
                if(B[i + x][j + y] != S[i][j])
                    return false;
        return true;
    }
    public static long solve (char B [][] , char [][] S)
    {
        long ans = 0;
        for (int i = 0 ; i <= B.length - S.length ; ++i)
            for (int j = 0 ; j <= B.length - S.length ; ++j)
                if(same(B,i,j,S))
                    ++ans;
        return ans;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            int bigS = sc.nextInt() , smallS = sc.nextInt();
            if(bigS == 0 && smallS == 0) break;
            char [][] B = new char[bigS][bigS] , S = new char[smallS][smallS] , S90 =new char[smallS][smallS]  , S180 = new char[smallS][smallS]  , S270 = new char[smallS][smallS] ;
            for(int i = 0 ; i < bigS ; ++i) B[i] = sc.next().toCharArray();
            for(int i = 0 ; i < smallS ; ++i) S[i] = sc.next().toCharArray();
            for(int i = 0 ; i < smallS ; ++i) for(int j = 0 ; j < smallS ; ++j) S90[j][smallS - i - 1] = S[i][j];
            for(int i = 0 ; i < smallS ; ++i) for(int j = 0 ; j < smallS ; ++j) S180[smallS - i - 1][smallS - j - 1] = S[i][j];
            for(int i = 0 ; i < smallS ; ++i) for(int j = 0 ; j < smallS ; ++j) S270[smallS - j - 1][i] = S[i][j];

            out.print( solve(B,S) +" " + solve (B,S90) +" " + solve (B , S180) +" "+ solve (B,S270)+"\n");
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
