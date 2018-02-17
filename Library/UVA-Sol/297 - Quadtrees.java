
import java.io.*;
import java.util.*;

public class Main {

    static int image [][];
    static char [] first , second ;
    static int idx ;
    public static void fill (int a , int b , int c , int d , int crntIdx , int arrIdx)
    {
        switch (arrIdx == 0 ? first[crntIdx] : second[crntIdx])
        {
            case 'p' :
                fill( a + c >> 1 , b          , c          , b + d >> 1 , ++idx , arrIdx);
                fill(a           , b          , a + c >> 1 , b + d >> 1 , ++idx , arrIdx);
                fill(a           , b + d >> 1 , a + c >> 1 , d          , ++idx , arrIdx);
                fill(a + c >> 1  , b + d >> 1 , c          , d          , ++idx , arrIdx);
                break;
            case 'e' : break;
            case 'f' : color(a , b , c , d);
        }
    }
    public static void color (int row1 , int col1 , int row2 , int col2)
    {
        for(int i = row1 ; i < row2 ; ++i)
            for (int j = col1 ; j < col2 ; ++j)
                image[i][j] = 1;
    }
    public static int count ()
    {
        int cnt = 0;
        for(int i = 0 ; i < 32 ; ++i)
            for(int j = 0 ; j < 32 ; ++j)
                if (image[i][j] == 1) ++cnt;
        return cnt;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            image = new int[32][32];
            first = sc.next().toCharArray();
            second = sc.next().toCharArray();
            idx = 0; fill(0,0,32,32,idx,0);
            idx = 0; fill(0,0,32,32,idx,1);
            out.printf("There are %d black pixels.\n" , count ());
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
