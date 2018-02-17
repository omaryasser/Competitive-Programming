// Cat(0) = 0
// Cat(n) = (C (2n , n)) / (n + 1)

static int cat [];
    public static void cat (int n)
    {
        cat = new int[n + 1];
        cat[0] = 1;
        for(int i = 1; i <= n; ++i)
            cat[i] = cat[i-1] * (i<<1) * ((i<<1) - 1) / (i * (i + 1));
    }



 static BigInteger cat [];

    public static void cat (int n)
    {
        cat = new BigInteger[n + 1];
        cat[0] = BigInteger.ONE;
        for(int i = 1; i <= n; ++i)
            cat[i] = cat[i-1].multiply(BigInteger.valueOf((i<<1) * ((i<<1) - 1))).divide(BigInteger.valueOf(i * (i + 1)));
    }