// O(V^3)
class pushrelabel{
public:
    const int INF=1e8;
    int n;
    vector<vector<int> >c,f;
    vector<int>h,e;
    vector<bool>active;
    pushrelabel(int n){
        this->n=n;
        c.assign(n,vector<int>(n));
        h=vector<int>(n);
        e=vector<int>(n);
        f.assign(n,vector<int>(n));
        active=vector<bool>(n);
    }
    void add_edge(int u,int v,int cap){
        c[u][v]+=cap;
    }
    int max_flow(int s,int t){
        h[s]=n-1;
        queue<int>q;
        for(int i=0;i<n;i++){
            f[i][s]=-(f[s][i]=e[i]=c[s][i]);
            if(i!=s&&i!=t&&e[i]>0){
                active[i]=true;
                q.push(i);
            }
        }

        while((int)q.size()){
            int u=q.front();
            bool pushed = false;
            for(int v=0;v<n&&e[u]!=0;v++) {
                if(h[u]>h[v]&&c[u][v]-f[u][v]>0){
                    int df=min(e[u],c[u][v]-f[u][v]);
                    f[u][v]+=df;
                    f[v][u]-=df;
                    e[u]-=df;
                    e[v]+=df;
                    if(v!=s&&v!=t&&!active[v]){
                        active[v]=true;
                        q.push(v);
                    }
                    pushed=true;
                }
            }
            if (e[u]==0){
                active[u]=false;
                q.pop();
            }

            if(!pushed){
                h[u]=INF;
                for(int v=0;v<n;++v)
                    if(c[u][v]-f[u][v]>0&&h[v]<=h[u])
                        h[u]=h[v]+1;
            }
        }
        return e[t];
    }
};
