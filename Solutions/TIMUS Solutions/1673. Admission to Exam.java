import java.io.*;
import java.util.*;

/**
 * Created by omar on 13/08/17.
 */
public class Main {

    static ArrayList<Integer> primes;

    public static void seive(int n) {
        primes = new ArrayList<>();
        boolean primess[] = new boolean[n + 1];
        Arrays.fill(primess, true);
        primess[0] = primess[1] = false;
        for (int i = 0; i * i <= n; i++)
            if (primess[i])
                for (int j = 2; 1l * i * j <= n; j++)
                    primess[i * j] = false;

        for (int i = 0; i <= n; ++i)
            if (primess[i]) primes.add(i);
    }
    static boolean isPrime (int num) {
        if (num == 0 || num == 1) return false;
        for (int i = 2; 1l * i * i <= num; i++)
            if (num % i == 0) return false;
        return true;
    }

    static TreeMap<Pair,Long> map = new TreeMap<>();
    static long solve(int k, int min) {
        if (k == 1) return 1;
        Long x = map.get(new Pair(k, min));
        if (x != null) return x;
        long res = Long.MAX_VALUE;
        if (isPrime(k + 1) && min <= k + 1) res = k + 1;
        int start = Collections.binarySearch(primes, min);
        if (start < 0) start = -start -1;
        for (int i = start; i < primes.size(); i++){
            int prime = primes.get(i);
            if (prime - 1 > k) break;
            int tmp = k;
            if (tmp % (prime - 1) == 0) {
                tmp /= (prime - 1);
                long go = solve(tmp, prime + 1);
                if (go != Long.MAX_VALUE) {
                    res = Math.min(res, prime * solve(tmp, prime + 1));
                }
                long primePower = prime;
                while (tmp % prime == 0) {
                    primePower *= prime;
                    tmp /= prime;
                    go = solve(tmp, prime + 1);
                    if (go != Long.MAX_VALUE) {
                        res = Math.min(res, primePower * solve(tmp, prime + 1));
                    }
                }

            }
        }
        map.put(new Pair(k, min), res);
        return res;
    }

    static class Pair implements Comparable<Pair>{
        int a, b;
        Pair (int aa, int bb) {a = aa; b = bb;}

        @Override
        public int compareTo(Pair pair) {
            return a != pair.a ? a - pair.a : b - pair.b;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        seive((int) 1e6);
        int k = sc.nextInt();
        long go = solve(k, 1);
        out.println(go == Long.MAX_VALUE ? 0 : go);

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
