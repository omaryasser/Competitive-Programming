import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int dx [] = {-1,0,1,0};
    static int dy [] = {0,1,0,-1};
    static int currentDir , N , M;
    static char [][] arr;
    static boolean invalid [][] ;
    static int currentX , currentY;
    public static void search ()
    {
        for (int i = 0 ; i < N ; ++i)
            for (int j = 0 ; j < M ; ++j)
            {
                switch (arr[i][j]) {
                    case 'N' : currentDir = 0; currentX = i ; currentY = j ;break;
                    case 'S' : currentDir = 2; currentX = i ; currentY = j ;break;
                    case 'L' : currentDir = 1; currentX = i ; currentY = j ;break;
                    case 'O' : currentDir = 3; currentX = i ; currentY = j ;break;
                    default:continue;
                }
            }
    }
    public static boolean valid (int i , int j)
    {
        return i >= 0 && j >= 0 && i < N && j < M && !invalid[i][j] ;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            N = sc.nextInt() ; M = sc.nextInt() ; int S = sc.nextInt();
            if (N == 0 && M == 0 && S == 0) break;
            arr = new char[N][M];
            for (int i = 0 ; i < N ; ++i) arr[i] = sc.next().toCharArray();
            invalid = new boolean[N][M];
            for (int i = 0 ; i < N ; ++i) for (int j = 0 ; j < M ; ++j) invalid[i][j] = arr[i][j] == '#';
            search();
            char [] inst = sc.next().toCharArray();
            int cnt = 0;
            for (int i = 0 ; i < inst.length ; ++i)
            {
                switch (inst[i])
                {
                    case 'D' : currentDir = (currentDir + 1) % 4;break;
                    case 'E' : currentDir = ((currentDir - 1) % 4 + 4) % 4;break;
                    case 'F' :
                    {
                        int newX = currentX + dx[currentDir] , newY = currentY + dy[currentDir];
                        if (valid(newX , newY))
                        {
                            currentX = newX;
                            currentY = newY;
                            if (arr[currentX][currentY] == '*'){
                                arr[currentX][currentY] = 'M';
                                ++ cnt;
                            }
                        }
                    }
                }
            }
            out.printf("%d\n" , cnt);
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
