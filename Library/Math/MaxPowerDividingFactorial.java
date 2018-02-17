// returns maximum x such that k^x | n
// this is right if k is prime , if it is not and k = p1^e1 * p2^e2 * ..., take the min (fact_pow(n, pi) / ei)
int fact_pow (int n, int k) {
        int res = 0;
        while (n) {
                n /= k;
                res += n;
        }
        return res;
}
