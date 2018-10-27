import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static int C , R;
    static String spread [][];
    public static int colNum (String col)
    {
        int pow = 0;
        int colN = 0;
        for (int i = col.length() -1 ; i >= 0 ; --i) colN += (Math.pow(26 , pow++) * ((int)(col.charAt(i) - 'A') + 1));
        return colN;
    }
    public static boolean isNumber (String inp)
    {
        for (int i = 0 ; i < inp.length() ; ++i)
            if (!Character.isDigit(inp.charAt(i))) return false;
        return true;
    }
    public static int getRow (String inp)
    {
        String num = "";
        for (int i = 0 ; i < inp.length() ; ++i)
            if (Character.isDigit(inp.charAt(i))) num += inp.charAt(i);
        return Integer.parseInt(num);
    }
    public static int getCol (String inp)
    {
        String colString = "";
        for (int i = 0 ; i < inp.length() ; ++i)
            if (!Character.isDigit(inp.charAt(i))) colString+=inp.charAt(i);
        return colNum(colString);
    }

    public static int fill (int i , int j)
    {
        if (isNumber(spread[i][j])) return Integer.parseInt(spread[i][j]);

        int ans = 0;
        StringTokenizer st = new StringTokenizer(spread[i][j] , "+");
        boolean first = true;
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (first) {
                token = token.substring(1);
                first = false;
            }
            int row = getRow(token) - 1;
            int col = getCol(token) - 1;
            ans += fill(row , col);
        }
        spread[i][j] = ans +"";
        return ans;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            C = sc.nextInt(); R = sc.nextInt();
            spread = new String[R][C];
            for (int i = 0 ; i < R ; ++i) for (int j = 0 ; j < C ; ++j) spread[i][j] = sc.next();
            for (int i = 0 ; i < R ; ++i)
                for (int j = 0 ; j < C ; ++j)
                    if (isNumber(spread[i][j])) continue;
                    else fill (i,j);
            for (int i = 0 ; i < R ; ++i){
                for (int j = 0 ; j < C ; ++j)
                    if (j == 0) sb.append(spread[i][j]);
                    else sb.append(" " + spread[i][j]);
                sb.append("\n");
            }
        }
        out.printf(sb.toString());
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
