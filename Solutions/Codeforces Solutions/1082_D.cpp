#include<bits/stdc++.h>
#define f(i, j, k) for (int i = j; i < k; i++)
using namespace std;


int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	
 	#ifndef ONLINE_JUDGE
 	freopen("D.in", "r", stdin);
	freopen("D.out", "w", stdout);
	#endif

	int n;
	cin >> n;           
	vector<int> a(n);
	set<pair<int,int> > st;
	f(i, 0, n)cin >> a[i], st.insert({a[i], i});
	vector<int> chain;
	auto it = st.begin();
	int idx = it->second;
	chain.push_back(idx);
	st.erase(it);
	while(!st.empty()){
	 	it = --st.end();
		int idx = it->second, deg = it->first;
		chain.push_back(idx);
		st.erase(it);
		if(deg == 1)break;
	}	

	vector<pair<int,int> > res;
	f(i, 0, (int)chain.size()){
	 	if(i == 0 || i == (int)chain.size() - 1)
			a[chain[i]]--;
		else a[chain[i]] -= 2;
		if(i)res.push_back({chain[i], chain[i - 1]});
	}	
	
	int idx_ = 0;
	while(!st.empty()){
		it = --st.end();
		int idx = it->second, deg = it->first;
		st.erase(it);
		while(idx_ < (int)chain.size()){
			if(a[chain[idx_]]){
//				cout << idx_ << " " << a[chain[idx_]] << "\n";
				res.push_back({idx, chain[idx_]});
				a[chain[idx_]]--;
				break;
			}else idx_++;
		}
		if(idx_ == (int)chain.size()){
			cout << "NO\n";
			return 0;
		}
	}
	cout << "YES " << (int)chain.size() - 1  << "\n";
	cout << (int)res.size() << "\n";
	for(auto p : res)
		cout << p.first + 1 << " " << p.second + 1 << "\n";
}
