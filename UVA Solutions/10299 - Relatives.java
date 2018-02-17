import java.io.*;
import java.util.*;

/**
 * Created by omar on 13/08/17.
 */
public class Main {
    static ArrayList<Integer> primes;
    public static void seive (int n)
    {
        primes = new ArrayList<>();
        boolean primess [] = new boolean[n+1];
        Arrays.fill(primess, true);
        primess[0]=primess[1]=false;
        for(int i =0; i*i<=n; i++)
            if(primess[i])
                for(int j =2; 1l * i*j <=n; j++)
                    primess [i*j]=false;

        for (int i = 0; i <= n; ++i)
            if (primess[i]) primes.add(i);
    }

    public static long num_of_coprimes(long N)
    {
        seive((int)Math.ceil(Math.sqrt(N)) + 1);
        int PF_IDX = 0;
        long PF = 2;
        long ans = N;
        while (PF * PF <= N)
        {
            if (N % PF == 0) ans -= ans / PF;
            while (N % PF == 0) N /= PF;
            if (PF_IDX == primes.size() - 1) break;
            PF = primes.get(++PF_IDX);
        }

        if (N != 1) ans -= ans / N;
        return ans;
    }

    public static void main (String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        seive((int)1e5);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            out.println(n == 1 ? 0 : num_of_coprimes(n));
        }
        out.close();
    }










































    static class Scanner {


        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }
    }

}
