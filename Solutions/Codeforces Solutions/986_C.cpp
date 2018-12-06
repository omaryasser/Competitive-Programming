#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;
class UnionFind {                                              // OOP style
public:
	vector<int> p, rank, setSize;                       // remember: vi is vector<int>
	int numSets;

public:
  void build(int N) {
    setSize.assign(N, 1); numSets = N; rank.assign(N, 0);
    p.assign(N, 0); for (int i = 0; i < N; i++) p[i] = i; }
  int findSet(int i) { return (p[i] == i) ? i : (p[i] = findSet(p[i])); }
  bool isSameSet(int i, int j) { return findSet(i) == findSet(j); }
  void unionSet(int i, int j) {
    if (!isSameSet(i, j)) { numSets--;
    int x = findSet(i), y = findSet(j);
    // rank is used to keep the tree short
    if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
    else                   { p[x] = y; setSize[y] += setSize[x];
                             if (rank[x] == rank[y]) rank[y]++; } } }
  int numDisjointSets() { return numSets; }
  int sizeOfSet(int i) { return setSize[findSet(i)]; }
};
// 4194304=4e6
const int N=5e6;
int a[N];
int n,m;
int mp[N];
int fir[N];
UnionFind uf;
int all;
bool go(int msk,int x){
	if(msk==0)return 0;
	if(fir[msk]==-2)return 0;
	if(fir[msk]!=-1){
		uf.unionSet(mp[fir[msk]],mp[x]);
		return 1;
	}
	bool f=0;
	for(int i=0;i<n;i++)
		if(((1<<i)&msk)!=0){
			f|=go(msk^(1<<i),x);
		}

	if(mp[msk]!=-1){
		f=1;
		uf.unionSet(mp[x],mp[msk]);
	}
	if(f){
		fir[msk]=x;
	}else fir[msk]=-2;
	return f;
}

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif
		
	memset(mp,-1,sizeof mp);		
    scanf("%d %d",&n,&m);
    all=(1<<n)-1;
    uf.build(m);
    memset(fir,-1,sizeof fir);

    REP(i,m){
    	scanf("%d",a+i);
    	mp[a[i]]=i;
    	if(a[i]==0){
    		printf("1\n");
    		return 0;
    	}
    }
    // printf("asdf\n");
	REP(i,m){
		go(a[i]^all,a[i]);
	}
	int r=0;
	REP(i,m)r+=uf.p[i]==i;
	printf("%d\n", r);
    return 0;
}