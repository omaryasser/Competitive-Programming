static int nCr1(int N, int K)		// O(K)
    {
        if(K > N)
            return 0;
        int res = 1;
        for(int i = 1; i <= K; ++i)
            res = (N - i + 1) * res / i;
        return res;
    }



static long [][] comb; // Initialize it with -1
static long nCr2(int n, int k)		// O(n * k)
    {
        if(n < k)
            return 0;
        if(k == 0 || k == n)		//may add k == 1 as a base case for fast calculations
            return 1;
        if(comb[n][k] != -1)
            return comb[n][k];
        if(n - k < k)
            return comb[n][k] = nCr2(n, n - k);		//reduce k to n - k
        return comb[n][k] = nCr2(n - 1, k - 1) + nCr2(n - 1 , k);
    }


// Pascal's Triangle
static void nCr3(int N)     // O(N * N)
    {
        comb = new long[N][N];
        comb[0][0] = 1;
        for (int i = 1; i < N; i++)
        {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++)
                comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]);       //may use mod
        }
    }

// O(1) nCr
    static int MAX = 5 * 100000 + 1;
    static int fact [] = new int[MAX];
    static int MOD = (int)1e9 + 7;
    static int ncr (int n, int r) {
        return (int)((long)fact[n] * ((inv(fact[r], MOD) * inv(fact[n - r], MOD)) % MOD) % MOD) % MOD;
    }
    static long inv(long x , long mod){
        long r, y;
        for(r = 1 , y = mod - 2 ; y != 0 ;x = x * x % mod, y>>=1)
            if ((y & 1) == 1)
                r = r * x % mod;
        return r;
    }
    static void factorial () {
        fact[0] = fact[1] = 1;
        for (int i = 2; i < MAX; i++)
            fact[i] = (int)((long)i * fact[i - 1] % MOD);
    }
