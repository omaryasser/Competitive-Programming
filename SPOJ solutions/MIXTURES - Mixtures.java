import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
 
public class Main {
 
    static int numMixtures;
    static long colorsPrefSum [];
    static long colors [];
    static long memo [][] ;
 
    static long dp (int leftIdx , int rightIdx) {
        if (memo[leftIdx][rightIdx] != - 1)
            return memo[leftIdx][rightIdx];
        if (leftIdx == rightIdx)
            return 0;
        if (leftIdx + 1 == rightIdx)
            return colors[leftIdx] * colors[rightIdx];
 
        long res = Long.MAX_VALUE;
        for (int end = leftIdx  ; end + 1 <= rightIdx ; ++ end) {
            long rightAlone = dp(end + 1 , rightIdx);
            long In = dp(leftIdx , end);
            long bigMerge = (((colorsPrefSum[rightIdx] - colorsPrefSum[end]) % 100)
                    * ((colorsPrefSum[end] - (leftIdx - 1 >= 0 ? colorsPrefSum[leftIdx - 1] : 0)) % 100));
            res = Math.min(res , rightAlone + In + bigMerge);
        }
        return memo[leftIdx][rightIdx] = res;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        while (sc.ready()) {
            numMixtures = sc.nextInt();
            colorsPrefSum = new long[numMixtures];
            colors = new long[numMixtures];
            memo = new long[numMixtures][numMixtures];
            for (int i = 0 ; i < numMixtures ; ++ i)
                Arrays.fill(memo[i] , - 1);
            for (int i = 0 ; i < numMixtures ; ++ i) {
                if (i == 0) {
                    colorsPrefSum[i] = colors[i] = sc.nextInt();
                }
                else {
                    colors [i] = sc.nextInt();
                    colorsPrefSum[i] = colorsPrefSum[i - 1] + colors[i];
                }
            }
            out.println(dp(0 , numMixtures - 1));
        }
        out.close ();
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
