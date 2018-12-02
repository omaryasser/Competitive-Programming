int MOD=1e9+7;
const int MAX = 5 * 100000 + 1;
ll fact [MAX];
ll inv(ll x , ll mod){
    ll r, y;
    for(r = 1 , y = mod - 2 ; y != 0 ;x = x * x % mod, y>>=1)
        if ((y & 1ll) == 1)
            r = r * x % mod;
    return r;
}
ll ncr (int n, int r) {
    return ((ll)fact[n] * ((inv(fact[r], MOD) * inv(fact[n - r], MOD)) % MOD) % MOD) % MOD;
}

void factorial () {
    fact[0] = fact[1] = 1;
    for (int i = 2; i < MAX; i++)
        fact[i] = ((ll)i * fact[i - 1] % MOD);
}

const int MAX=1000;
ll comb[MAX][MAX];//init with -1
ll nCr(int n,int k){
    if(n<k)return 0;
    if(k==0||k==n)return 1;
    if(comb[n][k]!=-1)return comb[n][k];
    if(n-k<k)return comb[n][k]=nCr(n,n-k);
    return comb[n][k]=nCr(n-1,k-1)+nCr(n-1,k);
}
