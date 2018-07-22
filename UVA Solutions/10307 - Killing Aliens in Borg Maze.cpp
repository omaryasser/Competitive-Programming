#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

class UnionFind{
public:
    vector<int>p,rank,setSize;
    int numSets;
    void build(int N){
        setSize.assign(N,1);
        numSets=N;
        rank.assign(N,0);
        p.assign(N,0);
        for(int i=0;i<N;i++)p[i]=i;
    }
    int findSet(int i){return(p[i]==i)?i:(p[i]=findSet(p[i]));}
    bool isSameSet(int i,int j){return findSet(i)==findSet(j);}
    void unionSet(int i,int j){
        if(!isSameSet(i,j)){
            numSets--;
            int x=findSet(i),y=findSet(j);
            if(rank[x]>rank[y]){p[y]=x;setSize[x]+=setSize[y];}
            else{
                p[x]=y;setSize[y]+=setSize[x];
                if(rank[x]==rank[y])rank[y]++;
            }
        }
    }
    int numDisjointSets(){return numSets;}
    int sizeOfSet(int i){return setSize[findSet(i)];}
};


const int N=51;
int n,m;
string arr[N];
int dist[101][N][N];
int INF=N*N+2;
int dx[]={0,0,1,-1};
int dy[]={1,-1,0,0};

bool valid(int r,int c){
    return r>=0&&c>=0&&r<n&&c<m&&arr[r][c]!='#';
}
void bfs(pair<int,int>s,int idx){
    queue<pair<int,int> >q;
    q.push(s);
    REP(i,N)REP(j,N)dist[idx][i][j]=INF;
    dist[idx][s.first][s.second]=0;
    while(!q.empty()){
        pair<int,int>u=q.front();q.pop();
        int r=u.first,c=u.second;
        REP(k,4){
            int nr=r+dx[k],nc=c+dy[k];
            if(valid(nr,nc)&&dist[idx][nr][nc]==INF){
                dist[idx][nr][nc]=dist[idx][r][c]+1;
                q.push(make_pair(nr,nc));
            }
        }
    }
}

int kruskal(vector<pair<int,int> >v){
    vector<pair<int,pair<int,int> > >edges;
    REP(i,v.size())REP1(j,i+1,v.size()){
            int cost=dist[i][v[j].first][v[j].second];
            edges.push_back(make_pair(cost,make_pair(i,j)));
        }
    sort(all(edges));
    UnionFind uf;uf.build(v.size());
    int ret=0;
    for(auto edge:edges){
        if(uf.numSets==1)break;
        int u=edge.second.first,v=edge.second.second;
        if(!uf.isSameSet(u,v))
            uf.unionSet(u,v),ret+=edge.first;
    }
    return ret;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int tc;
    string line;
    getline(cin,line);
    stringstream ss(line);
    ss>>tc;
    while(tc--){
        getline(cin,line);
        stringstream ss(line);
        ss>>m>>n;
        vector<pair<int,int> >interesting;
        REP(i,n){
            getline(cin,arr[i]);
            REP(j,m)
            {
                if(arr[i][j]=='S'||arr[i][j]=='A')
                    interesting.push_back(make_pair(i,j));
            }
//            cerr<<arr[i]<<"\n";
        }
        REP(i,interesting.size())
            bfs(interesting[i],i);

        cout<<kruskal(interesting)<<"\n";
    }

}
