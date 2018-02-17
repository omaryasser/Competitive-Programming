static int Z, I, M, L, mu, lambda;
    // mu is the start of the cycle
    // lambda is the length of the cycle

    static int f(int x) { return (Z * x + I) % M; }

    // x0 is the start node of the search space
    static void floydCycleFinding(int x0) {
        int slow = f(x0), fast = f(f(x0));
        while (slow != fast) { slow = f(slow); fast = f(f(fast)); }
        // now slow = fast -- > both are in the cycle
        mu = 0; fast = x0;
        while (slow != fast) { slow = f(slow); fast = f(fast); mu++; }
        // now slow = fast = start of the cycle
        lambda = 1; fast = f(slow);
        while (slow != fast) { fast = f(fast); lambda++; }
        // now lambda = the length of the cycle
    }
