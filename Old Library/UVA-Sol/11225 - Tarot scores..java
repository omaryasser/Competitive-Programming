
import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        boolean first = true;
        for (int t = 1 ; t <= T ; ++ t){
            int M = Integer.parseInt(sc.nextLine());
            String arr [] = new String[M];
            for (int i = 0 ; i < M ; ++i) arr[i] = sc.nextLine();
            int cnt = 0;
            for (int i = 0 ; i < M ; ++i){
                if (arr[i].equals("fool") || arr[i].equals("one of trumps") || arr[i].equals("twenty-one of trumps")) ++cnt;
            }

            int neededPoints = cnt == 0 ? 56 : cnt == 1 ? 51 : cnt == 2 ? 41 : 36;
            double points = 0;
            for (int i = 0 ; i < M ; ++i)
                if (arr[i].split(" ")[0].equals("king") || arr[i].equals("fool") || arr[i].equals("one of trumps") || arr[i].equals("twenty-one of trumps"))
                    points += 4.5;
                else if (arr[i].split(" ")[0].equals("queen")) points += 3.5;
                else if (arr[i].split(" ")[0].equals("knight")) points += 2.5;
                else if (arr[i].split(" ")[0].equals("jack")) points += 1.5;
                else points += 0.5;

            if (first) first = false;
            else sb.append("\n");

            sb.append("Hand #" + t +"\n");
            if (points < neededPoints)
                sb.append("Game lost by " + (int)(neededPoints - points)+ " point(s).\n");
            else
                sb.append("Game won by " + ((int)(points - neededPoints)) +" point(s).\n");
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
}
