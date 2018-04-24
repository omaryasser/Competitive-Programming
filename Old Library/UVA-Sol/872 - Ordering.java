import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static ArrayList<Character> chars ;
    static boolean adjMat [][];
    static boolean taken [];
    static boolean one ;
    static int len;
    static StringBuilder sb ;

    public static boolean can_put (int idx , StringBuilder current)
    {
        for (int i = 0 ; i < current.length() ; ++i)
            if (adjMat[chars.get(idx) - 'A'][current.charAt(i) - 'A']) return false;
        return true;
    }
    public static void backtrack (int curLen , StringBuilder current)
    {
        if (curLen == len) {
            one = true;
            for (int i = 0; i < current.length(); ++i)
                if (i == 0) sb.append(current.charAt(0));
                else sb.append(" " + current.charAt(i));
            sb.append("\n");
        }
        else
        {
            for (int i = 0 ; i < chars.size(); ++i)
            {
                if (!taken[i] && can_put(i,current))
                {
                    taken[i] = true;
                    backtrack(curLen + 1 , new StringBuilder(current).append(chars.get(i)));
                    taken[i] = false;
                }
            }
        }

    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        sb = new StringBuilder();
        StringTokenizer st ;

        boolean fist = true;
        int T = Integer.parseInt(sc.nextLine());
        while (T -- > 0)
        {
            sc.nextLine();
            st = new StringTokenizer(sc.nextLine());
            chars = new ArrayList<>();
            while(st.hasMoreTokens()) chars.add(st.nextToken().charAt(0)); len = chars.size();
            Collections.sort(chars);
            adjMat = new boolean[26][26];
            taken = new boolean[26];
            st = new StringTokenizer(sc.nextLine());
            while (st.hasMoreTokens())
            {
                StringTokenizer in = new StringTokenizer(st.nextToken(),"<");
                adjMat[in.nextToken().charAt(0) - 'A'][in.nextToken().charAt(0) - 'A'] = true;
            }
            if (fist) fist = false;
            else sb.append("\n");

            one = false;
            backtrack(0 , new StringBuilder());
            if (!one) sb.append("NO\n");

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
