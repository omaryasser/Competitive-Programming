struct ST{
    int n,lg;
    vector<vector<int> >tree;
    vector<int>a,plog;
    ST(int n,int *a){
        this->n=n;
        this->a=vector<int>(a,a+n);
        lg=0;
        while((1<<lg)<n)lg++;
        tree.assign(n,vector<int>(lg));
        plog.assign(n+1,0);
        int pw=2,curL=0;
        REP(i,n+1){
            if(i==pw)curL++,pw<<=1;
            plog[i]=curL;
        }
        REP(i,n)tree[i][0]=a[i];
        REP1(log,1,lg)
            REP(i,n){
                if(i+(1<<log)-1<n)
                    tree[i][log]=__gcd(tree[i][log-1],tree[i+(1<<(log-1))][log-1]);
            }
    }
    int q(int s,int e){
        int d=plog[e-s+1];
        if(s==e)return a[s];
        return __gcd(tree[s][d],tree[e-(1<<d)+1][d]);
    }
};
