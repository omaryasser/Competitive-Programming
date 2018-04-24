
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static String map = "AAB#C#D#E3F#G#HHIIJLK#LJMMN#OOP#Q#R#S2TTUUVVWWXXYYZ5112S3E4#5Z6#7#889#";
    
    public static int get_idx (char c)
    {
        if (Character.isAlphabetic(c))
            return (c - 'A') * 2;
        return (52 + (c - '1') * 2);
    }
    
    public static boolean isPal (String word)
    {
        return word.equals(new StringBuilder(word).reverse().toString());
    }
    
    public static boolean isMirrored (String word)
    {
        for (int i = 0 ; i <= word.length() / 2 ; ++i)
            if (word.charAt(word.length() - i - 1) != map.charAt(get_idx(word.charAt(i)) + 1))
                return false;
        return true;
    }
    
    public static boolean isMirroredPal (String word)
    {
        return isMirrored(word) && isPal(word);
    }
    
    public static void main (String [] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready())
        {
            String word = sc.next();
            if (isMirroredPal(word)) out.printf("%s -- is a mirrored palindrome.\n\n" , word);
            else if (isMirrored(word)) out.printf("%s -- is a mirrored string.\n\n" , word);
            else if (isPal(word)) out.printf("%s -- is a regular palindrome.\n\n" , word);
            else out.printf("%s -- is not a palindrome.\n\n" , word);
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
