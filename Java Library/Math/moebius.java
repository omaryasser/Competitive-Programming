/*
    μ(1) = 1
    μ(n) = 1 if n is a square-free positive integer
    with an even number of prime factors.
    μ(n) = −1 if n is a square-free positive integer
    with an odd number of prime factors.
    E.g. μ(2*3*5*7) = 1
    E.g. μ(2*3*5) = -1
    μ(n) = 0 if n has a squared prime factor

    */

    static int moebius (int n) {
        if (n == 1) return 1;
        int val = 1;
        for (int i = 2 ; i * i <= n ; ++ i)
            if (n % i == 0) {
                if (n % (i *i) == 0)
                    return 0; // not square-free
                n/= i;
                val = - val;
            }
        if (n != 1) val = - val;
        return val;
    }

    static int MAX = 1000000;
    static void moebius_generator() {
        int moebius[] = new int[MAX + 1];
        boolean isPrime[] = new boolean[MAX + 1];
        for (int i = 2; i <= MAX ; ++i) {
            moebius[i] = 1;
            isPrime[i] = true;
        }
        for (int i = 2 ; i <= MAX ; ++ i)
            if(isPrime[i]) {
                moebius[i] = - 1;
                for (int j = 2 * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                    moebius[j] = (j % (i * i) == 0) ? 0 : - moebius[j];
                }
            }
    }
