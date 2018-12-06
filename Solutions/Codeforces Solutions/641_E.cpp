#include <bits/stdc++.h>

using namespace std;

int n;
const int n_=1e5+10;
unordered_map<int,vector<pair<pair<int,int> ,int> > > queries;
int res[n_];

struct st{
	vector<int>T;
	int n;
	st(int sz){
		n=sz;
		T=vector<int>(sz<<1<<1);
	}
	void update(int idx,int ad){
		update(1,0,n-1,idx,ad);
	}
	void update(int node,int s,int e,int idx,int ad){
		if(s==e&&s==idx)T[node]+=ad;
		else if(s>idx||e<idx);
		else{
			int m=s+e>>1;
			update(node<<1,s,m,idx,ad);
			update(node<<1|1,m+1,e,idx,ad);
			T[node]=T[node<<1]+T[node<<1|1];
		}
	}
	int query(int s){return query(1,0,n-1,0,s);}
	int query(int node,int s,int e,int l, int r){
		if(s>=l&&e<=r)return T[node];
		if(s>r||e<l)return 0;
		int m=s+e>>1;
		return query(node<<1,s,m,l,r)+query(node<<1|1,m+1,e,l,r);
	}
};

void solve(vector<pair<pair<int,int> ,int> >q){
	st s=st((int)q.size());
	map<int,vector<pair<int,pair<int,int> > > >ts;
	for(int i=0;i<q.size();i++){
		ts[q[i].first.second].push_back({q[i].first.first,{i,q[i].second}});
	}
	int all=0;
	for(auto t:ts){
		for(auto op:t.second){
			if(op.first==1){
				s.update(op.second.first,1);
				all++;
			}else if(op.first==2){
				s.update(op.second.first,-1);
				all--;
			}else{
				res[op.second.second]=s.query(op.second.first);
			}
		}
	}
}

int main() {
	#ifndef ONLINE_JUDGE
	freopen("in.txt", "r", stdin);
	#else
	#endif

	scanf("%d",&n);
	vector<int>ask;
	for(int i=0;i<n;i++){
		int a,t,x;
		scanf("%d%d%d",&a,&t,&x);
		if(a==3)ask.push_back(i);
		queries[x].push_back({{a,t},i});
	}

	for(auto p:queries){
		solve(p.second);
	}
	for(int i:ask)
		printf("%d\n",res[i]);
}