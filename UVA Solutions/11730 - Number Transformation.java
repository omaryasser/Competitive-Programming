import java.io.*;
import java.lang.reflect.Array;
import java.util.*;


public class A {


    static ArrayList<Integer> primes ;
    static int max = 1001;
    static void seive ()
    {
        boolean isComposite [] = new boolean[max + 1];
        primes = new ArrayList<>();
        isComposite[0] = true;
        isComposite[1] = true;
        for (int i = 2;  i <= max ; ++i)
            if (!isComposite[i]){
                primes.add(i);
                for (int j = i + i ; j <= max ; j += i)
                    isComposite[j] = true;
            }
    }
    static ArrayList<Integer> factors (int n)
    {
        int tmp = n;
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0 , prime = primes.get(i) ; prime <= n ; ++i , prime =primes.get(i))
        {
            if (n % prime == 0 && tmp != prime) res.add(prime);
            while (n % prime == 0) n /= prime;
            if (i +1 == primes.size()) break;
        }
        return res;
    }
    static int T;
    static int INF = (int)1e6;
    static int memo [];
    static ArrayList<Integer> primes2 [];
    static int dp (int num)
    {

        if (num > T) return INF;
        if (num == T) return 0;
        if (memo[num] != -1) return memo[num];
        int ans = INF;
        for (int i = 0 ; i < primes2[num].size() ; ++i)
        {
            ans = Math.min(ans  , 1 +  dp(num + primes2[num].get(i)));
        }
        return memo[num] = ans;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        seive();
        primes2 = new ArrayList[1001];
        for (int i = 0 ; i < 1001 ; ++i)
            primes2[i] = factors(i);

        for (int t = 1 ;; t++)
        {
            int n = sc.nextInt();
            T = sc.nextInt();
            if (n == 0 && T == 0)
                break;
            sb.append("Case "+t+": ");
            memo = new int[T + 1];
            Arrays.fill(memo , - 1);
            int res = dp(n);
            if (res < INF)
                sb.append(res + "\n");
            else sb.append("-1\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
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

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
