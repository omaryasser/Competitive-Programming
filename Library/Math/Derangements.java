// # of permutations without any i at position i
static long D(int n){
        if(n <= 1) return 0;
        else if(n == 2) return 1L;
        else return (n-1)*(D(n-1) + D(n-2));
}

static long derangement (int n)
{
        long fac [] = new long[n + 1];
        fac[1] = fac[0] = 1;
        for (int i = 2; i <= n; ++i)
                fac[i] = i * fac[i - 1];

        double denominator = 0;

        for (int k = 0; k <= n; ++k)
                denominator += (((k & 1) == 1 ? -1 : 1) / (double)fac[k]);

        return (long) (fac[n] * denominator);
}

static long der2 (int n) {
        long fac [] = new long[n + 1];
        fac[1] = fac[0] = 1;
        for (int i = 2; i <= n; ++i)
                fac[i] = i * fac[i - 1];
        return (long)(Math.round(fac[n] / Math.E));
}
