import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static StringBuilder sb;
    static ArrayList<Character> chars;
    static boolean adjMat[][];
    static boolean taken [];

    public static boolean ok_to_take (int idx , StringBuilder curString)
    {
        for (int i = 0 ; i < curString.length() ; ++i)
            if (adjMat[chars.get(idx) - 'a'][curString.charAt(i) - 'a']) return false;
        return true;
    }
    public static void backtrack (int curLen , StringBuilder curString)
    {
        if (curLen == chars.size())
            sb.append(curString).append("\n");
        else
            for (int i = 0 ; i < chars.size() ; ++i)
                if (!taken[i] && ok_to_take(i , curString))
                {
                    taken[i] = true;
                    backtrack(curLen + 1 , new StringBuilder(curString).append(chars.get(i)));
                    taken[i] = false;
                }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        sb = new StringBuilder();
        int t = 0 ;
        boolean first = true;
        while (sc.ready())
        {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            chars = new ArrayList<>();
            while (st.hasMoreTokens()) chars.add(st.nextToken().charAt(0));
            Collections.sort(chars);

            adjMat = new boolean[26][26];
            taken = new boolean[26];
            st = new StringTokenizer(sc.nextLine());
            while (st.hasMoreTokens()) adjMat[st.nextToken().charAt(0) - 'a'][st.nextToken().charAt(0) - 'a'] = true;
            if (first) first = false;
            else sb.append("\n");
            backtrack(0 , new StringBuilder());
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
