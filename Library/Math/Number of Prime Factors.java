// For one number 

public static long numDiffPF(long N)
    {
        seive((int)Math.ceil(Math.sqrt(N)) + 2);
        int PF_IDX = 0 , PF = 2 ;
        long ans = 0;

        while (PF * PF <= N)
        {
            if (N % PF == 0) ++ans;
            while (N % PF == 0) N /= PF;

            PF = primes.get(++PF_IDX);
        }
        if (N != 1) ++ans;
        return ans;
    }


// more than one number 

static long numDiff [] ;
    public static void numDiffPF(int MAX_N)
    {
        numDiff = new long[MAX_N + 1];
        for (int i = 2 ; i <= MAX_N ; ++i)
            if (numDiff[i] == 0)
                for (int j = i ; j <= MAX_N ; j += i)
                    ++numDiff[j];
    }