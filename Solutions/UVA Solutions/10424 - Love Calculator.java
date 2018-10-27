

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t = 0;

        while (sc.ready())
        {
            char [] first = sc.nextLine().toCharArray();
            char [] second = sc.nextLine().toCharArray();

            int firstNum = firstOf(first);
            int secondNum = firstOf(second);
            double firstAns = calc(firstNum);
            double secondAns = calc(secondNum);
            out.printf("%.2f %s\n" , Math.round(Math.min(firstAns , secondAns) / Math.max(firstAns , secondAns) * 10000.0) / 100.0 , "%");
        }
        out.flush();
        out.close();
    }

    static int firstOf (char [] arr)
    {
        int res = 0 ;
        for (int i = 0 ; i < arr.length ; ++i){
            if (Character.isAlphabetic(arr[i]))
                res += ((int)(Character.toLowerCase(arr[i]) - 'a') + 1);
        }

        return res;
    }

    static int calc (int num)
    {
        if (num < 10) return num;
        int res = 0;
        while (num > 0)
        {
            res += (num % 10);
            num /= 10;
        }

        return calc(res);
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
