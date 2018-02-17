//Euler's Phi function answer to number N = N * summation of (1 - PF)
// If n = p1^a1 * p2^a2 * p3^a3 * .. * pk^ak, then Euler function can be found using formula:  φ (n) = n * (1 – 1/p 1) * (1 – 1/p 2) * … * (1 – 1/p k)
// If p is prime, then φ (p) = p – 1 and φ (pa) = p a * (1 – 1/p) for any a.
// If m and n are coprime, then φ (m * n) = φ (m) * φ (n).
//PF is a prime factor of N

static ArrayList<Integer> primes;
public static void seive (int n)
{
        primes = new ArrayList<>();
        boolean primess [] = new boolean[n+1];
        Arrays.fill(primess, true);
        primess[0]=primess[1]=false;
        for(int i =0; i*i<=n; i++)
                if(primess[i])
                        for(int j =2; 1l * i*j <=n; j++)
                                primess [i*j]=false;

        for (int i = 0; i <= n; ++i)
                if (primess[i]) primes.add(i);
}

public static long num_of_coprimes(long N)
{
        // care if you wants # of coprimes < N
        // if (N == 1) return 0;
        seive((int)Math.ceil(Math.sqrt(N)) + 1);
        int PF_IDX = 0;
        long PF = 2;
        long ans = N;
        while (PF * PF <= N)
        {
                if (N % PF == 0) ans -= ans / PF;
                while (N % PF == 0) N /= PF;
                if (PF_IDX == primes.size() - 1) break;
                PF = primes.get(++PF_IDX);
        }

        if (N != 1) ans -= ans / N;
        return ans;
}



static long coprimes [];
public static void num_of_coprimes (int MAX_N)     // FOR more than 1 number
{
        coprimes = new long[MAX_N + 1];
        for (int i = 0; i <= MAX_N; ++i) coprimes[i] = i;

        for (int i = 2; i <= MAX_N; ++i)
                if (coprimes[i] == i)
                        for (int j = i; j <= MAX_N; j += i)
                                coprimes[j] -= coprimes[j] / i;
}
