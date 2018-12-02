struct ST{
    vector<int>tree,lazy;
    int n;
    void init(int n){
        this->n=n;
        tree.assign(n<<1<<1,0);
        lazy.assign(n<<1<<1,0);
    }

    void propagate(int u,int s,int m,int e){
        if(lazy[u]){
            tree[u<<1]+=lazy[u];
            tree[u<<1^1]+=lazy[u];
            lazy[u<<1]+=lazy[u];
            lazy[u<<1^1]+=lazy[u];
            lazy[u]=0;
        }
    }
    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            tree[u]+=ad;
            lazy[u]+=ad;
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            propagate(u,s,m,e);
            update(u<<1,s,m,l,r,ad);
            update(u<<1^1,m+1,e,l,r,ad);
            tree[u]=max(tree[u<<1],tree[u<<1^1]);
        }
    }

    void update(int l,int r,int ad){
        update(1,0,n-1,l,r,ad);
    }

    int query(int u,int s,int e,int l,int r){
        if(s>=l&&e<=r)return tree[u];
        if(s>r||e<l)return 0;
        int m=s+e>>1;
        propagate(u,s,m,e);
        return max(query(u<<1,s,m,l,r),query(u<<1^1,m+1,e,l,r));
    }

    int query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};
