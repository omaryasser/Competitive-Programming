class bridgeTree{
public:
    void dfs();
    vector<vector<int> > G,T;
    vector<int>dfs_low,dfs_num,in_scc;
    stack<int>st;
    int n,counter,scc;
    bridgeTree(int nn,vector<int> *GG){
        n=nn;
        G.assign(n,vector<int>());
        REP(i,n)G[i]=GG[i];
        dfs_num.assign(n,0);
        dfs_low.assign(n,0);
        in_scc.assign(n,-1);
        scc=0;
        counter=0;
        for(int i=0;i<n;i++)
            if(dfs_num[i]==0)
                dfs(i);
        T.assign(scc,vector<int>());
        for(int i=0;i<n;i++)
            for(int v:G[i])
                if(in_scc[i]!=in_scc[v]){
                    T[in_scc[i]].push_back(in_scc[v]);
                    T[in_scc[v]].push_back(in_scc[i]);
                }
        for(int i=0;i<scc;i++){
            sort(all(T[i]));
            T[i].erase(unique(all(T[i])),T[i].end());
        }
    }
    void dfs(int u,int p=-1){
        dfs_num[u]=dfs_low[u]=++counter;
        st.push(u);
        for(int v:G[u])
            if(v!=p){
                if(dfs_num[v]==0)
                    dfs(v,u);
                if(in_scc[v]==-1)
                    dfs_low[u]=min(dfs_low[u],dfs_low[v]);
            }
        if(dfs_num[u]==dfs_low[u]){
            while(1){
                int v=st.top();st.pop();
                in_scc[v]=scc;
                if(v==u)break;
            }
            scc++;
        }
    }
};
