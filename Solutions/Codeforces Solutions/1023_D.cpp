#include <bits/stdc++.h>

#define REP(i, n) for(int i=0;i<(int)n;i++)
#define REP1(i, j, n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;
struct STMAX{
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

struct STMin{
	vector<int>tree;
	int n;
	void init(int n){
		this->n=n;
		tree.assign(n<<1<<1,1e6);
	}

	void update(int u,int s,int e,int l,int r,int ad){
		if(s>=l&&e<=r){
			tree[u]=ad;
		}else if(s>r||e<l);
		else{
			int m=s+e>>1;
			update(u<<1,s,m,l,r,ad);
			update(u<<1^1,m+1,e,l,r,ad);
			tree[u]=min(tree[u<<1],tree[u<<1^1]);
		}
	}

	void update(int l,int r,int ad){
		update(1,0,n-1,l,r,ad);
	}

	int query(int u,int s,int e,int l,int r){
		if(s>=l&&e<=r)return tree[u];
		if(s>r||e<l)return (int)1e7;
		int m=s+e>>1;
		return min(query(u<<1,s,m,l,r),query(u<<1^1,m+1,e,l,r));
	}

	int query(int l,int r){
		return query(1,0,n-1,l,r);
	}
};

int n, q;
vector<int> nxt;

int go(int idx)
{
	if(nxt[idx] == idx) return idx;
	return nxt[idx] = go(nxt[idx]);
}

int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);

	cin >> n >> q;
	STMin stmin; stmin.init(n);
	vector<int> a(n), s(q + 1, n + 1), e(q + 1, -1), f(q + 1);
	REP(i, n + 2) nxt.push_back(i);
	int zero_exist = 0;

	REP(i, n)
	{
		cin >> a[i];
		zero_exist += !a[i];
		f[a[i]] = 1;
	}

	if(zero_exist == n)
	{
		cout << "YES\n";
		cout << q;
		REP1(i, 1, n){
			cout << " " << q;
		}
		cout << "\n";
		return 0;
	}

	if(!f[q] && zero_exist)
	{
		REP(i, n)
		{
			if(!a[i])
			{
				a[i] = q;
				break;
			}
		}
	}
	REP1(i, 1, n) {
		if(!a[i] && a[i - 1])
		{
			a[i] = a[i - 1];
		}
	}
	for(int i = n - 2; i >= 0; i--)
	{
		if(!a[i] && a[i + 1])
		{
			a[i] = a[i + 1];
		}
	}

	REP(i, n)
	{
		stmin.update(i, i, a[i]);
		s[a[i]] = min(s[a[i]], i);
		e[a[i]] = i;
		f[a[i]] = 1;
	}
	bool ok = 1;
	REP1(i, 1, q + 1)
	{
		if(f[i])
		{
			if(s[i] + 1 <= e[i] - 1) {
				ok &= stmin.query(s[i] + 1, e[i] - 1) >= i;
			}
		}
	}

	if((!f[q] && !zero_exist) || !ok)
	{
		cout << "NO\n";
		return 0;
	}


	cout << "YES\n";
	vector<int> res(n, 0);
	for(int i = q; i >= 1; i--)
	{
		if(f[i])
		{
			int start = go(s[i]);
			while(start <= e[i] && start < n)
			{
				res[start] = i;
				nxt[start] = max(nxt[start], e[i] + 1);
				start = go(start + 1);
			}
		}
	}
	cout << res[0];
	REP1(i, 1, n){
		cout << " " << res[i];
	}
	cout << "\n";
}