// largest x such that p ^ x divides n , where p is prime
    static int maxPowInFact (long n , int p) {
        int power = 0 ;
        for (long i = p ; i <= n ; i *= p)
            power += n / i;

        return power;
    }
