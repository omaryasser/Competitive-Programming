#include <bits/stdc++.h>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second

typedef long long ll;

using namespace std;

int n;
const int N=100001;
bool occ[N];
vector<int>T[N];

struct node{
	int id;
	unordered_map<char,node* > nxt;
	node(int i):id(i){}
	node(){}
}*root=new node();


struct Trie{
	int id=1;
	void init(){
		node *cu=root;
		cu->id=0;
		occ[0]=1;
	}
	void insert(char* s){
		node* cu=root;
		while(1){
			char c=*s;
			if(cu->nxt.find(c)==cu->nxt.end()){
				T[cu->id].pb(id);
				node *nw=new node(id++);
				(cu->nxt[c])=nw;
			}
			cu=cu->nxt[c];
			s++;
			if(*s=='\0'){
				occ[cu->id]=1;
				break;
			}
		}
	}
};

char s[N];

int ans=0;
multiset<int> solve(int nod=0,int p=-1,int d=0){
	int res=0;
	multiset<int>st;
	for(int nx:T[nod])
		if(nx!=p){
			multiset<int>c=solve(nx,nod,d+1);
			if(c.size()>st.size())swap(c,st);
			for(auto i:c)st.insert(i);
		}	
	if(!occ[nod]&&st.size()){
		auto b=--st.end();
		st.erase(b);
		occ[nod]=1;
		ans+=*b-d;
	}
	if(occ[nod])st.insert(d);
	return st;
}

int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt","r",stdin);
	#endif


	Trie t;t.init();
	scanf("%d",&n);
	int len=0;
	REP(i,n){
		scanf("%s",s);
		len+=strlen(s);
		char *p=&s[0];
		t.insert(p);	
	}
	solve();
	printf("%d\n", len-ans);
}