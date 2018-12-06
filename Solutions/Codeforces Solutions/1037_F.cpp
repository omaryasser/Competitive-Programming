#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct ST{
    int n,lg;
    vector<vector<int> >tree;
    vector<int>a,plog;
    ST(int n,int *a){
        this->n=n;
        this->a=vector<int>(a,a+n);
        lg=0;
        while((1<<lg)<=n)lg++;
        tree.assign(n,vector<int>(lg));
        plog.assign(n+1,0);
        int pw=2,curL=0;
        for(int i = 0; i < n + 1; i++){
            if(i==pw)curL++,pw<<=1;
            plog[i]=curL;
        }
        for(int i = 0; i < n; i++)tree[i][0]=i;
        for(int log = 1; log < lg; log++)
        for(int i = 0; i < n; i++){
            if(i+(1<<log)-1<n) {
                if(a[tree[i][log - 1]] >= a[tree[i + (1 << (log - 1))][log - 1]])
                    tree[i][log] = tree[i][log - 1];
                else tree[i][log] = tree[i + (1 << (log - 1))][log - 1];
            }
        }
    }
    int q(int s,int e){
        int d=plog[e-s+1];
        if(s==e)return s;
        return a[tree[s][d]] >= a[tree[e-(1<<d)+1][d]] ? tree[s][d] : tree[e-(1<<d)+1][d];
    }
};

int n, k;
const int n_ = 1e6 + 10;
int a[n_];
int mod = (int)1e9 + 7;
int seg[n_];
int main () {
    scanf("%d %d", &n, &k);
    for(int i = 0; i < n; i++) {
        scanf("%d", a + i);
    }

    seg[1] = k == 1;
    for(int i = 2; i <= n; i++) {
        seg[i] = (((2ll * seg[i - 1] - seg[i - 2]) % mod) + mod) % mod;
        if(i == k || i > k && (i - k) % (k - 1) == 0) {
            seg[i]++;
            if(seg[i] >= mod) seg[i] -= mod;
        }
    }
    ST st(n, a);
    ll res = 0;
    for(int i = 0; i < n; i++) {
        int lo = 0, hi = i, bst1 = i;
        while (lo <= hi) {
            int m = lo + hi >> 1;
            if (st.q(m, i) == i) {
                bst1 = m;
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }
        lo = i, hi = n - 1;
        int bst2 = i;
        while (lo <= hi) {
            int m = lo + hi >> 1;
            if (st.q(i, m) == i) {
                bst2 = m;
                lo = m + 1;
            } else {
                hi = m - 1;
            }
        }
        lo = bst1;
        hi = bst2;
        res += 1ll * a[i] * seg[max(0, hi - lo + 1)] % mod;
        if(res >= mod) res -= mod;
        res -= 1ll * a[i] * seg[max(0, i - lo)] % mod;
        if(res < 0) res += mod;
        res -= 1ll * a[i] * seg[max(0, hi - i)] % mod;
        if(res < 0) res += mod;
    }
    printf("%lld\n", res);
}