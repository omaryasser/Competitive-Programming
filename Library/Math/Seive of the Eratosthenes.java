// Time complexity O(NloglogN). --- > up to 10 ^ 7 is OK
// int MOD1 = 1000_000_007, MOD2 = 9999_999_37;
boolean primes [] = new boolean[n+1];
Arrays.fill(primes, true);
primes[0]=primes[1]=false;
for(int i =0; i*i<=n; i++)
        if(primes[i])
                for(int j =2; i*j <=n; j++)
                        primes [i*j]=false;

// blocks
const int SQRT_MAXN = 100000; // root of the maximum value of N,
const int S = 10000;
bool nprime[SQRT_MAXN], bl[S];
int primes[SQRT_MAXN], cnt;

int main() {

        int n;
        cin >> n;
        int nsqrt = (int) sqrt (n + .0);
        for (int i=2; i<=nsqrt; ++i)
                if (!nprime[i]) {
                        primes[cnt++] = i;
                        if (i * 1ll * i <= nsqrt)
                                for (int j=i*i; j<=nsqrt; j+=i)
                                        nprime[j] = true;
                }

        int result = 0;
        for (int k=0, maxk=n/S; k<=maxk; ++k) {
                memset (bl, 0, sizeof bl);
                int start = k * S;
                for (int i=0; i<cnt; ++i) {
                        int start_idx = (start + primes[i] - 1) / primes[i];
                        int j = max(start_idx,2) * primes[i] - start;
                        for (; j<S; j+=primes[i])
                                bl[j] = true;
                }
                if (k == 0)
                        bl[0] = bl[1] = true;
                for (int i=0; i<S && start+i<=n; ++i)
                        if (!bl[i])
                                ++result;
        }
        cout << result;

}
