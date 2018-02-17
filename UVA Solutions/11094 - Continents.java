import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int N , M ;
    static char [][] arr;
    static int dx [] = {0,0,1,-1};
    static int dy [] = {1,-1,0,0};
    static int cnt ;
    static boolean lands [][];
    public static boolean valid (int x , int y)
    {
        return x >= 0 && x < N && lands[x][y];
    }
    public static void water (int x , int y)
    {
        ++cnt ;
        lands[x][y] = false;
        for (int i = 0 ; i < 4 ; ++i)
        {
            int newX = x + dx[i] ; int  newY = y + dy[i];
            newY = ((newY % M) + M) % M;
            if (valid(newX , newY)) water(newX , newY);
        }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (sc.ready())
        {
            N = sc.nextInt() ; M = sc.nextInt();
            arr = new char[N][M];
            lands = new boolean[N][M];
            for (int i = 0 ; i < N ; ++i) arr[i] = sc.next().toCharArray();
            int x = sc.nextInt() , y = sc.nextInt();
            char island = arr[x][y];
            for (int i = 0 ; i < N ; ++i)
                for (int j = 0 ; j < M ; ++j)
                    lands[i][j] = arr[i][j] == island;

            water(x , y);

            int MAX = 0;
            for (int i = 0 ; i < N ; ++i)
                for (int j = 0 ; j < M ; ++j)
                    if (valid(i,j))
                    {
                        cnt = 0;
                        water(i , j);
                        MAX = Math.max(MAX , cnt);
                    }
            sc.nextLine();
            out.printf("%d\n" , MAX);
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
