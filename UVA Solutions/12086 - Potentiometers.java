import java.io.*;
import java.util.*;

public class Main{

    public static void main (String [] args) throws Exception
    {
        Scanner sc= new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int T = 1;
        while(true)
        {
            int N = sc.nextInt();
            if (N == 0) break;



            int arr [] = new int[N];
            for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
            
            FenwickTree fenwickTree = new FenwickTree(N);
            for(int i = 0 ; i < N ; ++i)
                fenwickTree.update(i+1 , arr[i]);

            if(first) first = false;
            else sb.append("\n");

            sb.append("Case "+T+++":\n");

            while(true)
            {
                String query = sc.next();
                if(query.charAt(0) == 'E') break;
                if(query.charAt(0) == 'S') {
                    int idx = sc.nextInt();
                    int num = sc.nextInt();
                    fenwickTree.update(idx , num - arr[idx-1]);
                    arr[idx - 1] = num;
                }
                else sb.append(fenwickTree.rsq(sc.nextInt(), sc.nextInt()) +"\n");
            }
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }


    static public class FenwickTree { // one-based DS

        int n;
        int[] ft;

        FenwickTree(int size) { n = size; ft = new int[n+1]; }

        int rsq(int b)
        {
            int sum = 0;
            while(b > 0) { sum += ft[b]; b -= b & -b;}		//min?
            return sum;
        }

        int rsq(int a, int b) { return rsq(b) - rsq(a-1); }

        void update(int k, int val)	//O(log n), update = increment
        {
            while(k <= n) { ft[k] += val; k += k & -k; }		//min?
        }

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
