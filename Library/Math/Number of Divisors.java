
// if N = P1^a * P2^b * P3 ^ c    where Pi is a prime number then N has (a + 1) * (b + 1) * (c + 1) divisors

public static ArrayList<Integer> primes;
    public static void seive (int n)
    {
        boolean primess [] = new boolean[n+1];
        Arrays.fill(primess, true);
        primess[0]=primess[1]=false;

        for(int i =0 ; i*i<=n; i++)
            if(primess[i])
                for(int j =2 ;i*j <=n ; j++)
                    primess [i*j]=false;

        primes = new ArrayList<>();
        for (int i = 2 ; i < n + 1 ; ++i)
            if (primess[i])
                primes.add(i);
    }
    public static int numDivisors (int N )
    {
        seive((int)Math.sqrt(N) + 5);
        int PF_idx = 0 , PF = 2 , ans = 1;
        while (PF * PF <= N)
        {
            int power = 0;
            while (N % PF == 0) {N /= PF; power ++;}
            ans *= (power + 1);
            PF = primes.get(++PF_idx);
        }
        if (N != 1) ans <<= 1;
        return ans;
    }