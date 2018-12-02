package codes;

import FastInput.Scanner;
import java.io.PrintWriter;

public class TaskF {
    static long modular_exponentiation (long a  , long  b , long c ) // returns ( a ^ b ) % c
    {
        long ans = 1L;
        while(b!=0)
        {
            if((b & (1)) == 1)
                ans = (ans*a) %c;

            a = (a * a)%c;
            b >>=1L ;
        }

        return ans;
    }
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int [] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = in.nextInt();
        int mod = (int)1e9 + 7;

        int MAX = (int)1e5;
        int [] pow = new int[MAX + 1];
        for (int i = 0; i <= MAX; i++)
            pow[i] = (int)modular_exponentiation(2, i, mod) - 1;
        int [] cntMultiples = new int[MAX + 1];
        for (int i = 0; i < n; i++) {
            int num = arr[i];
            cntMultiples[1]++;
            if (num != 1) cntMultiples[num]++;
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    cntMultiples[j]++;
                    if (num / j != j) cntMultiples[num / j]++;
                }
            }
        }

        int [] res = new int[MAX + 1];
        for (int gcd = MAX; gcd >= 1; gcd--) {
            int resHere = pow[cntMultiples[gcd]];
            for (int times = 2;; times++) {
                if ((long)gcd * times > MAX) break;
                resHere = resHere - res[gcd * times];
              if (resHere < 0) {resHere += mod;}
            }
            res[gcd] = ((resHere % mod) + mod) % mod;
        }
        out.println(res[1]);
    }
}
