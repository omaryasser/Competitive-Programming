import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();


        String res [] = {"0/1","1/2","2/6","9/24","44/120","265/720","1854/5040","14833/40320","133496/362880","1334961/3628800","14684570/39916800" , "176214841/479001600"};
        int T = sc.nextInt();
        HashMap<Integer,String> ans = new HashMap<>();
        while (T -- > 0)
            out.print(res[sc.nextInt() - 1]+'\n');

//        int n = sc.nextInt();
//            if (ans.containsKey(n))
//            {
//                out.print(ans.get(n)+"\n");
//                continue;
//            }
//            int A [] = new int[n];
//            for (int i = 0 ; i < n ; ++i) A[i] = i;
//            long cnt = 0 , cnt2 = 0;
//            do {
//                ++cnt2;
//                if (check(A)) ++cnt;
//            }while (next_permutation(A));
//            out.printf("%s\n" , cnt+"/"+cnt2);
        out.flush();
        out.close();
    }


    static boolean check (int [] A)
    {
        for (int i = 0 ; i < A.length ; ++i)
            if (A[i] == i) return false;
        return true;
    }
    static boolean next_permutation(int[] A) {
        for (int a = A.length - 2; a >= 0; --a) {
            if (A[a] < A[a + 1]) {
                for (int b = A.length - 1;; --b) {
                    if (A[b] > A[a]) {
                        int t = A[a];
                        A[a] = A[b];
                        A[b] = t;
                        for (++a, b = A.length - 1; a < b; ++a, --b) {
                            t = A[a];
                            A[a] = A[b];
                            A[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
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
