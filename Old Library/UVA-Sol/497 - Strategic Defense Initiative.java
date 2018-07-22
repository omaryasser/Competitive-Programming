
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static Stack<Integer> stack;	//contains the last solution in increasing order
    static int lis2(int[] A, int n)
    {
        ArrayList<Integer> L = new ArrayList<Integer>();
        int[] P = new int[n];
        int[] L_id = new int[n];

        int lis = 0, lis_end = -1;
        for(int i = 0; i < n; ++i)
        {
            int pos = Collections.binarySearch(L, A[i]);
            if (pos < 0) pos = -(pos + 1);
            //			 else	pos++;		non-decreasing

            if(pos >= L.size()) L.add(A[i]);
            else                 L.set(pos, A[i]);

            if(pos + 1 > lis)
            {
                lis = pos + 1;
                lis_end = i;
            }

            //lis_end and the following part for printing the solution
            L_id[pos] = i;
            P[i] = pos > 0 ? L_id[pos-1] : -1;
        }

        stack = new Stack<Integer>();
        while(lis_end != -1)
        {
            stack.push(A[lis_end]);
            lis_end = P[lis_end];
        }
        return lis;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        boolean first = true;
        while (T -- > 0) {
            if (first) first = false;
            else out.printf("\n");
            ArrayList<Integer> arr = new ArrayList<>();
            String line ;
            while ((line = sc.nextLine())!= null && !line.isEmpty()) arr.add(Integer.parseInt(line));
            int A[] = new int[arr.size()];
            for (int i = 0; i < arr.size(); ++i) A[i] = arr.get(i);
            out.printf("Max hits: %d\n", lis2(A, A.length));
            while (!stack.isEmpty()) out.printf("%d\n", stack.pop());
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
