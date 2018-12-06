#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct STmin {
    vector<int> tree, arr;
    int n;

    void init(int n, vector<int> arr) {
        this->n = n;
        this->arr = arr;
        tree.assign(n << 1 << 1, 0);
        build(1, 0, n - 1);
    }

    void build(int u, int s, int e) {
        if (s == e) tree[u] = arr[s];
        else {
            int m = (s + e) >> 1;
            build(u << 1, s, m);
            build(u << 1 | 1, m + 1, e);
            tree[u] = min(tree[u << 1], tree[u << 1 | 1]);
        }
    }

    int query(int u, int s, int e, int l, int r) {
        if (s >= l && e <= r)return tree[u];
        if (s > r || e < l)return 12342344;
        int m = s + e >> 1;
        return min(query(u << 1, s, m, l, r), query(u << 1 ^ 1, m + 1, e, l, r));
    }

    int query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }
};

struct ST {
    vector<int> tree, lazy;
    int n;

    void init(int n) {
        this->n = n;
        tree.assign(n << 1 << 1, 0);
        lazy.assign(n << 1 << 1, 0);
    }

    void propagate(int u, int s, int m, int e) {
        if (lazy[u]) {
            tree[u << 1] = max(tree[u << 1], lazy[u]);
            tree[u << 1 ^ 1] = max(tree[u << 1 ^ 1], lazy[u]);
            lazy[u << 1] = max(lazy[u << 1], lazy[u]);
            lazy[u << 1 ^ 1] = max(lazy[u << 1 ^ 1], lazy[u]);
            lazy[u] = 0;
        }
    }

    void update(int u, int s, int e, int l, int r, int ad) {
        if (s >= l && e <= r) {
            tree[u] = max(tree[u], ad);
            lazy[u] = max(lazy[u], ad);
        } else if (s > r || e < l);
        else {
            int m = s + e >> 1;
            propagate(u, s, m, e);
            update(u << 1, s, m, l, r, ad);
            update(u << 1 ^ 1, m + 1, e, l, r, ad);
            tree[u] = max(tree[u << 1], tree[u << 1 ^ 1]);
        }
    }

    void update(int l, int r, int ad) {
        update(1, 0, n - 1, l, r, ad);
    }

    int query(int u, int s, int e, int l, int r) {
        if (s >= l && e <= r)return tree[u];
        if (s > r || e < l)return 0;
        int m = s + e >> 1;
        propagate(u, s, m, e);
        return max(query(u << 1, s, m, l, r), query(u << 1 ^ 1, m + 1, e, l, r));
    }

    int query(int l, int r) {
        return query(1, 0, n - 1, l, r);
    }
};

void sort_(vector<int> &SA, vector<int> &RA, int n, int k)
{
    int maxi = max(256, n);
    vector<int> c(maxi);
    for(int i = 0; i < n; ++i)
        c[i + k < n ? RA[i + k] : 0]++;
    for(int i = 0, sum = 0; i < maxi; ++i)
    {
        int t = c[i];
        c[i] = sum;
        sum += t;
    }
    vector<int> tmp(n);
    for(int i = 0; i < n; ++i)
    {
        int j = SA[i] + k;
        tmp[c[j < n ? RA[j] : 0]++] = SA[i];
    }

    for(int i = 0; i < n; ++i)
        SA[i] = tmp[i];
}

vector<int> buildSA (string s) {
    int n = (int)s.length();
    vector<int> SA(n), RA(n);

    for(int i = 0; i < n; ++i) { RA[i] = s[i]; SA[i] = i; }

    for(int k = 1; k < n; k <<= 1)
    {
        sort_(SA, RA, n, k);
        sort_(SA, RA, n, 0);
        vector<int> tmp(n);

        for(int i = 1, r = 0, s1 = SA[0], s2; i < n; ++i)
        {
            s2 = SA[i];
            tmp[s2] = RA[s1] == RA[s2] && RA[s1 + k] == RA[s2 + k] ? r : ++r;
            s1 = s2;
        }
        for(int i = 0; i < n; ++i)
            RA[i] = tmp[i];

        if(RA[SA[n-1]] == n - 1)
            break;
    }
    return SA;
}



vector<int> buildLCP (string s, vector<int> SA) {
    int n = (int)s.length();
    vector<int> LCP(n), rank(n);
    for (int i = 0; i < n; i++)
        rank[SA[i]] = i;

    int commonLength = 0;
    for (int i = 0; i < n; i++) {
        if (rank[i] != 0) {
            int j = SA[rank[i] - 1];
            while (i + commonLength < n && s[i + commonLength] == s[j + commonLength])
                commonLength++;
        }
        LCP[rank[i]] = commonLength;
        if (commonLength > 0) commonLength--;
    }
    for(int i = 0; i < n - 1; i++)
        LCP[i] = LCP[i + 1];
    return LCP;
}

struct STLCP {
    int n, lg;
    vector<vector<int> > treemax, treemin;
    vector<int> a, plog;

    void init(vector<int> a_) {
        int n_ = (int) a_.size();
        this->n = n_;
        a.assign(n, 0);
        for (int i = 0; i < n; i++) {
            a[i] = a_[i];
        }
        lg = 0;
        while ((1 << lg) <= n)lg++;
        treemax.assign(n, vector<int>(lg));
        treemin.assign(n, vector<int>(lg));
        plog.assign(n + 1, 0);
        int pw = 2, curL = 0;
        for (int i = 0; i < n + 1; i++) {
            if (i == pw)curL++, pw <<= 1;
            plog[i] = curL;
        }
        for (int i = 0; i < n; i++)
            treemax[i][0] = a[i];

        for (int i = 0; i < n; i++)
            treemin[i][0] = a[i];
        for (int log = 1; log < lg; log++)
            for (int i = 0; i < n; i++) {
                if (i + (1 << (log - 1)) < n)
                    treemin[i][log] = min(treemin[i][log - 1], treemin[i + (1 << (log - 1))][log - 1]);
            }
    }


    int qmin(int s, int e) {
        int d = plog[e - s + 1];
        if (s == e)return a[s];
        return min(treemin[s][d], treemin[e - (1 << d) + 1][d]);
    }
};


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, k;
    cin >> n >> k;
    vector<int> indices, len;
    string s;
    for (int i = 1; i <= n; i++) {
        string s_;
        cin >> s_;
        s += s_;
        for (int j = 0, sz = (int) s_.length(); j < sz; j++)
            indices.push_back(i), len.push_back(sz - j);
        s += "$";
        indices.push_back(0);
        len.push_back(0);
    }
    vector<int> SA = buildSA(s), LCP = buildLCP(s, SA);
    int nn = (int) s.length();
    ST segtreecum;
    STLCP stlcp;
    stlcp.init(LCP);
    segtreecum.init(nn);
    STmin segtreemin;
    segtreemin.init((int) LCP.size(), LCP);
    vector<int> cnt(n + 1);
    vector<int> first_to_reach_k(nn, (int) 1e6);
    int idx = 0, start = 0, cur = 0;
    while (start <= nn - k) {
        while (cur < k && idx < nn) {
            if (indices[SA[idx]] && !cnt[indices[SA[idx]]]++) {
                cur++;
            }
            idx++;
        }
        if (cur == k) {
            first_to_reach_k[start] = idx - 1;
        }
        if (indices[SA[start]] && !--cnt[indices[SA[start]]]) {
            cur--;
        }
        start++;
    }

    for (int i = 0; i < nn; i++) {
        if (indices[SA[i]]) {
            int lo = 1, hi = len[SA[i]], best = -1;
            while (lo <= hi) {
                int length = lo + hi >> 1;
                int lo2 = 0, hi2 = i, best2 = i;
                while (lo2 <= hi2) {
                    int mid = lo2 + hi2 >> 1;
                    if (mid == i ? len[SA[i]] : stlcp.qmin(mid, i - 1) >= length) {
                        best2 = mid;
                        hi2 = mid - 1;
                    } else {
                        lo2 = mid + 1;
                    }
                }

                int lo2_ = i, hi2_ = nn - 1, best2_ = i;
                while (lo2_ <= hi2_) {
                    int mid = lo2_ + hi2_ >> 1;
                    if (mid == i ? len[SA[i]] : stlcp.qmin(i, mid - 1) >= length) {
                        best2_ = mid;
                        lo2_ = mid + 1;
                    } else {
                        hi2_ = mid - 1;
                    }
                }

                if (first_to_reach_k[best2] <= best2_) {
                    best = length;
                    lo = length + 1;
                } else {
                    hi = length - 1;
                }
            }
            if (best != -1) {
                segtreecum.update(i, i, best);
            }
        }
    }

    vector<ll> res(n + 1);
    for (int i = 0; i < nn; i++) {
        res[indices[SA[i]]] += segtreecum.query(i, i);
    }
    for (int i = 1; i <= n; i++) {
        if (i > 1) cout << " ";
        cout << res[i];
    }
    return 0;
}