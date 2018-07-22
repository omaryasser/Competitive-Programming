// phi(k)^(-1) = solve (k, 1)
static ArrayList<Integer> primes;
public static void seive(int n) {
        primes = new ArrayList<>();
        boolean primess[] = new boolean[n + 1];
        Arrays.fill(primess, true);
        primess[0] = primess[1] = false;
        for (int i = 0; i * i <= n; i++)
                if (primess[i])
                        for (int j = 2; 1l * i * j <= n; j++)
                                primess[i * j] = false;

        for (int i = 0; i <= n; ++i)
                if (primess[i]) primes.add(i);
}
static boolean isPrime (int num) {
        if (num == 0 || num == 1) return false;
        for (int i = 2; 1l * i * i <= num; i++)
                if (num % i == 0) return false;
        return true;
}

static TreeMap<Pair,Long> map = new TreeMap<>();
static long solve(int k, int min) {
        if (k == 1) return 1;
        Long x = map.get(new Pair(k, min));
        if (x != null) return x;
        long res = Long.MAX_VALUE;
        if (isPrime(k + 1) && min <= k + 1) res = k + 1;
        int start = Collections.binarySearch(primes, min);
        if (start < 0) start = -start -1;
        for (int i = start; i < primes.size(); i++) {
                int prime = primes.get(i);
                if (prime - 1 > k) break;
                int tmp = k;
                if (tmp % (prime - 1) == 0) {
                        tmp /= (prime - 1);
                        long go = solve(tmp, prime + 1);
                        if (go != Long.MAX_VALUE) {
                                res = Math.min(res, prime * solve(tmp, prime + 1));
                        }
                        long primePower = prime;
                        while (tmp % prime == 0) {
                                primePower *= prime;
                                tmp /= prime;
                                go = solve(tmp, prime + 1);
                                if (go != Long.MAX_VALUE) {
                                        res = Math.min(res, primePower * solve(tmp, prime + 1));
                                }
                        }

                }
        }
        map.put(new Pair(k, min), res);
        return res;
}

static class Pair implements Comparable<Pair>{
int a, b;
Pair (int aa, int bb) {
        a = aa; b = bb;
}

@Override
public int compareTo(Pair pair) {
        return a != pair.a ? a - pair.a : b - pair.b;
}
}
