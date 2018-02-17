// a ^ 1 + a ^ 2 + ... + a ^ k
static long pow(long a, int e)
    {
        long res = 1;
        while(e > 0)
        {
            if((e & 1) == 1)
                res *= a;
            a *= a;
            e >>= 1;
        }
        return res;
    }

    // a ^ 1 + a ^ 2 + ... + a ^ k
    static long sumPows2(long a , int k) { // O(log k)
        if (k == 0)
            return 0;

        if (k % 2 == 1)
            return a * (1 + sumPows(a , k - 1));

        return sumPows2(a , k >> 1) * (1 + pow(a , k >> 1));
    }
