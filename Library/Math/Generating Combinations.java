// Combinations of N elements in K with changes of exactly one element

// (O(2 ^ n))
int gray_code (int n) {
        return n ^ (n >> 1);
}

int count_bits (int n) {
        int res = 0;
        for (; n; n >> = 1)
                res + = n & 1;
        return res;
}

void all_combinations (int n, int k) {
        for (int i = 0; i <(1 << n); ++i) {
                int cur = gray_code (i);
                if (count_bits (cur) == k) {
                        for (int j = 0; j <n; ++j)
                                if (cur & (1 << j))
                                        printf ("% d", j + 1);
                        puts ("");
                }
        }
}

// O(nCk * n)
bool ans [MAXN];

void gen (int n, int k, int l, int r, bool rev, int old_n) {
        if (k> n || k <0) return;
        if (!n) {
                for (int i = 0; i <old_n; ++i)
                        printf ("% d", (int) ans [i]);
                puts ("");
                return;
        }
        ans [rev ? r : l] = false;
        gen (n-1, k,!rev ? l + 1 : l,!rev ? r : r-1, rev, old_n);
        ans [rev ? r : l] = true;
        gen (n-1, k-1,!rev ? l + 1 : l,!rev ? r : r-1,!rev, old_n);
}

void all_combinations (int n, int k) {
        gen (n, k, 0, n-1, false, n);
}
