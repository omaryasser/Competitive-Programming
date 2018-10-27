#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(), x.end()
#define f(i, x, n) for (int i = x; i < n; i++)

struct state {
    int len, link;
    map<char, int> next;
};
const int MAXLEN = 90001;
state st[MAXLEN * 2];
int sz, last;
ll mem[MAXLEN << 1];
ll INF = 1e16;

void sa_init() {
    st[0].len = 0;
    st[0].link = -1;
    sz++;
    last = 0;
}

void sa_extend(char c) {
    int cur = sz++;
    st[cur].len = st[last].len + 1;
    int p = last;
    while (p != -1 && !st[p].next.count(c)) {
        st[p].next[c] = cur;
        p = st[p].link;
    }
    if (p == -1) {
        st[cur].link = 0;
    } else {
        int q = st[p].next[c];
        if (st[p].len + 1 == st[q].len) {
            st[cur].link = q;
        } else {
            int clone = sz++;
            st[clone].len = st[p].len + 1;
            st[clone].next = st[q].next;
            st[clone].link = st[q].link;
            while (p != -1 && st[p].next[c] == q) {
                st[p].next[c] = clone;
                p = st[p].link;
            }
            st[q].link = st[cur].link = clone;
        }
    }
    last = cur;
}


ll dp(int idx){
    if(mem[idx] != -1)return mem[idx];
    ll &ret = mem[idx];
    ret = idx != 0;
    for(auto p : st[idx].next)ret = min(INF, ret + dp(p.second));
    return ret;
}

int main () {
    ios_base::sync_with_stdio(0); cin.tie(0);

    string s;
    cin >> s;
    sa_init();
    int n = (int)s.length();
    f(i, 0, n)sa_extend(s[i]);
    memset(mem, -1, sizeof mem);
    int q;
    cin >> q;
    while(q--){
        ll k;
        cin >> k;
        string res = "";
        int state = 0;
        while(k){
            for(auto p : st[state].next){
                if(dp(p.second) >= k){
                    k--;
                    res += p.first;
                    state = p.second;
                    break;
                }else{
                    k -= dp(p.second);
                }
            }
        }
        cout << res << "\n";
    }
}
