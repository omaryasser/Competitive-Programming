
import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        TreeSet<Integer> ts = new TreeSet<Integer>();

        for(int i = 1 ; i <= 20 ; ++i)
        {
            if(!ts.contains(i)) arr.add(i);
            if(!ts.contains(i * 2)) arr.add(i * 2);
            if(!ts.contains(i * 3)) arr.add(i * 3);
            ts.add(i); ts.add(i * 2); ts.add(i * 3);
        }
        arr.add(50); arr.add(0);

        while(true)
        {
            int n = sc.nextInt();
            if(n <= 0) break;

            int permutations = 0 , combinations = 0;
            for (int i : arr)
                for(int j : arr)
                    for(int k : arr)
                        if (i + j + k == n)
                        {
                            ++permutations;
                            if(i <= j && j <= k) ++ combinations;
                        }
            if(permutations == 0) sb.append("THE SCORE OF "+n+" CANNOT BE MADE WITH THREE DARTS.\n");
            else {
                sb.append("NUMBER OF COMBINATIONS THAT SCORES "+n+" IS "+combinations+".\n");
                sb.append("NUMBER OF PERMUTATIONS THAT SCORES "+n+" IS "+permutations+".\n");
            }
            for(int i = 0 ; i < 70 ; ++i) sb.append("*");sb.append("\n");
        }

        sb.append("END OF OUTPUT\n");
        out.print(sb.toString());
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
