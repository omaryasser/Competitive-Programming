// O(min(V*E^2, E*f))
class edmondkarp{
public:
    const int INF=1e8;
    int n;
    vector<vector<int> >res;
    vector<int>p;
    vector<vector<int> >adj;
    edmondkarp(int n){
        this->n=n;
        res.assign(n,vector<int>(n));
        p=vector<int>(n);
        adj.assign(n,vector<int>());
    }
    void add_edge(int u,int v,int c){
        res[u][v]+=c;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    int aug(int u,int f){
        if(p[u]==u)return f;
        f=min(f,aug(p[u],min(f,res[p[u]][u])));
        res[p[u]][u]-=f;
        res[u][p[u]]+=f;
        return f;
    }
    int max_flow(int S,int T){
        int f=0;
        while(1){
            for(int i=0;i<n;i++)p[i]=-1;
            queue<int>q;
            p[S]=S;q.push(S);
            while((int)q.size()){
                int u=q.front();q.pop();
                for(int v:adj[u])
                    if(res[u][v]>0&&p[v]==-1){
                        q.push(v);
                        p[v]=u;
                    }
            }
            if(p[T]==-1)return f;
            f+=aug(T,INF);
        }
    }
};
