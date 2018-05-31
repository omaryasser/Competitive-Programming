// Hopcroft O(E*sqrt(V))
class BipGraph{
public:
    int n,m;
    list<int>*adj;
    int*pairU,*pairV,*dist;
    int NIL=0,INF=1e9;
    bool bfs(){
        queue<int> Q;
        for(int u=1;u<=n;u++){
            if(pairU[u]==NIL){
                dist[u]=0;
                Q.push(u);
            }
            else dist[u]=INF;
        }
        dist[NIL]=INF;
        while(!Q.empty()){
            int u=Q.front();
            Q.pop();
            if(dist[u]<dist[NIL]){
                list<int>::iterator i;
                for(i=adj[u].begin();i!=adj[u].end();++i){
                    int v=*i;
                    if(dist[pairV[v]]==INF){
                        dist[pairV[v]]=dist[u]+1;
                        Q.push(pairV[v]);
                    }
                }
            }
        }
        return (dist[NIL] != INF);
    }
    bool dfs(int u){
        if (u!=NIL){
            list<int>::iterator i;
            for(i=adj[u].begin();i!=adj[u].end();++i){
                int v=*i;
                if(dist[pairV[v]]==dist[u]+1){
                    if(dfs(pairV[v])== 1){
                        pairV[v]=u;
                        pairU[u]=v;
                        return 1;
                    }
                }
            }
            dist[u]=INF;
            return 0;
        }
        return 1;
    }
    int hopcroftKarp(){
        pairU=new int[n+1];
        pairV=new int[m+1];
        dist=new int[n+1];
        for (int u=0;u<=n;u++)
            pairU[u]=NIL;
        for (int v=0;v<=m;v++)
            pairV[v]=NIL;
        int result=0;
        while(bfs()){
            for(int u=1;u<=n;u++)
                if (pairU[u]==NIL&&dfs(u)){
                    result++;
                }
        }
        return result;
    }
    BipGraph(int n,int m){
        this->m =m;
        this->n =n;
        adj=new list<int>[n+1];
    }
    void addEdge(int u,int v){
        adj[u].push_back(v);
    }
};
