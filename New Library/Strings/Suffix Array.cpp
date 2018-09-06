//int compare(int i, int j, int l, int k) { // compare two substrings starting at indices i, j of length l and k is the largest i : 2^i <= l
//    pair<int, int> a = {c[k][i], c[k][(i+l-(1 << k))%n]};
//    pair<int, int> b = {c[k][j], c[k][(j+l-(1 << k))%n]};
//    return a == b ? 0 : a < b ? -1 : 1;
//}

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
    for(int i = 0; i < n - 1; i++) // LCP[i] = LCP(i, i + 1)
        LCP[i] = LCP[i + 1];
    return LCP;
}
