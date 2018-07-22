void compute_z() {
    int L = -1, R = -1;
    z[0] = n;
    for(int i = 1; i < n; i++) {
        if(i > R) {
            L = R = i;
            while(R < n && s[R] == s[R - L]) R++;
            z[i] = R - L;
            R--;
        } else {
            int k = i - L;
            if(z[k] < R - i + 1) {
                z[i] = z[k];
            } else {
                L = i;
                while(R < n && s[R] == s[R - L]) R++;
                z[i] = R - L;
                R--;
            }
        }
    }
}
