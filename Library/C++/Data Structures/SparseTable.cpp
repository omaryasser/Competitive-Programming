struct STLCP {
    int n, lg;
    vector<vector<int> > treemin;
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
        treemin.assign(n, vector<int>(lg));
        plog.assign(n + 1, 0);
        int pw = 2, curL = 0;
        for (int i = 0; i < n + 1; i++) {
            if (i == pw)curL++, pw <<= 1;
            plog[i] = curL;
        }
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
